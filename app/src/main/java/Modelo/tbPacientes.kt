package Modelo

data class tbPacientes(
    val uuid: String,
    val nombres: String,
    val apellidos : String,
    val edad: Int,
    val enfermedad: String,
    val habitacion : Int,
    val cama: Int,
    val fecha : String
)
