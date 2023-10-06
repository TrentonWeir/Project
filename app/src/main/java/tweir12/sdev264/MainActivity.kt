package tweir12.sdev264

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    //lateinit var btnAddNote: FloatingActionButton
    lateinit var rvAllNotes: RecyclerView
    lateinit var noteDB: NoteDBHelper
    lateinit var noteAdapter: NoteAdapter

    lateinit var rvAllTasks: RecyclerView
    lateinit var taskDB: TaskDBHelper
    lateinit var taskAdapter: TaskAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //THEMEING
        val isDarkMode = false// Determine if dark mode is enabled

        if (isDarkMode) {
            setTheme(R.style.Theme_Project) // Apply dark mode theme
        } else {
            setTheme(R.style.Theme_Project) // Apply light mode theme
        }
        setContentView(R.layout.activity_main)

        rvAllNotes = findViewById(R.id.rvAllNotes)
        rvAllTasks = findViewById(R.id.rvAllTasks)

        val btnNotes = findViewById<Button>(R.id.btnNotes)
        btnNotes.setOnClickListener {
            var intent = Intent(this, MyNotes::class.java)
            startActivity(intent)
        }
        val btnTasks = findViewById<Button>(R.id.btnTasks)
        btnTasks.setOnClickListener {
            var intent = Intent(this, MyTasks::class.java)
            startActivity(intent)
        }


        noteDB = NoteDBHelper(this)
        taskDB = TaskDBHelper(this)

        //btnAddNote.setOnClickListener {
        //    val intent = Intent(this, Add_Note::class.java)
        //    startActivity(intent)
        //}

        // Set up RecyclerView
        rvAllNotes.layoutManager = LinearLayoutManager(this)
        rvAllTasks.layoutManager = LinearLayoutManager(this)

        noteAdapter = NoteAdapter(this, noteDB.lastTwoNotes())
        taskAdapter = TaskAdapter(this, taskDB.lastTwoTasks())

        rvAllNotes.adapter = noteAdapter
        rvAllTasks.adapter = taskAdapter




    }

    override fun onResume() {
        super.onResume()
        // Update the RecyclerView when returning to the MainActivity
        noteAdapter = NoteAdapter(this, noteDB.allNotes())
        taskAdapter = TaskAdapter(this, taskDB.allTasks())
        rvAllNotes.adapter = noteAdapter
        rvAllTasks.adapter = taskAdapter
    }
}
