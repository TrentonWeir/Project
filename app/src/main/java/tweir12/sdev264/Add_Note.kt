package tweir12.sdev264

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import java.time.LocalDateTime
import java.util.Date

class Add_Note : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        val btnSave = findViewById<Button>(R.id.btnAddNote)
        val titleInput = findViewById<EditText>(R.id.txtTitle)
        val descriptionInput = findViewById<EditText>(R.id.txtDescription)
        val dateInput = findViewById<TextView>(R.id.txtDate)


        val date = LocalDateTime.now()
        dateInput.text = "Date: ${date.toLocalDate()}"

        val btnBackToNotes = findViewById<ImageButton>(R.id.btnAllNotes)
        btnBackToNotes.setOnClickListener{

                var intent = Intent(this, MyNotes::class.java)
                startActivity(intent)
        }

        val btnHome = findViewById<ImageButton>(R.id.btnHome)
        btnHome.setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnSave.setOnClickListener {
            val notedb = NoteDBHelper(this)
            notedb.addNote(
                titleInput.text.toString(),
                descriptionInput.text.toString(),
                LocalDateTime.now().toLocalDate().toString()
            )
        }

    }
}