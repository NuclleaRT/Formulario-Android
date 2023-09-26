package cristobal.ruiz.formulario_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

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


        txtNombre = findViewById(R.id.txtNombre)
        txtApellido = findViewById(R.id.txtApellido)
        txtRUT = findViewById(R.id.txtRUT)
        txtEdad = findViewById(R.id.txtEdad)
        txtFono = findViewById(R.id.txtFono)
        txtFechaNacimiento = findViewById(R.id.txtFechaNacimiento)
        txtHijos = findViewById(R.id.txtHijos)


        val regresar = findViewById<ImageButton>(R.id.btnRegresar)

        regresar.setOnClickListener{
            finish()
        }
//
//        val tvGet = findViewById<TextView>(R.id.tvGet)
//
//        // Obtener los valores del Bundle
//        val extras = intent.extras
//        if (extras != null) {
//            val nombre = extras.getString("nombre")
//            val apellido = extras.getString("apellido")
//            val fechaNacimiento = extras.getString("fechaNacimiento")
//            val rut = extras.getString("rut")
//            val tieneHijos = extras.getBoolean("tieneHijos")
//            val edad = extras.getString("edad")
//            val telefono = extras.getString("telefono")
//
//            // Construir un mensaje con los datos recibidos
//            val mensaje = "Nombre: $nombre\nApellido: $apellido\nFecha de Nacimiento: $fechaNacimiento\nRUT: $rut\nTiene hijos: $tieneHijos\nEdad: $edad\nTeléfono: $telefono"
//
//            // Mostrar el mensaje en el TextView
//            tvGet.text = mensaje
//        }
    }


    fun buscar(view: View) {
        val con = SQLite(this, "RegistroUsuario", null, 1)
        var BaseDatos = con.writableDatabase
        var RUT = txtRUT?.text.toString()
        if (RUT.isEmpty() == false) {
            val fila = BaseDatos.rawQuery(
                "SELECT USU_NOMBRE," +
                        "USU_APELLIDO, " +
                        "USU_EDAD," +
                        "USU_TELEFONO," +
                        "USU_FECHANACIMIENTO, " +
                        "USU_HIJOS FROM Usuario " +
                        "WHERE USU_RUT='$RUT' ", null
            )
            if (fila.moveToFirst() == true) {
                txtNombre?.setText(fila.getString(0))
                txtApellido?.setText(fila.getString(1))
                txtEdad?.setText(fila.getString(2))
                txtFono?.setText(fila.getString(3))
                txtFechaNacimiento?.setText(fila.getString(4))
                // Obtén el valor de la columna USU_HIJOS
                val hijos = fila.getInt(5)

                // Asigna "SI" o "No" al TextView dependiendo del valor
                val hijosTexto = if (hijos == 1) "SI" else "No"
                txtHijos?.setText(hijosTexto)
                BaseDatos.close()
            } else {
                txtNombre?.setText("")
                txtApellido?.setText("")
                txtEdad?.setText("")
                txtFono?.setText("")
                txtFechaNacimiento?.setText("")
                txtHijos?.setText("")
                Toast.makeText(this, "No se encontro ningun registro", Toast.LENGTH_LONG).show()
            }
        }
    }
    fun Eliminar(view: View) {
        val con = SQLite(this, "RegistroUsuario", null, 1)
        var BaseDatos = con.writableDatabase
        var RUT = txtRUT?.text.toString()
        if (RUT.isEmpty() == false) {
            val cant = BaseDatos.delete("Usuario", "USU_RUT='" + RUT + "'", null)
            if (cant > 0) {
                Toast.makeText(this, "Usuario Eliminado Correctamente", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Usuario no fue encontrado", Toast.LENGTH_LONG).show()

            }
            txtRUT?.setText("")
            txtNombre?.setText("")
            txtApellido?.setText("")
            txtEdad?.setText("")
            txtFono?.setText("")
            txtFechaNacimiento?.setText("")
            txtHijos?.setText("")
        } else {
            Toast.makeText(this, "El campo RUT no puede estar vacio", Toast.LENGTH_LONG).show()

        }
    }
}