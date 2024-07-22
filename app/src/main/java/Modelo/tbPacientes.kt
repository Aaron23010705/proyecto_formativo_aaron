package Modelo

data class tbPacientes(
    val uuid: String,
    var nombres: String,
    var apellidos : String,
    var edad: Int,
    var enfermedad: String,
    var habitacion : Int,
    var cama: Int,
    var fecha : String
)
