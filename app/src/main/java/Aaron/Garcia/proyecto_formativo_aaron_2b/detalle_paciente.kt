package Aaron.Garcia.proyecto_formativo_aaron_2b

import Modelo.ClaseConexion
import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class detalle_paciente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_paciente)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nombresRecibidos = intent.getStringExtra("Nombres_paciente")
        val apellidosRecibidos = intent.getStringExtra("Apellidos_paciente")
        val edadRecibida = intent.getIntExtra("Edad", 0)
        val EnfermedadRecibida = intent.getStringExtra("Enfermedad")
        val HabitacionRecibida = intent.getIntExtra("numero_habitacion", 0)
        val CamaRecibida = intent.getIntExtra("numero_habitacion", 0)
        val fecha_ingreso = intent.getStringExtra("fecha_ingreso")

        //Mando a llamar a todos los elelemntos de la pantalla
        val txtNombresDetalle = findViewById<TextView>(R.id.txtNombrePaciente)
        val txtApellidosDetalle = findViewById<TextView>(R.id.txtApellidosPaciente)
        val txtEdaddetalle = findViewById<TextView>(R.id.txtEdadPaciente)
        val txtEnfermedadDetalle = findViewById<TextView>(R.id.txtEnfermedadPaciente)
        val txtHabitacionDetalle = findViewById<TextView>(R.id.txtHabitacionPaciente)
        val txtCamaDetalle = findViewById<TextView>(R.id.txtCamaPaciente)
        val txtFecha_ingreso = findViewById<TextView>(R.id.txtFecha_paciente)
        val btnEditar = findViewById<Button>(R.id.btnEditar)

        //Asignarle los datos recibidos a mis textos
//Segundo = primero

        txtNombresDetalle.text = nombresRecibidos.toString()
        txtApellidosDetalle.text = apellidosRecibidos.toString()
        txtEdaddetalle.text = edadRecibida.toString()
        txtEnfermedadDetalle.text = EnfermedadRecibida.toString()
        txtHabitacionDetalle.text = HabitacionRecibida.toString()
        txtCamaDetalle.text = CamaRecibida.toString()
        txtFecha_ingreso.text = fecha_ingreso.toString()


        fun uodate(
            nombresnuevos: String,
            apellidosNuevos: String,
            EdadNueva: String,
            EnfermedadNueva: String,
            HabitacionNueva: String,
            CamaNueva: String,
            fechaNueva: String, ) {
            GlobalScope.launch(Dispatchers.IO) {

                ///1 - creo un objeto de la clase conexion
                val objConexion = ClaseConexion().cadenaConexion()

                //2 - Creo una variable que tenga un prepareStatement
                val actualizarPaciente =
                    objConexion?.prepareStatement(
                        "UPDATE tbveterinaria set nombre_veterinaria = ?, ubicacion_veterinaria = ?, nit = ?, contacto_veterinaria = ?, correo_veterinaria = ?, descripcion_servicio = ? where correo_veterinaria = ?"
                    )!!
                actualizarPaciente.setString(1, nombresnuevos)
                actualizarPaciente.setString(2, apellidosNuevos)
                actualizarPaciente.setString(3, EdadNueva)
                actualizarPaciente.setString(4, EnfermedadNueva)
                actualizarPaciente.setString(5, HabitacionNueva)
                actualizarPaciente.setString(6, CamaNueva)
                actualizarPaciente.setString(7, fechaNueva)
                actualizarPaciente.executeUpdate()
            }
        }


        fun isValid(vararg editTexts: EditText): Boolean {
            for (editText in editTexts) {
                if (editText.text.toString().isEmpty()) {
                    Toast.makeText(this, "Porfavor llene todos los datos", Toast.LENGTH_SHORT)
                        .show()
                    return false
                }
            }
            return true
        }


        btnEditar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Editar")
            builder.setMessage("Estas seguro que quieres editar?")

            val nombrenuevo = EditText(this)
            nombrenuevo.setHint("Nombres")

            val apellidonuevo = EditText(this)
            apellidonuevo.setHint("Apellidos")

            val edad = EditText(this)
            edad.setHint("Edad")
            edad.inputType = InputType.TYPE_CLASS_NUMBER //

            val enfermedad = EditText(this)
            enfermedad.setHint("enfermedad")

            val habitacion = EditText(this)
            habitacion.setHint("N° Habitación")

            val cama = EditText(this)
            cama.setHint("N° Cama")

            val fecha = EditText(this)
            fecha.setHint("Fecha_ingreso")

            val layout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                addView(nombrenuevo)
                addView(apellidonuevo)
                addView(edad)
                addView(enfermedad)
                addView(habitacion)
                addView(cama)
                addView(fecha)

            }

            builder.setView(layout)

            builder.setPositiveButton("Si") { dialog, which ->
                if (isValid(
                        nombrenuevo,
                        apellidonuevo,
                        edad,
                        enfermedad,
                        habitacion,
                        cama,
                        fecha
                    )
                ) {
                    uodate(
                        nombrenuevo.text.toString(),
                        apellidonuevo.text.toString(),
                        edad.text.toString(),
                        enfermedad.text.toString(),
                        habitacion.text.toString(),
                        cama.text.toString(),
                        fecha.text.toString()
                    )
                    Toast.makeText(this, "Datos actualizados", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                    txtNombresDetalle.text = nombrenuevo.text.toString()
                    txtApellidosDetalle.text = apellidonuevo.text.toString()
                    txtEdaddetalle.text = edad.text.toString()
                    txtEnfermedadDetalle.text = enfermedad.text.toString()
                    txtHabitacionDetalle.text = habitacion.text.toString()
                    txtCamaDetalle.text = cama.text.toString()
                    txtFecha_ingreso.text = fecha.text.toString()

                }
                builder.setNegativeButton("no") { dialog, which ->
                    dialog.dismiss()
                }
                builder.show()
            }


        }
    }
}