package cristobal.ruiz.formulario_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.content.ContentValues
import android.content.Intent
import android.widget.CheckBox

class GetActivity : AppCompatActivity() {


    var txtNombre: EditText? = null
    var txtApellido: EditText? = null
    var txtRUT: EditText? = null
    var txtEdad: EditText? = null
    var txtFono: EditText? = null
    var txtFechaNacimiento: EditText? = null
    var chHijos: CheckBox? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get)


  /*      txtNombre = findViewById(R.id.txtNombre)
        txtApellido = findViewById(R.id.txtApellido)
        txtRUT = findViewById(R.id.tvGet)
        txtEdad = findViewById(R.id.txtEdad)
        txtFono = findViewById(R.id.txtFono)
        txtFechaNacimiento = findViewById(R.id.txtFechaNacimiento)
        txtHijos = findViewById(R.id.txtHijos)
*/

        val regresar = findViewById<Button>(R.id.btnRegresar2)

        regresar.setOnClickListener{
            finish()
        }

        val tvGet = findViewById<TextView>(R.id.`tvGet`)

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

            // Obtener el valor "Si" o "No" basado en tieneHijos
            val tieneHijosTexto = if (tieneHijos) "Si" else "No"

            // Construir un mensaje con los datos recibidos
            val mensaje = "Nombre: $nombre\nApellido: $apellido\nFecha de Nacimiento: $fechaNacimiento\nRUT: $rut\nTiene hijos: $tieneHijosTexto\nEdad: $edad\nTeléfono: $telefono"

            // Mostrar el mensaje en el TextView
            tvGet.text = mensaje
        }
    }

    fun ObtenerDatos(view: View) {
        // Obtén los valores del Bundle (suponiendo que estos valores provienen de otra actividad)
        val extras = intent.extras
        if (extras != null) {
            val nombre = extras.getString("nombre")
            val apellido = extras.getString("apellido")
            val fechaNacimiento = extras.getString("fechaNacimiento")
            val rut = extras.getString("rut")
            val tieneHijos = extras.getBoolean("tieneHijos")
            val edad = extras.getString("edad")
            val telefono = extras.getString("telefono")

            // Obtener el valor "Si" o "No" basado en tieneHijos
            val tieneHijosTexto = if (tieneHijos) "Si" else "No"

            // Llama a la función para insertar datos en la base de datos SQLite
            insertarDatosEnBaseDeDatos(nombre, apellido, fechaNacimiento, rut, tieneHijosTexto, edad, telefono)
        } else {
            // Maneja el caso en el que no se obtuvieron datos del Bundle
            Toast.makeText(this, "No se obtuvieron datos", Toast.LENGTH_SHORT).show()
        }
    }



   private fun insertarDatosEnBaseDeDatos (nombre: String?, apellido: String?, fechaNacimiento: String?, rut: String?, tieneHijos: String?, edad: String?, telefono: String?){
        var con = SQLite(this, "RegistroUsuario", null, 1)
        var BaseDatos = con.writableDatabase
//        //parametros
//        var Nombre=txtNombre?.text.toString()
//        var Apellido=txtApellido?.text.toString()
//        var RUT=txtRUT?.text.toString()
//        var Edad=txtEdad?.text.toString()
//        var Fono=txtFono?.text.toString()
//        var FechaNacimiento=txtFechaNacimiento?.text.toString()
//        val tieneHijos = if (chHijos?.isChecked == true) 1 else 0


        if(nombre?.isEmpty()==false && apellido?.isEmpty()==false && rut?.isEmpty()==false
            && edad?.isEmpty()==false && telefono?.isEmpty()==false && fechaNacimiento?.isEmpty()==false){
            var registro = ContentValues()
            registro.put("USU_RUT",rut)
            registro.put("USU_NOMBRE",nombre)
            registro.put("USU_APELLIDO",apellido)
            registro.put("USU_EDAD",edad)
            registro.put("USU_TELEFONO",telefono)
            registro.put("USU_FECHANACIMIENTO",fechaNacimiento)
            registro.put("USU_HIJOS",tieneHijos)
            BaseDatos.insert("Usuario", null, registro)



            // Después de guardar los datos correctamente en GetActivity
            val intent = Intent()
            setResult(RESULT_OK, intent)
            finish()


            Toast.makeText(this,"Usuario Ingresado Correctamente", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"Debe completar todos los campos", Toast.LENGTH_LONG).show()

        }

    }



}