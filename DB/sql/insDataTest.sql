insert into ROLES values (SEQ_ROL.NEXTVAL, 'Usuario', 'Clientes de Spa Belleza');
insert into ROLES values (SEQ_ROL.NEXTVAL, 'Administrador', 'Administrador del Spa Belleza');

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


insert into LOGIN values (SEQ_LOGIN.NEXTVAL, 'USUARIO1', 'contra123', 1, systimestamp);
insert into LOGIN values (SEQ_LOGIN.NEXTVAL, 'Admin1', 'admin123', 2, systimestamp);

