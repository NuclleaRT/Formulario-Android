package cristobal.ruiz.formulario_android

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class EditarUsuarioActivity : AppCompatActivity() {

    private lateinit var etEditarNombre: EditText
    private lateinit var etEditarApellido: EditText
    private lateinit var etEditarFechaNacimiento: EditText
    private lateinit var cbEditarTieneHijos: CheckBox
    private lateinit var etEditarEdad: EditText
    private lateinit var etEditarTelefono: EditText
    private lateinit var btnGuardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_usuario)

        etEditarNombre = findViewById(R.id.etEditarNombre)
        etEditarApellido = findViewById(R.id.etEditarApellido)
        etEditarFechaNacimiento = findViewById(R.id.etEditarFechaNacimiento)
        cbEditarTieneHijos = findViewById(R.id.cbEditarTieneHijos)
        etEditarEdad = findViewById(R.id.etEditarEdad)
        etEditarTelefono = findViewById(R.id.etEditarTelefono)
        btnGuardar = findViewById(R.id.btnActualizar)

        // Recupera los datos del usuario que se va a editar desde la actividad anterior
        val extras = intent.extras
        if (extras != null) {
            val rut = extras.getString("rut")
            val nombre = extras.getString("nombre")
            val apellido = extras.getString("apellido")
            val fechaNacimiento = extras.getString("fechaNacimiento")
            val tieneHijos = extras.getBoolean("tieneHijos")
            val edad = extras.getString("edad")
            val telefono = extras.getString("telefono")

            // Establece los valores iniciales en los campos de edición
            etEditarNombre.setText(nombre)
            etEditarApellido.setText(apellido)
            etEditarFechaNacimiento.setText(fechaNacimiento)
            cbEditarTieneHijos.isChecked = tieneHijos
            etEditarEdad.setText(edad)
            etEditarTelefono.setText(telefono)
        }

        btnGuardar.setOnClickListener {
            // Obtiene los valores ingresados por el usuario
            val nuevoNombre = etEditarNombre.text.toString()
            val nuevoApellido = etEditarApellido.text.toString()
            val nuevaFechaNacimiento = etEditarFechaNacimiento.text.toString()
            val nuevoTieneHijos = if (cbEditarTieneHijos.isChecked) "Si" else "No"
            val nuevaEdad = etEditarEdad.text.toString()
            val nuevoTelefono = etEditarTelefono.text.toString()

            // Recupera el valor de "rut" de los extras
            val rut = extras?.getString("rut")

            if (rut != null) {
                // Abre la base de datos para actualizar los valores
                val con = SQLite(this, "RegistroUsuario", null, 1)
                val baseDatos = con.writableDatabase

                // Crea un objeto ContentValues para actualizar los datos
                val valoresActualizados = ContentValues()
                valoresActualizados.put("USU_NOMBRE", nuevoNombre)
                valoresActualizados.put("USU_APELLIDO", nuevoApellido)
                valoresActualizados.put("USU_FECHANACIMIENTO", nuevaFechaNacimiento)
                valoresActualizados.put("USU_HIJOS", nuevoTieneHijos)
                valoresActualizados.put("USU_EDAD", nuevaEdad)
                valoresActualizados.put("USU_TELEFONO", nuevoTelefono)

                // Realiza la actualización en la base de datos
                val resultado =
                    baseDatos.update("Usuario", valoresActualizados, "USU_RUT = ?", arrayOf(rut))

                if (resultado > 0) {
                    Toast.makeText(this, "Usuario actualizado correctamente", Toast.LENGTH_SHORT)
                        .show()

                } else {
                    Toast.makeText(this, "Error al actualizar el usuario", Toast.LENGTH_SHORT)
                        .show()
                }

                // Cierra la base de datos
                baseDatos.close()
            }
        }
    }
    fun IrActivityVer(view: View) {
        val intent = Intent(this, VerDatosActivity::class.java)
        startActivity(intent)
    }
}
