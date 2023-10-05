package tweir12.sdev264

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val context: Context, private val cursor: Cursor?) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        cursor?.moveToPosition(position)
        holder.titleTextView.text = cursor?.getString(cursor.getColumnIndex(NoteDBHelper.COLUMN_TITLE))
        holder.descriptionTextView.text = cursor?.getString(cursor.getColumnIndex(NoteDBHelper.COLUMN_DESCRIPTION))
        holder.dateTextView.text = cursor?.getString(cursor.getColumnIndex(NoteDBHelper.COLUMN_DATE))
    }



    override fun getItemCount(): Int {
        return cursor?.count ?: 0
    }
}
