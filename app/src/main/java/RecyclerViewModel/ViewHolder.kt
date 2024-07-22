package RecyclerViewHelper

import Aaron.Garcia.proyecto_formativo_aaron_2b.R
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val txtPacientesCard = view.findViewById<TextView>(R.id.txtPacientesCard)
}
