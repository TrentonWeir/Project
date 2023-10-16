package tweir12.sdev264

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import java.time.LocalDateTime

class Add_Note : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        val btnSave = findViewById<Button>(R.id.btnAddNote)
        val titleInput = findViewById<EditText>(R.id.txtTitle)
        val descriptionInput = findViewById<EditText>(R.id.txtDescription)
        val dateInput = findViewById<TextView>(R.id.txtDate)
        val txtId = findViewById<TextView>(R.id.txtId) // Add this line

        val date = LocalDateTime.now()
        dateInput.text = "Date: ${date.toLocalDate()}"

        val btnBackToNotes = findViewById<ImageButton>(R.id.btnAllNotes)
        btnBackToNotes.setOnClickListener {
            val intent = Intent(this, MyNotes::class.java)
            startActivity(intent)
        }

        val btnHome = findViewById<ImageButton>(R.id.btnHome)
        btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Check if a noteId was passed from the detail view
        val noteId = intent.getLongExtra("NOTE_ID", -1) // -1 is the default value if no ID is provided

        if (noteId.toInt() != -1) {
            // A valid noteId was provided, so you should fetch and populate the EditTexts
            val notedb = NoteDBHelper(this)
            val cursor = notedb.selectNoteById(noteId)

            // Check if the cursor has data and move to the first row
            if (cursor != null && cursor.moveToFirst()) {
                txtId.text = noteId.toString() // Set the ID in the TextView
                titleInput.setText(cursor.getString(cursor.getColumnIndex(NoteDBHelper.COLUMN_TITLE)))
                descriptionInput.setText(cursor.getString(cursor.getColumnIndex(NoteDBHelper.COLUMN_DESCRIPTION)))
                // You can add date handling as needed
            }
        } else {
            txtId.text = "" // Indicate this is a new note
        }

        btnSave.setOnClickListener {
            // Update the existing note or add a new note as needed
            val notedb = NoteDBHelper(this)
            if (noteId.toInt() != -1) {
                // Update the existing note
                notedb.updateNoteById(
                    noteId,
                    titleInput.text.toString(),
                    descriptionInput.text.toString(),
                    LocalDateTime.now().toLocalDate().toString()
                )
            } else {
                // Add a new note if txtId is empty or null
                if (txtId.text.isNullOrBlank()) {
                    notedb.addNote(
                        titleInput.text.toString(),
                        descriptionInput.text.toString(),
                        LocalDateTime.now().toLocalDate().toString()
                    )
                }
            }
            finish() // Close the activity when done
        }
    }
}
