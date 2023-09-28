package cristobal.ruiz.formulario_android

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class SQLite(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table Usuario (USU_RUT TEXT PRIMARY KEY, " +
                "USU_NOMBRE TEXT," +
                " USU_APELLIDO TEXT," +
                "USU_EDAD int, " +
                "USU_TELEFONO int," +
                " USU_FECHANACIMIENTO TEXT," +
                " USU_HIJOS TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

}