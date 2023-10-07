package cristobal.ruiz.formulario_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
    fun IrActivityMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun IrActivityVer(view: View) {
        val intent = Intent(this, VerDatosActivity::class.java)
        startActivity(intent)
    }
}