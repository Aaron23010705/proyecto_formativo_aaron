package Aaron.Garcia.proyecto_formativo_aaron_2b

import Modelo.ClaseConexion
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

class Registro_usuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_usuario)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val txtNombre = findViewById<EditText>(R.id.txtNombre)
        val txtCorreo = findViewById<EditText>(R.id.txtCorreo)
        val txtContra = findViewById<EditText>(R.id.txtContra)
        val btnPasar = findViewById<TextView>(R.id.btnPasarLogin)
        val btnRegistrar = findViewById<Button>(R.id.BtnRegistrar)


        btnRegistrar.setOnClickListener {

            println("Estamos dentro del setonclicklistener")
            val pasar_login = Intent(this, Login::class.java)
            CoroutineScope(Dispatchers.IO).launch {
                val objConexion = ClaseConexion().cadenaConexion()
println("Estamos dentro de una corrutina y funciono el objeto conexion")
                val nuevousuario = objConexion?.prepareStatement("insert into tbUsuarios (uuid_usuario, nombre_usario, correo_usuario, contra_usuario) values (?,?,?,?)")!!
                nuevousuario.setString(1, UUID.randomUUID().toString())
                nuevousuario.setString(2, txtNombre.text.toString())
                nuevousuario.setString(3, txtCorreo.text.toString())
                nuevousuario.setString(4, txtContra.text.toString())
                println("Se creo el usuarió")
                nuevousuario.executeUpdate()

                startActivity(pasar_login)

            }
        }

btnPasar.setOnClickListener {
    val login = Intent(this, Login::class.java)
    startActivity(login)
}

    }
}