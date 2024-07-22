package Aaron.Garcia.proyecto_formativo_aaron_2b

import Modelo.ClaseConexion
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [agregarMedicamento.newInstance] factory method to
 * create an instance of this fragment.
 */
class agregarMedicamento : Fragment() {
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
        val root =  inflater.inflate(R.layout.fragment_agregar_medicamento, container, false)

        val txtNombreMed = root.findViewById<EditText>(R.id.txtAgregarMed)
        val btnAgregarMed = root.findViewById<Button>(R.id.btnAgregarMed)

        btnAgregarMed.setOnClickListener {

            val nombreMed = txtNombreMed.text.toString()


            var hayerrores = false;

            if (nombreMed.isEmpty()) {
                txtNombreMed.error = "Porfavor ingrese un nombre"
                hayerrores = true
            } else {
                txtNombreMed.error = null;
            }
            if (hayerrores) {

            }
            else {
                CoroutineScope(Dispatchers.IO).launch {
                    val objConexion = ClaseConexion().cadenaConexion()
                    println("Estamos dentro de una corrutina y funciono el objeto conexion")
                    val nuevoMed =
                        objConexion?.prepareStatement("Insert into tbMedicamentos (UUID_Medicamento, nombre_medicamento) values (?,?)")!!
                    nuevoMed.setString(1, UUID.randomUUID().toString())
                    nuevoMed.setString(2, txtNombreMed.text.toString())

                    println("Se creo el medicamento ")
                    nuevoMed.executeUpdate()


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
         * @return A new instance of fragment agregarMedicamento.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            agregarMedicamento().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}