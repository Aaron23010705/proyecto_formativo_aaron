package Aaron.Garcia.proyecto_formativo_aaron_2b

import Modelo.ClaseConexion
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.CardPacientes)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtCorreoLogin = findViewById<EditText>(R.id.txtCorreoLogin)
        val txtContraLogin = findViewById<EditText>(R.id.txtContraLogin)
        val btnIniciar = findViewById<Button>(R.id.btnIniciar)
        val btnRegresar = findViewById<TextView>(R.id.btnRegresarRegistro)


        btnRegresar.setOnClickListener {
            val registro = Intent(this, Registro_usuario::class.java)
            startActivity(registro)
        }

        btnIniciar.setOnClickListener {

            val correoLogin = txtCorreoLogin.text.toString()
            val contraLogin = txtContraLogin.text.toString()



            var hayerrores = false;

            if (correoLogin.isEmpty()) {
                txtCorreoLogin.error = "Porfavor ingrese un correo"
                hayerrores = true
            } else {
                txtCorreoLogin.error = null;
            }

            if (contraLogin.isEmpty()) {
                txtContraLogin.error = "Porfavor ingrese una contraseña"
                hayerrores = true
            } else {
                txtContraLogin.error = null;
            }

            if (hayerrores) {

            }
            else {



            val pasar = Intent(this, MainActivity::class.java)

            CoroutineScope(Dispatchers.IO).launch {
                val objConexion = ClaseConexion().cadenaConexion()
                val VerUsuer =
                    objConexion?.prepareStatement("select * from tbUsuarios where correo_usuario = ? AND contra_usuario = ?")!!
                VerUsuer.setString(1, txtCorreoLogin.text.toString())
                VerUsuer.setString(2, txtContraLogin.text.toString())

                val usuarioVerdadero = VerUsuer.executeQuery()


                withContext(Dispatchers.Main) {
                    if (usuarioVerdadero.next()) {
                        startActivity(pasar)
                    } else {
                        Toast.makeText(
                            this@Login,
                            "Contraseña o correo incorrectos",
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                }
                }
            }
        }
    }
}