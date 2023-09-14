package cristobal.ruiz.formulario_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class GetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get)

        val regresar = findViewById<ImageButton>(R.id.btnRegresar)

        regresar.setOnClickListener{
            finish()
        }

        val tvGet = findViewById<TextView>(R.id.tvGet)

        // Obtener los valores del Bundle
        val extras = intent.extras
        if (extras != null) {
            val nombre = extras.getString("nombre")
            val apellido = extras.getString("apellido")
            val fechaNacimiento = extras.getString("fechaNacimiento")
            val rut = extras.getString("rut")
            val tieneHijos = extras.getBoolean("tieneHijos")
            val edad = extras.getString("edad")
            val telefono = extras.getString("telefono")

            // Construir un mensaje con los datos recibidos
            val mensaje = "Nombre: $nombre\nApellido: $apellido\nFecha de Nacimiento: $fechaNacimiento\nRUT: $rut\nTiene hijos: $tieneHijos\nEdad: $edad\nTeléfono: $telefono"

            // Mostrar el mensaje en el TextView
            tvGet.text = mensaje
        }
    }
}