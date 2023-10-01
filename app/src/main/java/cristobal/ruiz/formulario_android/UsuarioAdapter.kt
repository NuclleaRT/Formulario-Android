package cristobal.ruiz.formulario_android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UsuarioAdapter(private val usuarios: List<Usuario>) :
    RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_usuario, parent, false)
        return UsuarioViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = usuarios[position]

        holder.nombreTextView.text = "Nombre: ${usuario.nombre}"
        holder.apellidoTextView.text = "Apellido: ${usuario.apellido}"
        holder.rutTextView.text = "Rut: ${usuario.rut}"
        holder.edadTextView.text = "Edad: ${usuario.edad}"
        holder.telefonoTextView.text = "Telefono: ${usuario.telefono}"
        holder.fechaNacimientoTextView.text = "Fecha de Nacimiento: ${usuario.fechaNacimiento}"
        //val tieneHijosTexto = if (usuario.tieneHijos) "Sí" else "No"
        holder.tieneHijosTextView.text = "¿Tiene Hijos?: ${usuario.tieneHijosTexto}"



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
        val tieneHijosTextView: TextView = itemView.findViewById(R.id.textViewTieneHijos)
        val btnEliminar: Button = itemView.findViewById(R.id.btnEliminarUsuario)
    }
}
