package Aaron.Garcia.proyecto_formativo_aaron_2b

import Modelo.ClaseConexion
import Modelo.tbPacientes
import RecyclerViewHelper.Adaptador
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [verPacientes.newInstance] factory method to
 * create an instance of this fragment.
 */
class verPacientes : Fragment() {
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
       val root =  inflater.inflate(R.layout.fragment_ver_pacientes, container, false)


        val rcvPacientes = root.findViewById<RecyclerView>(R.id.rcvPacientes)

        rcvPacientes.layoutManager = LinearLayoutManager(context)

        fun obtenerPacientes(): List <tbPacientes> {

            val objConexion = ClaseConexion().cadenaConexion()

            val statement = objConexion?.createStatement()
            val resulset = statement?.executeQuery("Select * from tbPacientes")

            val listaPacientes = mutableListOf<tbPacientes>()

            while ((resulset!!.next())) {
                val uuid = resulset.getString("UUID_pacientes")
                val nombres = resulset.getString("nombres_paciente")
                val apellidos = resulset.getString("apellidos_paciente")
                val edad = resulset.getInt("edad")
                val enfermedad = resulset.getString("enfermedad")
                val habitacion = resulset.getInt("numero_habitacion")
                val cama = resulset.getInt("numero_cama")
                val fecha = resulset.getString("fecha_ingreso")

                val valoresJuntos = tbPacientes(uuid,nombres,apellidos,edad,enfermedad,habitacion,cama,fecha)

listaPacientes.add(valoresJuntos)
            }


            return listaPacientes

        }


        CoroutineScope(Dispatchers.IO).launch {
            val tbPacientes = obtenerPacientes()
            withContext(Dispatchers.Main) {
                val adapter = Adaptador(tbPacientes)
                rcvPacientes.adapter = adapter
            }
            val nuevoPaciente = obtenerPacientes()
            withContext(Dispatchers.Main) {
                (rcvPacientes.adapter as? Adaptador)?.ActualizarLista(nuevoPaciente)

            }

        }
        return root;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *p¿¿
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment verPacientes.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            verPacientes().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}