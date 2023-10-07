package cristobal.ruiz.formulario_android

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class GetActivity : AppCompatActivity() {


    var txtNombre: EditText? = null
    var txtApellido: EditText? = null
    var txtRUT: EditText? = null
    var txtEdad: EditText? = null
    var txtFono: EditText? = null
    var txtFechaNacimiento: EditText? = null
    var txtHijos: EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get)


        txtNombre = findViewById(R.id.txtNombre)
        txtApellido = findViewById(R.id.txtApellido)
        txtRUT = findViewById(R.id.txtRUT)
        txtEdad = findViewById(R.id.txtEdad)
        txtFono = findViewById(R.id.txtFono)
        txtFechaNacimiento = findViewById(R.id.txtFechaNacimiento)
        txtHijos = findViewById(R.id.txtHijos)


        val regresar = findViewById<ImageButton>(R.id.btnRegresar)

        regresar.setOnClickListener{
            finish()
        }
        //Obtener los Datos
        val extras = intent.extras
        if (extras != null) {
            val nombre = extras.getString("nombre")
            val apellido = extras.getString("apellido")
            val fechaNacimiento = extras.getString("fechaNacimiento")
            val rut = extras.getString("rut")
            val tieneHijos = extras.getBoolean("tieneHijos")
            val edad = extras.getString("edad")
            val telefono = extras.getString("telefono")

            // Asigna los datos a los EditText correspondientes en GetActivity
            txtRUT?.setText(rut)
            txtNombre?.setText(nombre)
            txtApellido?.setText(apellido)
            txtEdad?.setText(edad)
            txtFono?.setText(telefono)
            txtFechaNacimiento?.setText(fechaNacimiento)

            // Obtén el valor de la columna USU_HIJOS
            val hijosTexto = if (tieneHijos) "SI" else "No"
            txtHijos?.setText(hijosTexto)
        }

    }


    fun buscar(view: View) {
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
                val hijos = fila.getInt(5)

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
    }

    fun insertarDatos(view: View) {
        val con = SQLite(this, "RegistroUsuario", null, 1)
        val baseDatos = con.writableDatabase

        // Parámetros
        val nombre = txtNombre?.text.toString()
        val apellido = txtApellido?.text.toString()
        val rut = txtRUT?.text.toString()
        val edad = txtEdad?.text.toString()
        val telefono = txtFono?.text.toString()
        val fechaNacimiento = txtFechaNacimiento?.text.toString()

        // Valida que el campo "Tiene Hijos" solo contenga "Si" o "No"
        val tieneHijosText = txtHijos?.text.toString()
        val tieneHijos = if (tieneHijosText.equals("Si", ignoreCase = true) || tieneHijosText.equals("No", ignoreCase = true)) {
            tieneHijosText // Si es "Si" o "No", lo asigna directamente
        } else {
            "No" // Si no es "Si" ni "No", lo establece como "No" por defecto
        }

        if (nombre.isNotEmpty() && apellido.isNotEmpty() && rut.isNotEmpty()
            && edad.isNotEmpty() && telefono.isNotEmpty() && fechaNacimiento.isNotEmpty()) {

            val registro = ContentValues()
            registro.put("USU_RUT", rut)
            registro.put("USU_NOMBRE", nombre)
            registro.put("USU_APELLIDO", apellido)
            registro.put("USU_EDAD", edad)
            registro.put("USU_TELEFONO", telefono)
            registro.put("USU_FECHANACIMIENTO", fechaNacimiento)
            registro.put("USU_HIJOS", tieneHijos)

            baseDatos.insert("Usuario", null, registro)

            // Vaciar Campos
            txtNombre?.text?.clear()
            txtApellido?.text?.clear()
            txtFechaNacimiento?.text?.clear()
            txtRUT?.text?.clear()
            txtHijos?.text?.clear()
            txtEdad?.text?.clear()
            txtFono?.text?.clear()
            Toast.makeText(this, "Usuario Ingresado Correctamente", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Ingrese los valores Correspondientes", Toast.LENGTH_LONG).show()
        }

        baseDatos.close()
    }


    fun Eliminar(view: View) {
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
    }

    fun editar(view: View){
        val con = SQLite(this, "RegistroUsuario", null, 1)
        var BaseDatos = con.writableDatabase

        var Nombre=txtNombre?.text.toString()
        var Apellido=txtApellido?.text.toString()
        var RUT=txtRUT?.text.toString()
        var Edad=txtEdad?.text.toString()
        var Fono=txtFono?.text.toString()
        var FechaNacimiento=txtFechaNacimiento?.text.toString()
        val tieneHijos = txtHijos?.text.toString()

        if(!Nombre.isEmpty() && !Apellido.isEmpty() && !RUT.isEmpty() && !Edad.isEmpty() && !Fono.isEmpty() && !FechaNacimiento.isEmpty() && !tieneHijos.isEmpty()){
            var registro = ContentValues()
            registro.put("USU_RUT",RUT)
            registro.put("USU_NOMBRE",Nombre)
            registro.put("USU_APELLIDO",Apellido)
            registro.put("USU_EDAD",Edad)
            registro.put("USU_TELEFONO",Fono)
            registro.put("USU_FECHANACIMIENTO",FechaNacimiento)
            registro.put("USU_HIJOS",tieneHijos)


           val cant = BaseDatos.update("Usuario", registro,"USU_RUT='$RUT'",null)

            if(cant>0){
                Toast.makeText(this,"Usuario editado correctamente",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"El Registro no fue encontrado",Toast.LENGTH_LONG).show()

            }
        }else{
            Toast.makeText(this,"Los campos no pueden estar vacios",Toast.LENGTH_LONG).show()

        }

    }
}