package cristobal.ruiz.formulario_android

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class VerDatosActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UsuarioAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_datos)

        recyclerView = findViewById(R.id.recyclerViewUsuarios)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Obtener la lista de usuarios de la base de datos
        val usuarios = obtenerTodosLosUsuarios()

        // Configurar el adaptador con la lista de usuarios
        adapter = UsuarioAdapter(usuarios)
        recyclerView.adapter = adapter
    }

    // Función para obtener todos los usuarios de la base de datos
    private fun obtenerTodosLosUsuarios(): List<Usuario> {
        val usuarios = mutableListOf<Usuario>()
        val con = SQLite(this, "RegistroUsuario", null, 1)
        val baseDatos = con.readableDatabase

        val cursor = baseDatos.rawQuery("SELECT * FROM Usuario", null)
        if (cursor.moveToFirst()) {
            do {
                val nombre = cursor.getString(cursor.getColumnIndex("USU_NOMBRE"))
                val apellido = cursor.getString(cursor.getColumnIndex("USU_APELLIDO"))
                val rut = cursor.getString(cursor.getColumnIndex("USU_RUT"))
                val edad = cursor.getString(cursor.getColumnIndex("USU_EDAD"))
                val telefono = cursor.getString(cursor.getColumnIndex("USU_TELEFONO"))
                val fechaNacimiento =
                    cursor.getString(cursor.getColumnIndex("USU_FECHANACIMIENTO"))
                val tieneHijos = cursor.getString(cursor.getColumnIndex("USU_HIJOS"))

                val usuario =
                    Usuario(nombre, apellido, edad, rut, telefono, fechaNacimiento, tieneHijos)
                usuarios.add(usuario)
            } while (cursor.moveToNext())
        }

        cursor.close()
        baseDatos.close()

        return usuarios
    }

    // Función para mostrar el diálogo de confirmación
    fun mostrarDialogoConfirmacion(rut: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmación")
            .setMessage("¿Deseas eliminar este usuario?")
            .setPositiveButton("Aceptar") { _, _ ->
                eliminarUsuario(rut)
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    // Función para eliminar al usuario en la base de datos SQLite
    private fun eliminarUsuario(rut: String) {
        val con = SQLite(this, "RegistroUsuario", null, 1)
        val baseDatos = con.writableDatabase

        val cant = baseDatos.delete("Usuario", "USU_RUT=?", arrayOf(rut))
        baseDatos.close()

        if (cant > 0) {
            Toast.makeText(this, "Usuario Eliminado Correctamente", Toast.LENGTH_LONG).show()
            // Actualizar el RecyclerView después de eliminar el usuario
            adapter.notifyDataSetChanged()
        } else {
            Toast.makeText(this, "Usuario no fue encontrado", Toast.LENGTH_LONG).show()
        }
    }

    // Adaptador para el RecyclerView
    inner class UsuarioAdapter(private val usuarios: List<Usuario>) :
        RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_usuario, parent, false)
            return UsuarioViewHolder(view)
        }

        override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
            val usuario = usuarios[position]

            holder.nombreTextView.text = "Nombre: ${usuario.nombre}"
            holder.apellidoTextView.text = "Apellido: ${usuario.apellido}"
            holder.rutTextView.text = "Rut: ${usuario.rut}"
            holder.edadTextView.text = "Edad: ${usuario.edad}"
            holder.telefonoTextView.text = "Telefono: ${usuario.telefono}"
            holder.fechaNacimientoTextView.text =
                "Fecha de Nacimiento: ${usuario.fechaNacimiento}"
            holder.hijosTextView.text = "¿Tiene hijos?: ${usuario.tieneHijosTexto}"

            holder.btnEliminar.setOnClickListener {
                mostrarDialogoConfirmacion(usuario.rut)
            }
        }

        override fun getItemCount(): Int {
            return usuarios.size
        }

        inner class UsuarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val nombreTextView: TextView = itemView.findViewById(R.id.textViewNombre)
            val apellidoTextView: TextView = itemView.findViewById(R.id.textViewApellido)
            val rutTextView: TextView = itemView.findViewById((R.id.textViewRut))
            val edadTextView: TextView = itemView.findViewById(R.id.textViewEdad)
            val telefonoTextView: TextView = itemView.findViewById(R.id.textViewTelefono)
            val fechaNacimientoTextView: TextView =
                itemView.findViewById(R.id.textViewFechaNacimiento)
            val hijosTextView: TextView = itemView.findViewById(R.id.textViewTieneHijos)
            val btnEliminar: Button = itemView.findViewById(R.id.btnEliminarUsuario)
        }
    }
}
