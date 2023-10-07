package cristobal.ruiz.formulario_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class VerDatosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_datos)

        val userListLayout = findViewById<LinearLayout>(R.id.userListLayout)

        val con = SQLite(this, "RegistroUsuario", null, 1)
        val baseDatos = con.readableDatabase

        // Consulta todos los usuarios en la base de datos
        val cursor = baseDatos.rawQuery("SELECT * FROM Usuario", null)

        // Verifica si hay resultados en el cursor
        if (cursor.moveToFirst()) {
            do {
                val rut = cursor.getString(cursor.getColumnIndex("USU_RUT")).toString()
                val nombre = cursor.getString(cursor.getColumnIndex("USU_NOMBRE")).toString()
                val apellido = cursor.getString(cursor.getColumnIndex("USU_APELLIDO")).toString()
                val fechaNacimiento = cursor.getString(cursor.getColumnIndex("USU_FECHANACIMIENTO")).toString()
                val tieneHijos = cursor.getInt(cursor.getColumnIndex("USU_HIJOS")) == 1
                val edad = cursor.getString(cursor.getColumnIndex("USU_EDAD")).toString()
                val telefono = cursor.getString(cursor.getColumnIndex("USU_TELEFONO")).toString()

                // Crea un TextView para mostrar los datos del usuario
                val usuarioTextView = TextView(this)
                usuarioTextView.text = "RUT: $rut\nNombre: $nombre\nApellido: $apellido\nFecha de Nacimiento: $fechaNacimiento\nTiene Hijos: $tieneHijos\nEdad: $edad\nTelefono: $telefono"
                usuarioTextView.textSize = 16f
                usuarioTextView.setPadding(0, 0, 0, 16) // Añade un espacio entre usuarios

                // Crea un botón "Editar" para cada usuario
                val btnEditar = Button(this)
                btnEditar.text = "Editar"
                btnEditar.setOnClickListener {
                    editarUsuario(rut)
                }

                // Crea un botón "Eliminar" para cada usuario
                val btnEliminar = Button(this)
                btnEliminar.text = "Eliminar"
                btnEliminar.setOnClickListener {
                    eliminarUsuario(rut)
                    // Actualiza la lista de usuarios después de eliminar
                    userListLayout.removeView(usuarioTextView)
                    userListLayout.removeView(btnEditar)
                    userListLayout.removeView(btnEliminar)
                }

                // Agrega el TextView y los botones al layout
                userListLayout.addView(usuarioTextView)
                userListLayout.addView(btnEditar)
                userListLayout.addView(btnEliminar)
            } while (cursor.moveToNext())
        }

        // Cierra el cursor y la base de datos
        cursor.close()
        baseDatos.close()
    }

    private fun editarUsuario(rut: String) {
        val con = SQLite(this, "RegistroUsuario", null, 1)
        val baseDatos = con.readableDatabase

        val cursor = baseDatos.rawQuery("SELECT * FROM Usuario WHERE USU_RUT = ?", arrayOf(rut))

        if (cursor.moveToFirst()) {
            val nombre = cursor.getString(cursor.getColumnIndex("USU_NOMBRE"))
            val apellido = cursor.getString(cursor.getColumnIndex("USU_APELLIDO"))
            val fechaNacimiento = cursor.getString(cursor.getColumnIndex("USU_FECHANACIMIENTO"))
            val tieneHijos = cursor.getInt(cursor.getColumnIndex("USU_HIJOS")) == 1
            val edad = cursor.getString(cursor.getColumnIndex("USU_EDAD"))
            val telefono = cursor.getString(cursor.getColumnIndex("USU_TELEFONO"))

            // Pasa los datos del usuario a la actividad de edición
            val intent = Intent(this, EditarUsuarioActivity::class.java)
            intent.putExtra("rut", rut)
            intent.putExtra("nombre", nombre)
            intent.putExtra("apellido", apellido)
            intent.putExtra("fechaNacimiento", fechaNacimiento)
            intent.putExtra("tieneHijos", tieneHijos)
            intent.putExtra("edad", edad)
            intent.putExtra("telefono", telefono)
            startActivity(intent)
        }

        cursor.close()
        baseDatos.close()
    }

    private fun eliminarUsuario(rut: String) {
        val con = SQLite(this, "RegistroUsuario", null, 1)
        val baseDatos = con.writableDatabase

        val resultado = baseDatos.delete("Usuario", "USU_RUT = ?", arrayOf(rut))

        if (resultado > 0) {
            Toast.makeText(this, "Usuario eliminado correctamente", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Error al eliminar el usuario", Toast.LENGTH_SHORT).show()
        }

        baseDatos.close()
    }
}
