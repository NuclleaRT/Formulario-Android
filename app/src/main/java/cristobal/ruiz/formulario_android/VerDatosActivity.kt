package cristobal.ruiz.formulario_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class VerDatosActivity : AppCompatActivity() {

    var txtNombre: EditText? = null
    var txtApellido: EditText? = null
    var txtRUT: EditText? = null
    var txtEdad: EditText? = null
    var txtFono: EditText? = null
    var txtFechaNacimiento: EditText? = null
    var txtHijos: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_datos)

       /* txtNombre = findViewById(R.id.txtNombre)
        txtApellido = findViewById(R.id.txtApellido)
        txtRUT = findViewById(R.id.txtRUT)
        txtEdad = findViewById(R.id.txtEdad)
        txtFono = findViewById(R.id.txtFono)
        txtFechaNacimiento = findViewById(R.id.txtFechaNacimiento)
        txtHijos = findViewById(R.id.txtHijos)
        */

    }
    //Buscar usuario
  /*  fun buscar(view: View) {
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
    }*/

  /*  fun Eliminar(view: View) {
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
    }*/

}