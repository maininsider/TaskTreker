package model;

import java.util.Objects;

public class Task {
    private String nameOfTask;
    private String description;
    private Integer id;
    private TaskStatus taskStatus;

    //конструктор с id для целей тестирования
    public Task(int id, String nameOfTask, String description, TaskStatus taskStatus) {
        this.id = id;
        this.description = description;
        this.nameOfTask = nameOfTask;
        this.taskStatus = taskStatus;
    }

    public Task(int id, String nameOfTask, String description) {
        this.id = id;
        this.description = description;
        this.nameOfTask = nameOfTask;
        }

    public Task(String nameOfTask, String description, TaskStatus taskStatus) {
        this.nameOfTask = nameOfTask;
        this.description = description;
        this.taskStatus = taskStatus;
    }

    public Task(String nameOfTask, String description) {
        this.nameOfTask = nameOfTask;
        this.description = description;

    }

    public String getNameOfTask() {
        return nameOfTask;
    }

    public void setNameOfTask(String nameOfTask) {
        this.nameOfTask = nameOfTask;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id; /*&& Objects.equals(nameOfTask, task.nameOfTask)
                && Objects.equals(description, task.description) && taskStatus == task.taskStatus;*/
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfTask, description, id, taskStatus);
    }

    @Override
    public String toString() {
        return "model.Task{" +
                "nameOfTask='" + nameOfTask + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", taskStatus=" + taskStatus +
                '}';
    }
}
