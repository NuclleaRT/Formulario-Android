package cristobal.ruiz.formulario_android

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.View
import android.widget.TextView

class VerDatosActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UsuarioAdapter

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
                val rut = cursor.getString((cursor.getColumnIndex("USU_RUT")))
                val edad = cursor.getString(cursor.getColumnIndex("USU_EDAD"))
                val telefono = cursor.getString(cursor.getColumnIndex("USU_TELEFONO"))
                val fechaNacimiento =cursor.getString(cursor.getColumnIndex("USU_FECHANACIMIENTO"))
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

    // Adaptador para el RecyclerView
    inner class UsuarioAdapter(private val usuarios: List<Usuario>) :
        RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_usuario, parent, false)
            return UsuarioViewHolder(view)
        }

        override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
            val usuario = usuarios[position]

            holder.nombreTextView.text = "Nombre: ${usuario.nombre}"
            holder.apellidoTextView.text = "Apellido: ${usuario.apellido}"
            holder.rutTextView.text = "Rut"
            holder.edadTextView.text = "Edad: ${usuario.edad}"
            holder.telefonoTextView.text = "Telefono: ${usuario.telefono}"
            holder.fechaNacimientoTextView.text = "Fecha de Nacimiento: ${usuario.fechaNacimiento}"
            holder.hijosTextView.text = "¿Tiene hijos?: ${usuario.tieneHijosTexto}"
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
            val fechaNacimientoTextView: TextView = itemView.findViewById(R.id.textViewFechaNacimiento)
            val hijosTextView: TextView = itemView.findViewById(R.id.textViewTieneHijos)
        }
    }
}
