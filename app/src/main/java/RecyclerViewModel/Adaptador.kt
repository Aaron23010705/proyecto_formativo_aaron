package RecyclerViewHelper

import Aaron.Garcia.proyecto_formativo_aaron_2b.R
import Aaron.Garcia.proyecto_formativo_aaron_2b.agregarPaciente
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import Modelo.ClaseConexion
import Modelo.tbPacientes
import android.content.Intent


class Adaptador(private var Datos: List<tbPacientes>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_item_card, parent, false)


        return ViewHolder(vista)
    }

    override fun getItemCount() = Datos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val paciente = Datos[position]
        holder.CardPacientes.text = paciente.nombres


        holder.itemView.setOnClickListener {

            val context = holder.itemView.context

            //Cambiar de pantalla a la pantalla de detalle

            val pantallaDetalle = Intent(context, agregarPaciente::class.java)
            //Enviar a la otra pantalla todos mis valores
            pantallaDetalle.putExtra("UUID_pacientes", paciente.uuid)
            pantallaDetalle.putExtra("Nombres_paciente",paciente.nombres)
            pantallaDetalle.putExtra("Apellidos_paciente", paciente.apellidos)
            pantallaDetalle.putExtra("Edad", paciente.edad)
            pantallaDetalle.putExtra("Enfermedad", paciente.enfermedad)
            pantallaDetalle.putExtra("numero_habitacion", paciente.habitacion)
            pantallaDetalle.putExtra("numero_cama", paciente.cama)
            pantallaDetalle.putExtra("fecha_ingreso", paciente.fecha)


            context.startActivity(pantallaDetalle)
        }

    }

    fun ActualizarLista(nuevoLista: List<tbPacientes>) {
        Datos = nuevoLista
        notifyDataSetChanged()//Esto mnotifica al RecyclerView que hay datos nuevos
    }




}
