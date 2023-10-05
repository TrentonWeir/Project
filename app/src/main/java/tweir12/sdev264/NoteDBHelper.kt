package tweir12.sdev264

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class NoteDBHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
    private val ctx = context

    companion object {
        private const val DATABASE_NAME = "NoteTracker.db"
        private const val DATABASE_VER = 1
        private const val TABLE_NAME = "notes"
        public const val COLUMN_ID = "_id"
        public const val COLUMN_TITLE = "title"
        public const val COLUMN_DESCRIPTION = "description"
        public const val COLUMN_DATE = "date"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val query = "CREATE TABLE $TABLE_NAME " +
                "($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_TITLE TEXT, " +
                "$COLUMN_DESCRIPTION TEXT, " +
                "$COLUMN_DATE TEXT);"

        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }

    fun addNote(title: String, description: String, date: String) {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(COLUMN_TITLE, title)
        cv.put(COLUMN_DESCRIPTION, description)
        cv.put(COLUMN_DATE, date)

        val result = db.insert(TABLE_NAME, null, cv)

        if (result == -1L) {
            Toast.makeText(ctx, "FAILED TO INSERT", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(ctx, "SUCCESSFULLY CREATED NOTE ID: $result", Toast.LENGTH_SHORT).show()
        }
    }

    fun allNotes(): Cursor? {
        val query = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        if(db != null){
            cursor = db.rawQuery(query, null)
        }
        return cursor
    }

    fun selectNoteById(id:Int){

    }
}
