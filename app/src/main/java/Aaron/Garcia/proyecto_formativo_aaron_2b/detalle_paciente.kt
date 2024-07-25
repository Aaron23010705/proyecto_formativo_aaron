package Aaron.Garcia.proyecto_formativo_aaron_2b

import Modelo.ClaseConexion
import RecyclerViewHelper.Adaptador
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Calendar

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
        val hora_recibida  = intent.getStringExtra("hora_aplicacion")
        var uuid_med_recibida =  intent.getStringExtra("medicamento")







        //Mando a llamar a todos los elelemntos de la pantalla
        val txtNombresDetalle = findViewById<TextView>(R.id.txtNombrePaciente)
        val txtApellidosDetalle = findViewById<TextView>(R.id.txtApellidosPaciente)
        val txtEdaddetalle = findViewById<TextView>(R.id.txtEdadPaciente)
        val txtEnfermedadDetalle = findViewById<TextView>(R.id.txtEnfermedadPaciente)
        val txtHabitacionDetalle = findViewById<TextView>(R.id.txtHabitacionPaciente)
        val txtCamaDetalle = findViewById<TextView>(R.id.txtCamaPaciente)
        val txtFecha_ingreso = findViewById<TextView>(R.id.txtFecha_paciente)
        val txtHoraMed = findViewById<TextView>(R.id.txtHoraMedDetalle)
        val txtMed = findViewById<TextView>(R.id.txtMedDetalle)
        val btnVolver = findViewById<ImageView>(R.id.btnVolverVer)
        val btnEditar = findViewById<Button>(R.id.btnEditar)




         //Asignarle los datos recibidos a mis textos



        txtNombresDetalle.text = nombresRecibidos.toString()
        txtApellidosDetalle.text = apellidosRecibidos.toString()
        txtEdaddetalle.text = edadRecibida.toString()
        txtEnfermedadDetalle.text = EnfermedadRecibida.toString()
        txtHabitacionDetalle.text = HabitacionRecibida.toString()
        txtCamaDetalle.text = CamaRecibida.toString()
        txtFecha_ingreso.text = fecha_ingreso.toString()
        txtHoraMed.text = hora_recibida.toString()
        txtMed.text = uuid_med_recibida.toString()




        fun uodate(nombresnuevos: String, apellidosNuevos: String, EdadNueva: String, EnfermedadNueva: String, HabitacionNueva: String, CamaNueva: String, fechaNueva: String, hora_nueva:String ) { GlobalScope.launch(Dispatchers.IO) {

                ///1 - creo un objeto de la clase conexion
                val objConexion = ClaseConexion().cadenaConexion()

                //2 - Creo una variable que tenga un prepareStatement
                val actualizarPaciente =
                    objConexion?.prepareStatement(
                        "Update tbPacientes set nombres_paciente = ?, apellidos_paciente = ?, edad = ?, enfermedad = ?, numero_habitacion = ?, numero_cama = ?, fecha_ingreso = ?, hora_aplicacion = ?  where nombres_paciente = ?")!!
                actualizarPaciente.setString(1, nombresnuevos)
                actualizarPaciente.setString(2, apellidosNuevos)
                actualizarPaciente.setString(3, EdadNueva)
                actualizarPaciente.setString(4, EnfermedadNueva)
                actualizarPaciente.setString(5, HabitacionNueva)
                actualizarPaciente.setString(6, CamaNueva)
                actualizarPaciente.setString(7, fechaNueva)
            actualizarPaciente.setString(8, hora_nueva)
            actualizarPaciente.setString(9, nombresRecibidos)
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



        btnVolver.setOnClickListener {
            val volverVer = Intent(this, verPacientes::class.java)
            startActivity(volverVer)
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
            habitacion.inputType = InputType.TYPE_CLASS_NUMBER

            val cama = EditText(this)
            cama.setHint("N° Cama")
            cama.inputType = InputType.TYPE_CLASS_NUMBER

            val fecha = EditText(this)
            fecha.setHint("Fecha_ingreso")

            val hora = EditText(this)
            hora.setHint("hora_aplicacion")



            val layout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                addView(nombrenuevo)
                addView(apellidonuevo)
                addView(edad)
                addView(enfermedad)
                addView(habitacion)
                addView(cama)
                addView(fecha)
                addView(hora)

            }
            builder.setView(layout)

            builder.setPositiveButton("Si") { dialog, which ->
                if (isValid(nombrenuevo, apellidonuevo, edad, enfermedad, habitacion, cama, fecha,hora)
                ) { uodate(nombrenuevo.text.toString(), apellidonuevo.text.toString(), edad.text.toString(), enfermedad.text.toString(), habitacion.text.toString(), cama.text.toString(), fecha.text.toString(), hora.text.toString())
                    Toast.makeText(this, "Datos actualizados", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                    txtNombresDetalle.text = nombrenuevo.text.toString()
                    txtApellidosDetalle.text = apellidonuevo.text.toString()
                    txtEdaddetalle.text = edad.text.toString()
                    txtEnfermedadDetalle.text = enfermedad.text.toString()
                    txtHabitacionDetalle.text = habitacion.text.toString()
                    txtCamaDetalle.text = cama.text.toString()
                    txtFecha_ingreso.text = fecha.text.toString()
                    txtHoraMed.text = hora.text.toString()

                }
                builder.setNegativeButton("no") { dialog, which ->
                    dialog.dismiss()
                }

            }

builder.show()


        }
    }
}