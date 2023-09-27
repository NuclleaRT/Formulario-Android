package cristobal.ruiz.formulario_android

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale





class MainActivity : AppCompatActivity() {

    private lateinit var btnFecha : Button;
    private lateinit var tvFecha : TextView

    var txtNombre : EditText?=null
    var txtApellido : EditText?=null
    var txtRUT : EditText?=null
    var txtEdad : EditText?=null
    var txtFono : EditText?=null
    var txtFechaNacimiento : EditText?=null
    var chHijos : CheckBox?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btnFecha = findViewById(R.id.btnFecha)
        tvFecha = findViewById(R.id.txtFechaNacimiento)

        val Calendario = Calendar.getInstance()
        val FechaSeleccionada = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            Calendario.set(Calendar.YEAR, year )
            Calendario.set(Calendar.MONTH, month)
            Calendario.set(Calendar.DAY_OF_MONTH, day)
            ActualizarCalendario(Calendario)
        }

        txtNombre=findViewById(R.id.txtNombre)
        txtApellido=findViewById(R.id.txtApellido)
        txtRUT=findViewById(R.id.`as`)
        txtEdad=findViewById(R.id.txtEdad)
        txtFono=findViewById(R.id.txtFono)
        txtFechaNacimiento=findViewById(R.id.txtFechaNacimiento)
        chHijos=findViewById(R.id.chHijos)

        btnFecha.setOnClickListener{
            DatePickerDialog(this, FechaSeleccionada, Calendario.get(Calendar.YEAR), Calendario.get(Calendar.MONTH),
                Calendario.get(Calendar.DAY_OF_MONTH)).show()
        }

        val Enviar = findViewById<Button>(R.id.btnGuardar)
        Enviar.setOnClickListener{
            val nombre = findViewById<EditText>(R.id.txtNombre).text.toString()
            val apellido = findViewById<EditText>(R.id.txtApellido).text.toString()
            val fechaNacimiento = findViewById<EditText>(R.id.txtFechaNacimiento).text.toString()
            val rut = findViewById<EditText>(R.id.`as`).text.toString()
            val tieneHijos = findViewById<CheckBox>(R.id.chHijos).isChecked
            val edad = findViewById<EditText>(R.id.txtEdad).text.toString()
            val telefono = findViewById<EditText>(R.id.txtFono).text.toString()

            val bundle = Bundle()
            bundle.putString("nombre", nombre)
            bundle.putString("apellido", apellido)
            bundle.putString("fechaNacimiento", fechaNacimiento)
            bundle.putString("rut", rut)
            bundle.putBoolean("tieneHijos", tieneHijos)
            bundle.putString("edad",edad)
            bundle.putString("telefono",telefono)

            val intent = Intent(this,GetActivity::class.java)
            intent.putExtras(bundle)

            startActivityForResult(intent, 1) // Utiliza startActivityForResult para obtener una respuesta
        }

    }

    // Sobreescribe onActivityResult para manejar el resultado de GetActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // La solicitud se completó correctamente, así que limpiamos los campos.
                limpiarCampos()
            }
        }
    }

    private fun limpiarCampos() {
        txtNombre?.text?.clear()
        txtApellido?.text?.clear()
        txtFechaNacimiento?.text?.clear()
        txtRUT?.text?.clear()
        chHijos?.isChecked = false
        txtEdad?.text?.clear()
        txtFono?.text?.clear()
    }

    private fun ActualizarCalendario(calendario: Calendar) {
        val formato = "dd-MM-YYYY"
        val cambioFormato = SimpleDateFormat(formato, Locale.US)
        tvFecha.setText(cambioFormato.format(calendario.time))
    }




}
