package model;

import service.TaskStatus;

public class Subtask extends Task {
    int epicId;

    //конструктор с id для целей тестирования
    public Subtask(int id, int epicId, String nameOfTask, String description, TaskStatus taskStatus) {
        super(id, nameOfTask, description);
        this.taskStatus = taskStatus;
        this.epicId = epicId;
    }

    public Subtask(String nameOfTask, String description, TaskStatus taskStatus) {
        super(nameOfTask, description, taskStatus);
    }


    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }
}
