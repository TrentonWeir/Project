package tweir12.sdev264

class TaskDTO(
    id:Int?,
    title: String,
    description: String,
    date: String
) : DataDTO(id,title, description, date) {
    // You can add specific properties or methods for tasks here
}
