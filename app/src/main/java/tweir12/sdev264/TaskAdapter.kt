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
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val context: Context, private val cursor: Cursor?) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        val editButton: ImageButton = itemView.findViewById(R.id.editTask)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteTask)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        cursor?.moveToPosition(position)

        // Extract the note ID from the cursor
        val taskId = cursor?.getLong(cursor.getColumnIndex(TaskDBHelper.COLUMN_ID))

        holder.editButton.setOnClickListener {
            // Open the AddTask activity with the note ID for editing
            val intent = Intent(context, AddTask::class.java)
            intent.putExtra("TASK_ID", taskId) // Pass the note ID to Add_Note
            context.startActivity(intent)
        }

        holder.deleteButton.setOnClickListener {
            // Delete the task with the specified ID
            if (taskId != null) {
                val taskDB = TaskDBHelper(context)
                if (taskDB.deleteTaskById(taskId)) {
                    // Notify the adapter that the data has changed (note deleted)
                    notifyDataSetChanged()
                    Toast.makeText(context, "TASK $taskId deleted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Failed to delete TASK $taskId", Toast.LENGTH_SHORT).show()
                }
            }
        }
        holder.titleTextView.text = cursor?.getString(cursor.getColumnIndex(TaskDBHelper.COLUMN_TITLE))
        holder.descriptionTextView.text = cursor?.getString(cursor.getColumnIndex(TaskDBHelper.COLUMN_DESCRIPTION))
        holder.dateTextView.text = cursor?.getString(cursor.getColumnIndex(TaskDBHelper.COLUMN_DATE))
    }



    override fun getItemCount(): Int {
        return cursor?.count ?: 0
    }
}