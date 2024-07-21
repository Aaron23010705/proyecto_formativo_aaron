Create table tbPacientes (
UUID_pacientes varchar2(50) primary key not null,
nombres_paciente varchar2(50) not null,
apellidos_paciente varchar2(50) not null,
edad int not null,
enfermedad varchar2(50) not null,
numero_habitacion int not null,
numero_cama int not null,
medicamentos varchar2 (50) not null,
fecha_ingreso varchar2 (20) not null,
hora_medicamentos char(10) not null
);

Create table tbUsuarios (
UUID_usuario varchar2(50) primary key not null,
nombre_usario varchar2(50)not null,
correo_usuario varchar2(50) not null,
contra_usuario varchar2(50) not null
);


insert into tbUsuarios (uuid_usuario, nombre_usario, correo_usuario, contra_usuario) values (SYS_GUID(), 'hola', 'hola@gmail.com', 'h3ola');
select * from tbusuarios
