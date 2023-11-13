package com.spabelleza.gestion_spa.service;

import com.spabelleza.gestion_spa.DTO.*;
import com.spabelleza.gestion_spa.entity.Appointment;
import com.spabelleza.gestion_spa.exception.NotFoundException;
import com.spabelleza.gestion_spa.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnType;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppointmentService implements IAppointmentService{

    private final JdbcTemplate jdbcTemplate;
    private final AppointmentRepository appointmentRepository;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private final ISpecialistService iSpecialistService;

    @Autowired
    public AppointmentService(JdbcTemplate jdbcTemplate, AppointmentRepository appointmentRepository, NamedParameterJdbcTemplate namedParameterJdbcTemplate, ISpecialistService iSpecialistService) {
        this.jdbcTemplate = jdbcTemplate;
        this.appointmentRepository = appointmentRepository;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.iSpecialistService = iSpecialistService;
    }

    @Override
    public List<AppointmentBySpecialityDTO> getAppointmentsBySpecialityAndDate(Integer specialityId, Timestamp startDate) {
        ZoneId bogotaZone = ZoneId.of("America/Bogota"); // Zona horaria de Bogotá

        List<Appointment> appointments = appointmentRepository.findBySpecialitySpecialityAndAppointmentDateAfter(specialityId, startDate);

        return appointments.stream()
                .map(appointment -> {
                    AppointmentBySpecialityDTO dto = new AppointmentBySpecialityDTO();
                    dto.setSpecialistId(appointment.getSpecialist().getSpecialistId());
                    dto.setSpecialityId(appointment.getSpeciality().getSpeciality());
                    dto.setAppointmentDate(appointment.getAppointmentDate().toInstant().atZone(bogotaZone).toLocalDateTime());
                    dto.setDuration(appointment.getDuration());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public LocalDate getPriorityAppointmentDate(Integer membershipTypeId, Integer planId){
        LocalDate startDate;
        LocalDate today = LocalDate.now();

        switch (membershipTypeId) {
            case 1: // ID de sin membresía
                startDate=today.plusDays(20);
                break;
            case 2: // ID de membresía para PLATINO
                startDate=today;
                break;
            case 3: // ID de membresía para ORO
                startDate = today.plusDays(7);
                break;
            case 4: // ID de membresía para PLATA
                startDate = today.plusDays(15);
                break;
            default:
                // Manejar el caso por defecto o lanzar una excepción si es necesario
                throw new NotFoundException("ID de membresía no válido: " + membershipTypeId);
        }
        switch (planId) {
            case 1:
                startDate=startDate.plusDays(1);
                break;
            case 2:
                //startDate=startDate;
                break;
            case 3:
            case 4:
                startDate = startDate.plusDays(3);
                break;
            default:
                // Manejar el caso por defecto o lanzar una excepción si es necesario
                throw new NotFoundException("ID de plan no válido: " + planId);
        }
        return startDate;
    }

    private List<LocalTime> calculateAvailableTimeSlots(LocalTime startTime, LocalTime endTime, List<AppointmentBySpecialityDTO> appointmentsOnDate) {
        List<LocalTime> availableTimeSlots = new ArrayList<>();

        // Crear un conjunto de todas las horas entre startTime y endTime
        Set<LocalTime> allTimeSlots = new HashSet<>();
        LocalTime currentTime = startTime;
        while (currentTime.isBefore(endTime)) {
            allTimeSlots.add(currentTime);
            currentTime = currentTime.plusHours(1); // Puedes ajustar el intervalo según tus necesidades
        }

        // Filtrar las horas disponibles eliminando las ocupadas
        allTimeSlots.removeAll(appointmentsOnDate.stream()
                .flatMap(appointment -> getHoursBetween(appointment.getAppointmentDate().toLocalTime(), appointment.getDuration(), appointment.getAppointmentDate()).stream())
                .collect(Collectors.toList()));

        availableTimeSlots.addAll(allTimeSlots);
        return availableTimeSlots;
    }

    private List<LocalTime> getHoursBetween(LocalTime startTime, int duration, LocalDateTime appointmentDateTime) {
        List<LocalTime> hoursBetween = new ArrayList<>();
        LocalTime currentTime = startTime;
        for (int i = 0; i < duration; i++) {
            // Comparar con la fecha y hora exactas de la cita

            //if (!currentTime.isAfter(appointmentDateTime.toLocalTime())) {
            hoursBetween.add(currentTime);
           //}
            currentTime = currentTime.plusHours(1);
        }
        return hoursBetween;
    }
    @Override
    public List<AvailableTimeSlotDTO> getAvailableTimeSlotsBySpeciality (Integer specialityId, Timestamp startDate) {

        List<AvailableTimeSlotDTO> availableTimeSlots = new ArrayList<>();
        // Obtener horarios laborales de los especialistas para la especialidad específica
        List<SpecialistScheduleDTO> specialistSchedules = iSpecialistService.getSpecialistScheduleBySpecialityId(specialityId);
        // Obtener citas agendadas para la especialidad y la fecha proporcionada
        List<AppointmentBySpecialityDTO> appointments = getAppointmentsBySpecialityAndDate(specialityId, startDate);

        // Crear un mapa para almacenar las citas agrupadas por especialista y día
        Map<Long, Map<LocalDate, List<AppointmentBySpecialityDTO>>> appointmentsBySpecialistAndDate = new HashMap<>();

        // Agrupar citas por especialista y día
        for (AppointmentBySpecialityDTO appointment : appointments) {
            appointmentsBySpecialistAndDate
                    .computeIfAbsent(appointment.getSpecialistId(), k -> new HashMap<>())
                    .computeIfAbsent(appointment.getAppointmentDate().toLocalDate(), k -> new ArrayList<>())
                    .add(appointment);
        }

        // Iterar sobre los horarios laborales de los especialistas
        for (SpecialistScheduleDTO schedule : specialistSchedules) {
            Long specialistId = schedule.getSpecialistId();
            String specialistName = schedule.getSpecialistName();
            LocalTime startTime = schedule.getStartTime();
            LocalTime endTime = schedule.getEndTime();

            // Obtener las citas para el especialista actual
            Map<LocalDate, List<AppointmentBySpecialityDTO>> appointmentsByDate = appointmentsBySpecialistAndDate.getOrDefault(specialistId, new HashMap<>());

            // Lista para almacenar las horas disponibles por día
            List<AvailableTimeSlotDTO> availableTimeSlotsBySpecialist = new ArrayList<>();

            // Iterar sobre los siguientes 3 días
            for (int i = 0; i < 1; i++) {
                LocalDate currentDate = startDate.toLocalDateTime().toLocalDate().plusDays(i);
                List<AppointmentBySpecialityDTO> appointmentsOnDate = appointmentsByDate.getOrDefault(currentDate, new ArrayList<>());

                // Filtrar las citas para el día actual
                List<AppointmentBySpecialityDTO> appointmentsForCurrentDate = appointmentsOnDate.stream()
                        .filter(appointment -> appointment.getAppointmentDate().toLocalDate().equals(currentDate))
                        .collect(Collectors.toList());

                // Calcular las horas disponibles para el día actual
                List<LocalTime> availableTimeSlotsOnDate = calculateAvailableTimeSlots(startTime, endTime, appointmentsForCurrentDate);

                // Agregar a la lista solo si hay disponibilidad
                if (!availableTimeSlotsOnDate.isEmpty()) {
                    AvailableTimeSlotDTO availableTimeSlot = new AvailableTimeSlotDTO(specialistId, specialistName, currentDate, availableTimeSlotsOnDate);
                    availableTimeSlotsBySpecialist.add(availableTimeSlot);
                }
            }
            // Agregar las listas de disponibilidad por especialista a la lista principal
            availableTimeSlots.addAll(availableTimeSlotsBySpecialist);
        }

        return availableTimeSlots;
    }
    @Override
    public List<MatchingTimeSlotResult> getAvailableTimeSlots(List<Integer> specialityIds, Timestamp startDate) {
        List<MatchingTimeSlotResult> matchingTimeSlots = new ArrayList<>();

        // Obtener las disponibilidades para cada especialidad
        List<List<AvailableTimeSlotDTO>> timeSlotsBySpeciality = new ArrayList<>();
        for (Integer specialityId : specialityIds) {
            List<AvailableTimeSlotDTO> timeSlots = getAvailableTimeSlotsBySpeciality(specialityId, startDate);
            timeSlotsBySpeciality.add(timeSlots);
        }

        // Comparar las disponibilidades para encontrar coincidencias de fecha y hora
        for (int i = 0; i < timeSlotsBySpeciality.size() - 1; i++) {
            List<AvailableTimeSlotDTO> timeSlots1 = timeSlotsBySpeciality.get(i);

            for (int j = i + 1; j < timeSlotsBySpeciality.size(); j++) {
                List<AvailableTimeSlotDTO> timeSlots2 = timeSlotsBySpeciality.get(j);

                // Comparar las disponibilidades para encontrar coincidencias de fecha y hora
                matchingTimeSlots = findMatchingTimeSlots(timeSlots1, timeSlots2);

                /*// Imprimir las coincidencias
                for (MatchingTimeSlotResult result : matchingTimeSlots) {
                    System.out.println("Coincidencia para especialidad " + result.getSpecialistId1() +
                            " y especialidad " + result.getSpecialistId2() +
                            " el día " + result.getDate() +
                            " a las horas " + result.getMatchingTimeSlots());
                }*/
            }
        }

        return matchingTimeSlots;
    }

    private List<MatchingTimeSlotResult> findMatchingTimeSlots(List<AvailableTimeSlotDTO> timeSlots1, List<AvailableTimeSlotDTO> timeSlots2) {
        List<MatchingTimeSlotResult> matchingTimeSlots = new ArrayList<>();

        for (AvailableTimeSlotDTO timeSlot1 : timeSlots1) {
            for (AvailableTimeSlotDTO timeSlot2 : timeSlots2) {
                // Verificar coincidencia de fecha
                if (timeSlot1.getDate().equals(timeSlot2.getDate())) {
                    // Verificar coincidencia de hora
                    List<LocalTime> overlappingTimes = getOverlap(timeSlot1.getAvailableTimeSlots(), timeSlot2.getAvailableTimeSlots());

                    if (!overlappingTimes.isEmpty()) {
                        // Agregar a la lista de coincidencias
                        MatchingTimeSlotResult result = new MatchingTimeSlotResult();
                        result.setSpecialistId1(timeSlot1.getSpecialistId());
                        result.setSpecialistName1(timeSlot1.getSpecialistName());
                        result.setSpecialistId2(timeSlot2.getSpecialistId());
                        result.setSpecialistName2(timeSlot2.getSpecialistName());
                        result.setDate(timeSlot1.getDate());
                        result.setMatchingTimeSlots(overlappingTimes);

                        matchingTimeSlots.add(result);
                    }
                }
            }
        }

        return matchingTimeSlots;
    }

    private List<LocalTime> getOverlap(List<LocalTime> timeSlots1, List<LocalTime> timeSlots2) {
        List<LocalTime> overlappingTimes = new ArrayList<>();

        for (LocalTime time1 : timeSlots1) {
            for (LocalTime time2 : timeSlots2) {
                // Verificar coincidencia de hora
                if (time1.equals(time2)) {
                    overlappingTimes.add(time1);
                }
            }
        }
        return overlappingTimes;
    }

    private static TYRC_APPOI getTyrcAppoi(Struct struct) throws SQLException {
        Object[] attributes = struct.getAttributes();
        TYRC_APPOI tyrc_appoi = new TYRC_APPOI();
        tyrc_appoi.setAppointmentId((BigDecimal) attributes[0]);
        tyrc_appoi.setSpecialistName((String) attributes[1]);
        tyrc_appoi.setUserName((String) attributes[2]);
        tyrc_appoi.setMembershipType((String) attributes[3]);
        tyrc_appoi.setPlanType((String) attributes[4]);
        tyrc_appoi.setSpecialityName((String) attributes[5]);
        Timestamp timestampFromDB = (Timestamp) attributes[6];
        TimeZone timeZoneFromDB = TimeZone.getTimeZone("America/Bogota"); // Zona horaria
        // Aplica la zona horaria especificada al Timestamp
        timestampFromDB.setTime(timestampFromDB.getTime() + timeZoneFromDB.getRawOffset());
        tyrc_appoi.setAppointmentDate(timestampFromDB);
        tyrc_appoi.setTotalPrice((BigDecimal) attributes[7]);
        tyrc_appoi.setStatus((String) attributes[8]);
        return tyrc_appoi;
    }

    @Override
    public List<TYRC_APPOI> getAppointmentAgenda(AppointmentDTO appointmentDTO) {
        try{
            Timestamp appointmentDate = null;
            Timestamp startDate = null;
            Timestamp endDate = null;

            Long specialistId = appointmentDTO.getSpecialistId();
            if(appointmentDTO.getAppointmentDate()!=null){
                appointmentDate = Timestamp.valueOf(appointmentDTO.getAppointmentDate());
            }
            Long userId = appointmentDTO.getUserId();
            Long planId = appointmentDTO.getPlanId();
            if(appointmentDTO.getStartDate()!=null){
                startDate = Timestamp.valueOf(appointmentDTO.getStartDate());
            }
            if(appointmentDTO.getEndDate()!=null){
                endDate = Timestamp.valueOf(appointmentDTO.getEndDate());
            }

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName("PKG_APPOINTMENT")
                    .withProcedureName("GET_APPOINTMENT_AGENDA").declareParameters(
                    new SqlParameter("p_specialist_id", Types.NUMERIC),
                    new SqlParameter("p_appointment_date", Types.TIMESTAMP),
                    new SqlParameter("p_user_id", Types.NUMERIC),
                    new SqlParameter("p_plan_id", Types.NUMERIC),
                    new SqlParameter("p_start_date", Types.TIMESTAMP),
                    new SqlParameter("p_end_date", Types.TIMESTAMP),
                    new SqlOutParameter("orc", Types.ARRAY, "TYTB_APPOI",
                            new SqlReturnType() {
                                @Override
                                public Object getTypeValue(CallableStatement cs, int paramIndex, int sqlType, String typeName) throws SQLException {
                                    Array array = cs.getArray(paramIndex);
                                    System.out.println(array);
                                    if (array != null) {
                                        //System.out.println(array);
                                        Object[] structArray = (Object[]) array.getArray();
                                        List<TYRC_APPOI> resultList = new ArrayList<>();
                                        for (Object struct : structArray) {
                                            TYRC_APPOI tyrc_appoi = getTyrcAppoi((Struct) struct);
                                            resultList.add(tyrc_appoi);
                                        }
                                        return resultList;
                                    }
                                    return Collections.emptyList();
                                }
                            }
                    )
            );

            MapSqlParameterSource inParams = new MapSqlParameterSource();
            inParams.addValue("p_specialist_id", specialistId);  // Nombre del parámetro en el procedimiento almacenado y su valor
            inParams.addValue("p_appointment_date", appointmentDate);
            inParams.addValue("p_user_id", userId);
            inParams.addValue("p_plan_id", planId);
            inParams.addValue("p_start_date", startDate);
            inParams.addValue("p_end_date", endDate);

            //System.out.println(inParams);
            Map<String, Object> result = jdbcCall.execute(inParams);
            return (List<TYRC_APPOI>) result.get("orc");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            // Loggear el error completo
            //log.error("Error al ejecutar el procedimiento almacenado: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void cargarArchivo(MultipartFile file) {
        String sql = "INSERT INTO APPOINTMENTS (APPOINTMENT_ID, USER_ID, SPECIALIST_ID, SPECIALITY_ID, PLAN_ID, APPOINTMENT_DATE, APPOINTMENT_DURATION, TOTAL_PRICE, STATUS, LAST_CHANGE_DATE) " +
                "VALUES (SEQ_APPOI.nextval, :userId, :specialistId, :specialityId, :planId, TO_TIMESTAMP(:appointmentDate, 'dd/mm/yyyy hh24:mi:ss'), :appointmentDuration, :totalPrice, :status, SYSDATE)";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(";");

                MapSqlParameterSource parameters = new MapSqlParameterSource();
                parameters.addValue("userId", Long.parseLong(values[0]), Types.NUMERIC);
                parameters.addValue("specialistId", Long.parseLong(values[1]), Types.NUMERIC);
                parameters.addValue("specialityId", Long.parseLong(values[2]), Types.NUMERIC);
                parameters.addValue("planId", Long.parseLong(values[3]), Types.NUMERIC);
                parameters.addValue("appointmentDate", values[4], Types.VARCHAR);
                parameters.addValue("appointmentDuration", Integer.parseInt(values[5]), Types.NUMERIC);
                parameters.addValue("totalPrice", Double.parseDouble(values[6]), Types.NUMERIC);
                parameters.addValue("status", values[7], Types.VARCHAR);

                namedParameterJdbcTemplate.update(sql, parameters);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
