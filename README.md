
Create table tbMedicamentos (
UUID_Medicamento varchar2(50) primary key not null,
nombre_medicamento varchar2(50) not null);

Create table tbUsuarios (
UUID_usuario varchar2(50) primary key not null,
nombre_usario varchar2(50)not null,
correo_usuario varchar2(50) not null,
contra_usuario varchar2(50) not null
);

Create table tbPacientes (
UUID_pacientes varchar2(50) primary key not null,
nombres_paciente varchar2(50) not null,
apellidos_paciente varchar2(50) not null,
edad int not null,
enfermedad varchar2(50) not null,
numero_habitacion int not null,
numero_cama int not null,
fecha_ingreso varchar2 (20) not null,
hora_aplicacion varchar2(20) not NULL,
medicamento varchar2(50) not null,
constraint fk_medicamento
foreign key (medicamento)
references tbMedicamentos (UUID_medicamento)
);



Insert all
into tbMedicamentos (UUID_Medicamento, nombre_medicamento) values (SYS_GUID(), 'Acetaminophen')
into tbMedicamentos (UUID_Medicamento, nombre_medicamento) values (SYS_GUID(), 'Paracetamol')
into tbMedicamentos (UUID_Medicamento, nombre_medicamento) values (SYS_GUID(), 'Alegra')
into tbMedicamentos (UUID_Medicamento, nombre_medicamento) values (SYS_GUID(), 'Simvastatina ')
into tbMedicamentos (UUID_Medicamento, nombre_medicamento) values (SYS_GUID(), 'Aspirina')
select * from dual;
commit;

SELECT p.uuid_pacientes, p.nombres_paciente, p.apellidos_paciente, p.edad, p.enfermedad, p.numero_habitacion, p.numero_cama, p.fecha_ingreso, p.hora_aplicacion, m.nombre_medicamento FROM tbPacientes p INNER JOIN tbMedicamentos m ON p.medicamento = m.uuid_medicamento;



