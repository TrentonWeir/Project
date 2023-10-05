package tweir12.sdev264

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val btnSave = findViewById<Button>(R.id.btnAddNote)
        val titleInput = findViewById<EditText>(R.id.txtTitle)
        val descriptionInput = findViewById<EditText>(R.id.txtDescription)
        val dateInput = findViewById<EditText>(R.id.txtDate)

        btnSave.setOnClickListener {
            val taskdb = TaskDBHelper(this)
            taskdb.addNote(
                titleInput.text.toString(),
                descriptionInput.text.toString(),
                dateInput.text.toString()
            )
        }
    }
}