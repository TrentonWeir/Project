package tweir12.sdev264

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.widget.ConstraintLayout
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

    private lateinit var bg:ConstraintLayout
    private lateinit var day_night_switch:SwitchCompat


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bg = findViewById(R.id.bg)
        day_night_switch = findViewById(R.id.day_night_switch)
        day_night_switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Set background color to purple
                bg.setBackgroundColor(resources.getColor(R.color.purple_700))
            } else {
                // Set background color to teal
                bg.setBackgroundColor(resources.getColor(R.color.teal_500))
            }
        }


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
