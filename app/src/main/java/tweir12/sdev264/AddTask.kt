package tweir12.sdev264

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class AddTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val btnSave = findViewById<Button>(R.id.btnAddNote)
        val titleInput = findViewById<EditText>(R.id.txtTitle)
        val descriptionInput = findViewById<EditText>(R.id.txtDescription)
        val dateInput = findViewById<TextView>(R.id.txtDate)

        val btnHome = findViewById<ImageButton>(R.id.btnHome)
        btnHome.setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val btnBackToTasks = findViewById<ImageButton>(R.id.btnAllTasks)
        btnBackToTasks.setOnClickListener{

            var intent = Intent(this, MyNotes::class.java)
            startActivity(intent)
        }

        btnSave.setOnClickListener {
            val taskdb = TaskDBHelper(this)
            taskdb.addTask(
                titleInput.text.toString(),
                descriptionInput.text.toString(),
                dateInput.text.toString()
            )
        }
    }
}