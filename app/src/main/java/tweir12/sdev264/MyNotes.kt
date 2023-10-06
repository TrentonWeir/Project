package tweir12.sdev264

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MyNotes : AppCompatActivity() {

    lateinit var btnAddNote: FloatingActionButton
    lateinit var rvAllNotes: RecyclerView
    lateinit var noteDB: NoteDBHelper
    lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_notes)

        rvAllNotes = findViewById(R.id.rvAllNotes)
        btnAddNote = findViewById(R.id.btnAddNote)

        noteDB = NoteDBHelper(this)

        val btnHome = findViewById<ImageButton>(R.id.btnHome)
        btnHome.setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val btnBackToNotes = findViewById<ImageButton>(R.id.btnAllNotes)
        btnBackToNotes.setOnClickListener{

            var intent = Intent(this, MyNotes::class.java)
            startActivity(intent)
        }

        btnAddNote.setOnClickListener {
            val intent = Intent(this, Add_Note::class.java)
            startActivity(intent)
        }

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