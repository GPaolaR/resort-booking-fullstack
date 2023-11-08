insert into ROLES values (SEQ_ROL.NEXTVAL, 'Usuario', 'Clientes de Spa Belleza');
insert into ROLES values (SEQ_ROL.NEXTVAL, 'Administrador', 'Administrador del Spa Belleza');
--insert into ROLES values (SEQ_ROL.NEXTVAL, 'Especialista', 'Especialista del Spa Belleza');


insert into DOCUMENT_TYPES values(SEQ_DOCTY.NEXTVAL,'CC', 'Cédula de ciudadanía');
--insert into DOCUMENT_TYPES values(SEQ_DOCTY.NEXTVAL,'TI', 'Tarjeta de identidad');
--insert into DOCUMENT_TYPES values(SEQ_DOCTY.NEXTVAL,'NI', 'NIT');

insert into membership_types values(seq_memty.nextval, 'No aplica', 0 );
insert into membership_types values(seq_memty.nextval, 'Platino', 0.25 );
insert into membership_types values(seq_memty.nextval, 'Oro', 0.2 );
insert into membership_types values(seq_memty.nextval, 'Plata', 0.1 );


insert into plans values(seq_plan.nextval, 'Spa para parejas', 'Relajate y renueva cuerpo y mente, reconectandote con tu persona especial.', 3150000, 0);
insert into plans values(seq_plan.nextval, 'Spa para empresas', 'Promueve la salud y el bienestar para tus empleados y colaboradores, estimula un ambiente de relajación y salud mental.', 230000, 1); -- xpersona
insert into plans values(seq_plan.nextval, 'Masajes de relajación', 'Masaje con aceites naturales para equilibrar energias, relajarte y aliviar tensiones.', 250000, 1);
insert into plans values(seq_plan.nextval, 'Tratamientos faciales', 'Hidrata y refresca, refina los poros abiertos, reduce líneas de expresion y aclara la piel dejándola clara y limpia.', 215000, 1);

insert into specialities values(seq_SPETY.nextval, 'Facial', systimestamp);
insert into specialities values(seq_SPETY.nextval, 'Corporal', systimestamp);
insert into specialities values(seq_SPETY.nextval, 'Uñas', systimestamp);

insert into specialists values(seq_splts.nextval,'Carlos', 'Flores', 1, '10000001', '3000000002', 'carlos.flores@spabelleza.com', 2, TO_TIMESTAMP('8:00','hh24:mi'), TO_TIMESTAMP('17:00','hh24:mi'), systimestamp);
insert into specialists values(seq_splts.nextval,'Juana', 'Martinez', 1, '10000003', '3000000004', 'juana.martinez@spabelleza.com', 3, TO_TIMESTAMP('8:00','hh24:mi'), TO_TIMESTAMP('17:00','hh24:mi'), systimestamp);


insert into LOGIN values (SEQ_LOGIN.NEXTVAL, 'USUARIO1', 'contra123', 1, systimestamp);
insert into LOGIN values (SEQ_LOGIN.NEXTVAL, 'Admin1', 'admin123', 2, systimestamp);

insert into administrators values (SEQ_ADMIN.NEXTVAL, 2, 'Juan','Perez', 1, '1000000002', '3000000003', 'AdminJP@spabelleza.com', systimestamp);

insert into users values (SEQ_USER.NEXTVAL, 1, 'Carolina','Jimenez', 1, '1000000001', 32, 'F', '3000000001', 'carolina.jimenez@example.com', 4, systimestamp);

insert into appointments values (SEQ_APPOI.NEXTVAL, 1, 1, 2, 4, TO_TIMESTAMP('07/10/2023 10:00', 'dd/mm/yyyy hh24:mi:ss'), 2, 200000,'Cumplida', systimestamp);




