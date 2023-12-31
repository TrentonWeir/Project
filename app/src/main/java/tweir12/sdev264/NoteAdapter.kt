package tweir12.sdev264

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(
    private val context: Context,
    private val cursor: Cursor?
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTextView: TextView = itemView.findViewById(R.id.idTextView)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        val editButton: ImageButton = itemView.findViewById(R.id.editNote)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteNote)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        cursor?.moveToPosition(position)

        // Extract the note ID from the cursor
        val noteId = cursor?.getLong(cursor.getColumnIndex(NoteDBHelper.COLUMN_ID))

        holder.editButton.setOnClickListener {
            // Open the Add_Note activity with the note ID for editing
            val intent = Intent(context, Add_Note::class.java)
            intent.putExtra("NOTE_ID", noteId) // Pass the note ID to Add_Note
            context.startActivity(intent)
        }
        holder.deleteButton.setOnClickListener {
            // Extract the note ID from the cursor
            val noteId = cursor?.getLong(cursor.getColumnIndex(NoteDBHelper.COLUMN_ID))

            // Delete the note using the NoteDBHelper
            val notedb = NoteDBHelper(context)
            if (noteId != null) {
                if (notedb.deleteNoteById(noteId)) {
                    Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Failed to delete note", Toast.LENGTH_SHORT).show()
                }
                // Refresh the RecyclerView after deleting
                notifyDataSetChanged()
            }
            (context as AppCompatActivity).recreate()
        }


        // Populate your TextViews with data
        holder.idTextView.text = noteId.toString()
        holder.titleTextView.text = cursor?.getString(cursor.getColumnIndex(NoteDBHelper.COLUMN_TITLE))
        holder.descriptionTextView.text = cursor?.getString(cursor.getColumnIndex(NoteDBHelper.COLUMN_DESCRIPTION))
        holder.dateTextView.text = cursor?.getString(cursor.getColumnIndex(NoteDBHelper.COLUMN_DATE))
    }




    override fun getItemCount(): Int {
        return cursor?.count ?: 0
    }
}
