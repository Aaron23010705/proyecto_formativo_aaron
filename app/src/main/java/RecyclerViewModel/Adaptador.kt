package RecyclerViewHelper

import Aaron.Garcia.proyecto_formativo_aaron_2b.R
import Aaron.Garcia.proyecto_formativo_aaron_2b.agregarPaciente
import Aaron.Garcia.proyecto_formativo_aaron_2b.detalle_paciente
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import Modelo.ClaseConexion
import Modelo.tbPacientes
import android.app.AlertDialog
import android.content.Intent


class Adaptador(private var Datos: List<tbPacientes>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_item_card, parent, false)


        return ViewHolder(vista)
    }

    fun actualicePantalla(uuid: String, nuevoNombre: String) {
        val index = Datos.indexOfFirst { it.uuid == uuid }
        Datos[index].nombres = nuevoNombre
        notifyDataSetChanged()
    }

    fun ActualizarLista(nuevoLista: List<tbPacientes>) {
        Datos = nuevoLista
        notifyDataSetChanged()//Esto mnotifica al RecyclerView que hay datos nuevos
    }

    fun eliminarDatos(NombrePaciente: String, posicion: Int) {
        //Actualizo la lista de datos y notifico al adaptador
        val listaDatos = Datos.toMutableList()
        listaDatos.removeAt(posicion)
        GlobalScope.launch(Dispatchers.IO) {
            // creamos un objeto de la clase conexion

            val objConexion = ClaseConexion().cadenaConexion()

            // 2- Crear una variable que contenga un preparestatement (donde se mete el código de sqlserver
            val borrarPaciente =
                objConexion?.prepareStatement("delete from tbPacientes where nombres_paciente = ?")!!
            borrarPaciente.setString(1, NombrePaciente)
            borrarPaciente.executeUpdate()

            val commit = objConexion.prepareStatement("commit")!!
            commit.executeUpdate()
        }
        Datos = listaDatos.toList()
        //Notificar el adaptador sobre los cambios
        notifyItemRemoved(posicion)
        notifyDataSetChanged()
    }


    override fun getItemCount() = Datos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val paciente = Datos[position]
        holder.CardPacientes.text = paciente.nombres

        holder.imgBorrar.setOnClickListener {
            val context = holder.itemView.context

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Elimnar")
            builder.setMessage("¿Está seguro que quiere eliminar la mascota?")

            //Botones
            builder.setPositiveButton("Si") { dialog, which ->
                eliminarDatos(paciente.nombres, position)
            }
            builder.setNegativeButton("no") { dialog, which ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()

        }

        holder.itemView.setOnClickListener {

            val context = holder.itemView.context

            //Cambiar de pantalla a la pantalla de detalle

             val pantallaDetalle = Intent(context, detalle_paciente::class.java)
            //Enviar a la otra pantalla todos mis valores
            pantallaDetalle.putExtra("UUID_pacientes", paciente.uuid)
            pantallaDetalle.putExtra("Nombres_paciente", paciente.nombres)
            pantallaDetalle.putExtra("Apellidos_paciente", paciente.apellidos)
            pantallaDetalle.putExtra("Edad", paciente.edad)
            pantallaDetalle.putExtra("Enfermedad", paciente.enfermedad)
            pantallaDetalle.putExtra("numero_habitacion", paciente.habitacion)
            pantallaDetalle.putExtra("numero_cama", paciente.cama)
            pantallaDetalle.putExtra("fecha_ingreso", paciente.fecha)
            pantallaDetalle.putExtra("hora_aplicacion", paciente.hora_med)
            pantallaDetalle.putExtra("medicamento", paciente.med)
            context.startActivity(pantallaDetalle)

        }

    }
}

