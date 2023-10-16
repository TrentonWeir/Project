package tweir12.sdev264

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import java.time.LocalDateTime

class AddTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val btnSave = findViewById<Button>(R.id.btnAddTask)
        val titleInput = findViewById<EditText>(R.id.txtTitle)
        val descriptionInput = findViewById<EditText>(R.id.txtDescription)
        val dateInput = findViewById<TextView>(R.id.txtDate)
        val txtId = findViewById<TextView>(R.id.txtId) // Add this line

        val date = LocalDateTime.now()
        dateInput.text = "Date: ${date.toLocalDate()}"

        val btnBackToTasks = findViewById<ImageButton>(R.id.btnAllTasks)
        btnBackToTasks.setOnClickListener {
            val intent = Intent(this, MyTasks::class.java)
            startActivity(intent)
        }

        val btnHome = findViewById<ImageButton>(R.id.btnHome)
        btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Check if a taskId was passed from the detail view
        val taskId = intent.getLongExtra("TASK_ID", -1) // -1 is the default value if no ID is provided

        if (taskId.toInt() != -1) {
            // A valid taskId was provided, so you should fetch and populate the EditTexts
            val taskdb = TaskDBHelper(this)
            val cursor = taskdb.selectTaskById(taskId)

            // Check if the cursor has data and move to the first row
            if (cursor != null && cursor.moveToFirst()) {
                txtId.text = taskId.toString() // Set the ID in the TextView
                titleInput.setText(cursor.getString(cursor.getColumnIndex(TaskDBHelper.COLUMN_TITLE)))
                descriptionInput.setText(cursor.getString(cursor.getColumnIndex(TaskDBHelper.COLUMN_DESCRIPTION)))
                // You can add date handling as needed
            }
        } else {
            txtId.text = "" // Indicate this is a new task
        }

        btnSave.setOnClickListener {
            // Update the existing task or add a new task as needed
            val taskdb = TaskDBHelper(this)
            if (taskId.toInt() != -1) {
                // Update the existing task
                taskdb.updateTaskById(
                    taskId,
                    titleInput.text.toString(),
                    descriptionInput.text.toString(),
                    dateInput.text.toString()
                )
            } else {
                // Add a new task if txtId is empty or null
                if (txtId.text.isNullOrBlank()) {
                    taskdb.addTask(
                        titleInput.text.toString(),
                        descriptionInput.text.toString(),
                        dateInput.text.toString()
                    )
                }
            }
            finish() // Close the activity when done
        }
    }
}
