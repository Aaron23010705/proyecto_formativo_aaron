package Aaron.Garcia.proyecto_formativo_aaron_2b

import Modelo.ClaseConexion
import RecyclerViewHelper.Adaptador
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.UUID

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [agregarPaciente.newInstance] factory method to
 * create an instance of this fragment.
 */
class agregarPaciente : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      val root = inflater.inflate(R.layout.fragment_agregar_paciente, container, false)


        val txtNombres =  root.findViewById<EditText>(R.id.txtNombres)
        val txtApellidos =  root.findViewById<EditText>(R.id.txtApellidos)
        val txtEdad =  root.findViewById<EditText>(R.id.txtEdad)
        val txtEnfermedad =  root.findViewById<EditText>(R.id.txtEnfermedad)
        val txtHabitacion =  root.findViewById<EditText>(R.id.txtNhabitacion)
        val txtCama =  root.findViewById<EditText>(R.id.txtNCama)
        val txtFechaIngreso =  root.findViewById<EditText>(R.id.txtFechaIngreso)
        val btnAgregar =  root.findViewById<Button>(R.id.btnAgregarPaciente)


        txtFechaIngreso.setOnClickListener {
            val calendario = Calendar.getInstance()
            val anio = calendario.get(Calendar.YEAR)
            val mes = calendario.get(Calendar.MONTH)
            val dia = calendario.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { view, anioSeleccionado, mesSeleccionado, diaSeleccionado ->
                    val fechaSeleccionada =
                        "$diaSeleccionado/${mesSeleccionado + 1}/$anioSeleccionado"
                    txtFechaIngreso.setText(fechaSeleccionada)
                },
                anio, mes, dia
            )
            datePickerDialog.show()
        }

        btnAgregar.setOnClickListener {
            val nombres = txtNombres.text.toString()
            val apellidos = txtApellidos.text.toString()
            val edad = txtEdad.text.toString()
            val enfermedad = txtEnfermedad.text.toString()
            val habitacion = txtHabitacion.text.toString()
            val cama = txtCama.text.toString()
            val fecha = txtFechaIngreso.text.toString()


            var hayerrores = false;

            if (nombres.isEmpty()) {
                txtNombres.error = "Los nombres son obligatorios"
                hayerrores = true
            } else {
                txtNombres.error = null;
            }

            if (apellidos.isEmpty()) {
                txtApellidos.error = "Los apellidos son obligatorios"
                hayerrores = true
            } else {
                txtApellidos.error = null;
            }


            if (edad.isEmpty()) {
                txtEdad.error = "La edad es obligatoria"
                hayerrores = true
            } else {
                txtEdad.error = null;
            }

            if (enfermedad.isEmpty()) {
                txtEnfermedad.error = "La enfermedad es obligatoria"
                hayerrores = true
            } else {
                txtEnfermedad.error = null;
            }


            if (habitacion.isEmpty()) {
                txtHabitacion.error = "El número de habitación es obligatorio"
                hayerrores = true
            } else {
                txtHabitacion.error = null;
            }

            if (cama.isEmpty()) {
                txtCama.error = "el número de cama es obligatorio"
                hayerrores = true
            } else {
                txtCama.error = null;
            }

            if (fecha.isEmpty()) {
                txtFechaIngreso.error = "La fecha de ingreso es obligatoria"
                hayerrores = true
            } else {
                txtFechaIngreso.error = null;
            }

            if (hayerrores) {
                //
            } else {

                CoroutineScope(Dispatchers.IO).launch {

                    val objConexion = ClaseConexion().cadenaConexion()
                    val agregarPaciente =
                        objConexion?.prepareStatement("Insert into tbPacientes (uuid_pacientes, nombres_paciente, apellidos_paciente,edad, enfermedad, numero_habitacion, numero_cama, fecha_ingreso) values (?,?,?,?,?,?,?,?)")!!
                    agregarPaciente.setString(1, UUID.randomUUID().toString())
                    agregarPaciente.setString(2, txtNombres.text.toString())
                    agregarPaciente.setString(3, txtApellidos.text.toString())
                    agregarPaciente.setString(4, txtEdad.text.toString())
                    agregarPaciente.setString(5, txtEnfermedad.text.toString())
                    agregarPaciente.setString(6, txtHabitacion.text.toString())
                     agregarPaciente.setString(7, txtCama.text.toString())
                    agregarPaciente.setString(8, txtFechaIngreso.text.toString())

                    agregarPaciente.executeUpdate()

                }

            }
        }
        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment agregarPaciente.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            agregarPaciente().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}