package service;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.Collection;

public interface TaskManager {
    int generateNewId();

    Task addTask(Task task);

    Epic addEpic(Epic epic);

    Subtask addSubtask(Subtask subtask);

    Task updateTask(Task task);

    Epic updateEpic(Epic epic);

    Subtask updateSubtask(Subtask subtask);

    Task getTaskById(int id);

    Epic getEpicById(int id);

    Subtask getSubtaskById(int id);

    Collection<Task> getTasks();

    Collection<Epic> getEpics();

    Collection<Subtask> getSubtasks();

    ArrayList<Subtask> getSubtasksOfEpic(int epicId);

    Task removeTaskById(int id);

    Subtask removeSubtaskById(int id);

    Epic removeEpicById(int id);

    void removeTasks();

    void removeEpics();

    void removeSubtasks();

    ArrayList<Task> getHistoryFromHistoryManager();
}
