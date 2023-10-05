package tweir12.sdev264

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MyTasks : AppCompatActivity() {

    lateinit var btnAddTask: FloatingActionButton
    lateinit var rvAllTasks: RecyclerView
    lateinit var taskDB: TaskDBHelper
    lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_tasks)
        rvAllTasks = findViewById(R.id.rvAllTasks)
        btnAddTask = findViewById(R.id.btnAddTask)

        taskDB = TaskDBHelper(this)

        btnAddTask.setOnClickListener {
            val intent = Intent(this, AddTask::class.java)
            startActivity(intent)
        }

        // Set up RecyclerView
        rvAllTasks.layoutManager = LinearLayoutManager(this)
        taskAdapter = TaskAdapter(this, taskDB.allTasks())
        rvAllTasks.adapter = taskAdapter
    }

    override fun onResume() {
        super.onResume()
        // Update the RecyclerView when returning to the MainActivity
        taskAdapter = TaskAdapter(this, taskDB.allTasks())
        rvAllTasks.adapter = taskAdapter
    }
}