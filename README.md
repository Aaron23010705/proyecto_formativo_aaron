Create table tbPacientes (
UUID_pacientes varchar2(50) primary key not null,
nombres_paciente varchar2(50) not null,
apellidos_paciente varchar2(50) not null,
edad int not null,
enfermedad varchar2(50) not null,
numero_habitacion int not null,
numero_cama int not null,
fecha_ingreso varchar2 (20) not null
);

Create table tbUsuarios (
UUID_usuario varchar2(50) primary key not null,
nombre_usario varchar2(50)not null,
correo_usuario varchar2(50) not null,
contra_usuario varchar2(50) not null
);

Create table tbMedicamentos (
UUID_Medicamento varchar2(50) primary key not null,
nombre_medicamento varchar2(50) not null);


Insert into tbMedicamentos (UUID_Medicamento, nombre_medicamento) values (SYS_GUID(), 'Prueba');


insert into tbUsuarios (uuid_usuario, nombre_usario, correo_usuario, contra_usuario) values (SYS_GUID(), 'hola', 'hola@gmail.com', 'h3ola');
select * from tbusuarios;

drop table tbPacientes:

Insert into tbPacientes (uuid_pacientes, nombres_paciente, apellidos_paciente,edad, enfermedad, numero_habitacion, numero_cama, fecha_ingreso) values (SYS_GUID(), 'Aarón Edgardo', 'García Romero', 17, 'Diabetes', 1, 1, '23-01-07');


select * from tbPacientes;
Select * from tbMedicamentos;
nombre_usario varchar2(50)not null,
correo_usuario varchar2(50) not null,
contra_usuario varchar2(50) not null
);


insert into tbUsuarios (uuid_usuario, nombre_usario, correo_usuario, contra_usuario) values (SYS_GUID(), 'hola', 'hola@gmail.com', 'h3ola');
select * from tbusuarios
