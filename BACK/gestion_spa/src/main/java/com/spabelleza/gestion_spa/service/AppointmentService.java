package com.spabelleza.gestion_spa.service;

import com.spabelleza.gestion_spa.DTO.AppointmentDTO;
import com.spabelleza.gestion_spa.DTO.TYRC_APPOI;
import com.spabelleza.gestion_spa.entity.Appointment;
import com.spabelleza.gestion_spa.repository.AppointmentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnType;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

@Service
public class AppointmentService implements IAppointmentService{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AppointmentService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TYRC_APPOI> getAppointmentAgenda(AppointmentDTO appointmentDTO) {
        try{
            Timestamp appointmentDate = null;
            Timestamp startDate = null;
            Timestamp endDate = null;

            Long specialistId = appointmentDTO.getSpecialistId();
            //LocalDateTime appointmentDate = request.getAppointmentDate();
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

            System.out.println(inParams);

            Map<String, Object> result = jdbcCall.execute(inParams);
            return (List<TYRC_APPOI>) result.get("orc");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            // Loggear el error completo
            //log.error("Error al ejecutar el procedimiento almacenado: {}", e.getMessage(), e);
            throw e;
        }
    }

    private static TYRC_APPOI getTyrcAppoi(Struct struct) throws SQLException {
        Object[] attributes = struct.getAttributes();
        TYRC_APPOI tyrc_appoi = new TYRC_APPOI();
        tyrc_appoi.setSpecialistName((String) attributes[0]);
        tyrc_appoi.setUserName((String) attributes[1]);
        tyrc_appoi.setMembershipType((String) attributes[2]);
        tyrc_appoi.setPlanType((String) attributes[3]);
        tyrc_appoi.setSpecialityName((String) attributes[4]);
        Timestamp timestampFromDB = (Timestamp) attributes[5];
        TimeZone timeZoneFromDB = TimeZone.getTimeZone("America/Bogota"); // Cambia a tu zona horaria

        // Aplica la zona horaria especificada al Timestamp
        timestampFromDB.setTime(timestampFromDB.getTime() + timeZoneFromDB.getRawOffset());

        tyrc_appoi.setAppointmentDate(timestampFromDB);
        tyrc_appoi.setTotalPrice((BigDecimal) attributes[6]);
        tyrc_appoi.setStatus((String) attributes[7]);
        return tyrc_appoi;
    }


    public List<Appointment> getAppointmentsByCriteria(int specialistId, long userId, int planId, LocalDate startDate, LocalDate endDate) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM Appointments WHERE specialist_id = ?");
        List<Object> params = new ArrayList<>();
        params.add(specialistId);

        if (userId != 0) {
            sqlBuilder.append(" AND user_id = ?");
            params.add(userId);
        }
        if (planId != 0) {
            sqlBuilder.append(" AND plan_id = ?");
            params.add(planId);
        }
        if (startDate != null && endDate != null) {
            sqlBuilder.append(" AND appointment_date BETWEEN ? AND ?");
            params.add(startDate);
            params.add(endDate);
        }

        String sql = sqlBuilder.toString();
        return jdbcTemplate.query(sql, params.toArray(), new AppointmentRowMapper());
    }

    public List<Appointment> getAppointmentsForUserInFuture(long userId) {
        String sql = "SELECT * FROM APPOINTMENTS WHERE USER_ID = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, new AppointmentRowMapper());
    }
    /*@PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Date> getDatesWithoutAvailableSpecialists(Speciality speciality, Plan plan) {
        LocalDateTime currentDate = LocalDateTime.now();
        Date startDate = (Date) Date.from(currentDate.atZone(ZoneId.systemDefault()).toInstant());

        TypedQuery<Date> query = (TypedQuery<Date>) entityManager.createQuery(
                "SELECT DISTINCT a.appointmentDate FROM Appointment a " +
                        "WHERE a.specialist.speciality = :speciality " +
                        "AND a.specialist.plan = :plan " +
                        "AND a.appointmentDate >= :startDate");

        query.setParameter("speciality", speciality);
        query.setParameter("plan", plan);
        query.setParameter("startDate", startDate);

         // Generar una lista de fechas desde la fecha actual hasta una fecha futura (por ejemplo, 30 días después).
        List<Date> allDates = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            allDates.add(Date.from(currentDate.plusDays(i).atZone(ZoneId.systemDefault()).toInstant()));
        }

        // Obtener las fechas sin especialistas disponibles
        allDates.removeAll(busyDates);

        return query.getResultList();
    }*/
}
