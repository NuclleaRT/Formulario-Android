package cristobal.ruiz.formulario_android

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class VerDatosActivity : AppCompatActivity() {

    private lateinit var listViewUsuarios: ListView
    private lateinit var adapter: ArrayAdapter<String> // Cambiar el tipo según tus necesidades
    private lateinit var db: SQLite
    private lateinit var usuarios: MutableList<String> // Cambiar el tipo según tus necesidades

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_datos)

        // Inicializar la base de datos SQLite utilizando tu clase SQLiteOpenHelper
        db = SQLite(this, "RegistrarUsuario", null, 1)

        // 1. Obtener una referencia al ListView desde el diseño XML
        listViewUsuarios = findViewById(R.id.listViewUsuarios)

        // 2. Obtener los datos de los usuarios de la base de datos
        usuarios = obtenerTodosLosUsuarios()

        // 3. Configurar un adaptador para el ListView
        adapter = ArrayAdapter(this, R.layout.item_usuario, usuarios)

        // 4. Asignar el adaptador al ListView
        listViewUsuarios.adapter = adapter

        // 5. Configurar OnClickListener para los botones "Eliminar"
        configurarOnClickEliminar()
    }

    private fun obtenerTodosLosUsuarios(): MutableList<String> {
        val usuarios = mutableListOf<String>()

        // Consultar todos los registros de la tabla "Usuario"
        val query = "SELECT * FROM Usuario"
        val cursor = db.readableDatabase.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val rut = cursor.getString(cursor.getColumnIndex("USU_RUT"))
            val nombre = cursor.getString(cursor.getColumnIndex("USU_NOMBRE"))
            val apellido = cursor.getString(cursor.getColumnIndex("USU_APELLIDO"))
            val edad = cursor.getInt(cursor.getColumnIndex("USU_EDAD"))
            val telefono = cursor.getString(cursor.getColumnIndex("USU_TELEFONO"))
            val fechaNacimiento = cursor.getString(cursor.getColumnIndex("USU_FECHANACIMIENTO"))
            val tieneHijos = cursor.getString(cursor.getColumnIndex("USU_HIJOS"))

            // Formatea la información de los usuarios como desees
            val usuarioInfo = "Nombre: $nombre $apellido\nRUT: $rut\nEdad: $edad\nTeléfono: $telefono\nFecha de Nacimiento: $fechaNacimiento\nTiene Hijos: $tieneHijos"
            usuarios.add(usuarioInfo)
        }

        cursor.close()
        return usuarios
    }

    private fun configurarOnClickEliminar() {
        for (i in 0 until usuarios.size) {
            val btnEliminar = listViewUsuarios.getChildAt(i)?.findViewById<Button>(R.id.btnEliminarUsuario)
            btnEliminar?.setOnClickListener {
                // Aquí puedes obtener el usuario asociado al botón haciendo referencia a la posición "i"
                val usuario = usuarios[i]
                eliminarUsuario(usuario)
                // Actualiza la lista después de eliminar el usuario
                usuarios.removeAt(i)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun eliminarUsuario(usuario: String) {
        // Realiza la eliminación del usuario en la base de datos utilizando su ID o rut
        val db = db.writableDatabase
        val whereClause = "USU_RUT = ?"
        val whereArgs = arrayOf(rut) // Asegúrate de que tengas un campo "rut" en la clase Usuario
        db.delete("Usuario", whereClause, whereArgs)
        db.close()
    }
}