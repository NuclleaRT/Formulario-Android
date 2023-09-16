package cristobal.ruiz.formulario_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Enviar = findViewById<Button>(R.id.btnEnviar)

        Enviar.setOnClickListener{
            val nombre = findViewById<EditText>(R.id.txtNombre).text.toString()
            val apellido = findViewById<EditText>(R.id.txtApellido).text.toString()
            val fechaNacimiento = findViewById<EditText>(R.id.txtFechaNacimiento).text.toString()
            val rut = findViewById<EditText>(R.id.txtRUT).text.toString()
            val tieneHijos = findViewById<CheckBox>(R.id.chHijos).isChecked
            val edad = findViewById<EditText>(R.id.txtEdad).text.toString()
            val telefono = findViewById<EditText>(R.id.txtFono).text.toString()

            val bundle = Bundle()
            bundle.putString("nombre", nombre)
            bundle.putString("apellido", apellido)
            bundle.putString("fechaNacimiento", fechaNacimiento)
            bundle.putString("rut", rut)
            bundle.putBoolean("tieneHijos", tieneHijos)
            bundle.putString("edad",edad)
            bundle.putString("telefono",telefono)

            val intent = Intent(this,GetActivity::class.java)
            intent.putExtras(bundle)

            startActivity(intent)
        }

    }
}