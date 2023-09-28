package cristobal.ruiz.formulario_android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        holder.nombreTextView.text = "USU_NOMBRE: ${usuario.nombre}"
        holder.apellidoTextView.text = "USU_APELLIDO: ${usuario.apellido}"
        holder.rutTextView.text = "USU_RUT: ${usuario.rut}"
        holder.edadTextView.text = "USU_EDAD: ${usuario.edad}"
        holder.telefonoTextView.text = "USU_TELEFONO: ${usuario.telefono}"
        holder.fechaNacimientoTextView.text = "USU_FECHANACIMIENTO: ${usuario.fechaNacimiento}"
        //val tieneHijosTexto = if (usuario.tieneHijos) "Sí" else "No"
        holder.tieneHijosTextView.text = "USU_HIJOS: ${usuario.tieneHijosTexto}"
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
    }
}
