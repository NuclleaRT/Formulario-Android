package cristobal.ruiz.formulario_android

import android.app.DatePickerDialog
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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
        txtRUT=findViewById(R.id.txtRUT)
        txtEdad=findViewById(R.id.txtEdad)
        txtFono=findViewById(R.id.txtFono)
        txtFechaNacimiento=findViewById(R.id.txtFechaNacimiento)
        chHijos=findViewById(R.id.chHijos)

        btnFecha.setOnClickListener{
            DatePickerDialog(this, FechaSeleccionada, Calendario.get(Calendar.YEAR), Calendario.get(Calendar.MONTH),
                Calendario.get(Calendar.DAY_OF_MONTH)).show()
        }


    }

    fun insertarDatos (view: View){
        var con = SQLite(this, "RegistroUsuario", null, 1)
        var BaseDatos = con.writableDatabase
        //parametros
        var Nombre=txtNombre?.text.toString()
        var Apellido=txtApellido?.text.toString()
        var RUT=txtRUT?.text.toString()
        var Edad=txtEdad?.text.toString()
        var Fono=txtFono?.text.toString()
        var FechaNacimiento=txtFechaNacimiento?.text.toString()
        val tieneHijos = if (chHijos?.isChecked == true) 1 else 0


        if(Nombre.isEmpty()==false && Apellido.isEmpty()==false && RUT.isEmpty()==false
            && Edad.isEmpty()==false && Fono.isEmpty()==false && FechaNacimiento.isEmpty()==false){
            var registro = ContentValues()
            registro.put("RUT",RUT)
            registro.put("Nombre",Nombre)
            registro.put("Apellido",Apellido)
            registro.put("Edad",Edad)
            registro.put("Fono",Fono)
            registro.put("FechaNacimiento",FechaNacimiento)
            registro.put("Hijos",tieneHijos)
            BaseDatos.insert("Usuario", null, registro)


            //Vaciar Campos
//            txtNombre?.text?.clear()
//            txtApellido?.text?.clear()
//            txtFechaNacimiento?.text?.clear()
//            txtRUT?.text?.clear()
//            chHijos?.isChecked = false
//            txtEdad?.text?.clear()
//            txtFono?.text?.clear()
            Toast.makeText(this,"Usuario Ingresado Correctamente", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"Ingrese los valores Correspondientes", Toast.LENGTH_LONG).show()

        }
    }
    private fun ActualizarCalendario(calendario: Calendar) {
        val formato = "dd-MM-YYYY"
        val cambioFormato = SimpleDateFormat(formato, Locale.US)
        tvFecha.setText(cambioFormato.format(calendario.time))
    }


}
