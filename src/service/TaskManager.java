package service;

import model.Task;
import model.Subtask;
import model.Epic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class TaskManager {
    static int taskId = 0;
    ArrayList<Task> allTasks = new ArrayList<>();
    ArrayList<Task> tasksList = new ArrayList<>();
    ArrayList<Task> epicsList = new ArrayList<>();
    ArrayList<Task> subtaskList = new ArrayList<>();
    public HashMap<Integer, Task> tasks = new HashMap<>();
    public HashMap<Integer, Epic> epics = new HashMap<>();
    public HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public int generateNewId() {
        taskId++;
        return taskId;
    }

    public Task addTask(Task task) {
        int newId = generateNewId();
        tasks.put(newId, task);
        task.setId(newId);
        return task;
    }

    public Epic addEpic(Epic epic) {
        int newId = generateNewId();
        epics.put(newId, epic);
        epic.setId(newId);
        return epic;
    }

    public Subtask addSubtask(Subtask subtask, Epic epic) {
        if (subtask != null && epic != null) {
            int newId = generateNewId();
            subtask.setEpicId(epic.id);
            subtasks.put(newId, subtask);
            subtask.setId(newId);
            epic.setSubtaskId(newId);
            updateEpicStatus(epics.get(subtask.getEpicId()));
            return subtask;
        } else {
            return null;
        }
    }

    private void updateEpicStatus(Epic epic) {
        if (epic != null) {
            ArrayList<TaskStatus> taskStatuses = new ArrayList<>();
            ArrayList<Subtask> subtasksOfEpic = getSubtasksOfEpic(epic.getId());

            if (subtasks.isEmpty()) {
                epic.setTaskStatus(TaskStatus.NEW);
            } else {
                for (Subtask subtask : subtasksOfEpic) {
                    taskStatuses.add(subtask.getTaskStatus());
                }
                if (epic.getSubtasksIds().isEmpty()) {
                    epic.setTaskStatus(TaskStatus.NEW);
                } else if (taskStatuses.contains(TaskStatus.DONE)
                        && !taskStatuses.contains(TaskStatus.IN_PROGRESS)
                        && !taskStatuses.contains(TaskStatus.NEW)) {
                    epic.setTaskStatus(TaskStatus.DONE);
                } else if (taskStatuses.contains(TaskStatus.NEW)
                        && !taskStatuses.contains(TaskStatus.IN_PROGRESS)
                        && !taskStatuses.contains(TaskStatus.DONE)) {
                    epic.setTaskStatus(TaskStatus.NEW);
                } else {
                    epic.setTaskStatus(TaskStatus.IN_PROGRESS);
                }
            }
        }
    }

    public Task updateTask(Task task) {
        if (task != null) {
            int id = task.getId();
            if (tasks.containsKey(id)) {
                tasks.put(id, task);
                return task;
            } else {
                System.out.println("Задачи с данным id не существует");
                return null;
            }
        } else {
            return null;
        }
    }

    public Epic updateEpic(Epic epic) {
        if (epic != null) {
            int id = epic.getId();
            if (epics.containsKey(id)) {
                tasks.put(id, epic);
                updateEpicStatus(epic);
                return epic;
            } else {
                System.out.println("Эпика с данным id не существует");
                return null;
            }
        } else {
            return null;
        }
    }

    public Subtask updateSubtask(Subtask subtask) {
        if (subtask != null) {
            int id = subtask.getId();
            if (subtasks.keySet().contains(id)) {
                subtasks.put(subtask.getId(), subtask);
                updateEpicStatus(epics.get(subtask.getEpicId()));
                return subtask;
            } else {
                System.out.println("Подзадачи с данным id не существует");
                return null;
            }
        } else {
            return null;
        }
    }

    public Task getTaskById(int id) {
        if (tasks.containsKey(id)) {
            return tasks.get(id);
        } else {
            System.out.println("Задачи с данным id не существует");
        }
        return null;
    }

    public Epic getEpicById(int id) {
        if (epics.containsKey(id)) {
            return epics.get(id);
        } else {
            System.out.println("Эпика с данным id не существует");
        }
        return null;
    }

    public Subtask getSubtaskById(int id) {
        if (subtasks.containsKey(id)) {
            return subtasks.get(id);
        } else {
            System.out.println("Подзадачи с данным id не существует");
        }
        return null;
    }

    public ArrayList<Task> getTasks() {
        tasksList.addAll(tasks.values());
        return tasksList;
    }

    public ArrayList<Task> getEpics() {
        epicsList.addAll(epics.values());
        return epicsList;
    }

    public ArrayList<Task> getSubtasks() {
        subtaskList.addAll(subtasks.values());
        return subtaskList;
    }

    public ArrayList<Task> getAllTasks() {
        allTasks.addAll(tasks.values());
        allTasks.addAll(epics.values());
        allTasks.addAll(subtasks.values());
        return allTasks;
    }

    public ArrayList<Subtask> getSubtasksOfEpic(int epicId) {
        ArrayList<Subtask> epicSubtasks = new ArrayList<>();
        if (epics.containsKey(epicId)) {
            Epic currentEpic = epics.get(epicId);
            ArrayList<Integer> subtasksIds = currentEpic.getSubtasksIds();
            for (int subtaskId : subtasksIds) {
                epicSubtasks.add(subtasks.get(subtaskId));
            }
            return epicSubtasks;
        } else {
            System.out.println("Эпика с данным id не существует");
        }
        return null;
    }

    public Task removeTaskById(int id) {
        if (tasks.containsKey(id)) {
            Task task = tasks.get(id);
            tasks.remove(id);
            return task;
        } else {
            System.out.println("Задачи с данным id не существует");
            return null;
        }
    }

    public Subtask removeSubtaskById(int id) {
        if (subtasks.containsKey(id)) {
            Subtask subtask = subtasks.get(id);
            int epicId = subtask.getEpicId();
            Epic epicOfSubtask = epics.get(epicId);

            subtasks.remove(id);
            epicOfSubtask.removeSubtasksId(id);
            updateEpicStatus(epicOfSubtask);
            return subtask;
        } else {
            System.out.println("Подзадачи с данным id не существует");
            return null;
        }
    }

    public void removeEpicById(int id) {
        if (epics.containsKey(id)) {
            for (int subtaskId : subtasks.keySet()) {
                if (subtasks.get(subtaskId).getEpicId() == id) {
                 subtasks.remove(subtaskId);
                }
            }
            epics.remove(id);
        } else {
            System.out.println("Эпика с данным id не существует");
        }
    }

    public void removeTasks() {
        tasks.clear();
    }

    public void removeEpics() {
        epics.clear();
        subtasks.clear();
    }

    public void removeSubtasks() {
        subtasks.clear();
    }

    public void removeAllTasks() {
        allTasks.clear();
    }
}
