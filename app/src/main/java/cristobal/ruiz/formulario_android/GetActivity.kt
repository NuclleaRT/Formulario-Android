package cristobal.ruiz.formulario_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class GetActivity : AppCompatActivity() {


    var txtNombre: EditText? = null
    var txtApellido: EditText? = null
    var txtRUT: EditText? = null
    var txtEdad: EditText? = null
    var txtFono: EditText? = null
    var txtFechaNacimiento: EditText? = null
    var txtHijos: EditText? = null


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



   /* fun insertarDatos (view: View){
        var con = SQLite(this, "RegistroUsuario", null, 1)
        var BaseDatos = con.writableDatabase
        //parametros
        var Nombre=txtNombre?.text.toString()
        var Apellido=txtApellido?.text.toString()
        var RUT=txtRUT?.text.toString()
        var Edad=txtEdad?.text.toString()
        var Fono=txtFono?.text.toString()
        var FechaNacimiento=txtFechaNacimiento?.text.toString()
        val tieneHijos = if (chHijos?.isChecked == true) 1 else 0


        if(Nombre.isEmpty()==false && Apellido.isEmpty()==false && RUT.isEmpty()==false
            && Edad.isEmpty()==false && Fono.isEmpty()==false && FechaNacimiento.isEmpty()==false){
            var registro = ContentValues()
            registro.put("USU_RUT",RUT)
            registro.put("USU_NOMBRE",Nombre)
            registro.put("USU_APELLIDO",Apellido)
            registro.put("USU_EDAD",Edad)
            registro.put("USU_TELEFONO",Fono)
            registro.put("USU_FECHANACIMIENTO",FechaNacimiento)
            registro.put("USU_HIJOS",tieneHijos)
            BaseDatos.insert("Usuario", null, registro)


            //Vaciar Campos
//            txtNombre?.text?.clear()
//            txtApellido?.text?.clear()
//            txtFechaNacimiento?.text?.clear()
//            txtRUT?.text?.clear()
//            chHijos?.isChecked = false
//            txtEdad?.text?.clear()
//            txtFono?.text?.clear()
            Toast.makeText(this,"Usuario Ingresado Correctamente", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"Ingrese los valores Correspondientes", Toast.LENGTH_LONG).show()

        }

    }*/

}