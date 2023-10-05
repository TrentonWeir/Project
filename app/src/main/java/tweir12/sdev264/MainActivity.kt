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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvAllNotes = findViewById(R.id.rvAllNotes)
        //btnAddNote = findViewById(R.id.btnAddNote)

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

        //btnAddNote.setOnClickListener {
        //    val intent = Intent(this, Add_Note::class.java)
        //    startActivity(intent)
        //}

        // Set up RecyclerView
        rvAllNotes.layoutManager = LinearLayoutManager(this)
        noteAdapter = NoteAdapter(this, noteDB.allNotes())
        rvAllNotes.adapter = noteAdapter
    }

    override fun onResume() {
        super.onResume()
        // Update the RecyclerView when returning to the MainActivity
        noteAdapter = NoteAdapter(this, noteDB.allNotes())
        rvAllNotes.adapter = noteAdapter
    }
}
