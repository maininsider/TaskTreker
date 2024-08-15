package service;

import model.Task;
import model.Subtask;
import model.Epic;
import model.TaskStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {
    private int taskId = 0;
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private HistoryManager historyManager = Managers.getDefaultHistory();

    @Override
    public int generateNewId() {
        taskId++;
        return taskId;
    }

    @Override
    public Task addTask(Task task) {
        int newId = generateNewId();
        tasks.put(newId, task);
        task.setId(newId);
        return task;
    }

    @Override
    public Epic addEpic(Epic epic) {
        int newId = generateNewId();
        epics.put(newId, epic);
        epic.setId(newId);
        return epic;
    }

    @Override
    public Subtask addSubtask(Subtask subtask) {
        Epic epic = epics.get(subtask.getEpicId());
        if (subtask != null) {
            int newId = generateNewId();
            subtask.setEpicId(epic.getId());
            subtasks.put(newId, subtask);
            subtask.setId(newId);
            epic.setSubtaskId(newId);
            updateEpicStatus(epics.get(subtask.getEpicId()));
            return subtask;
        } else {
            return null;
        }
    }


    @Override
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

    @Override
    public Epic updateEpic(Epic epic) {
        if (epic != null) {
            int id = epic.getId();
            if (epics.containsKey(id)) {
                ArrayList<Integer> SubtasksIds = epics.get(id).getSubtasksIds();
                epics.put(id, epic);
                epic.setSubtasksIds(SubtasksIds);
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

    @Override
    public Subtask updateSubtask(Subtask subtask) {
        if (subtask != null) {
            int id = subtask.getId();
            if (subtasks.containsKey(id)) {
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

    @Override
    public Task getTaskById(int id) {
        if (tasks.containsKey(id)) {
            historyManager.checkingHistoryLength();
            historyManager.add(tasks.get(id));
            return tasks.get(id);
        } else {
            System.out.println("Задачи с данным id не существует");
        }
        return null;
    }

    @Override
    public Epic getEpicById(int id) {
        if (epics.containsKey(id)) {
            historyManager.checkingHistoryLength();
            historyManager.add(epics.get(id));
            return epics.get(id);
        } else {
            System.out.println("Эпика с данным id не существует");
        }
        return null;
    }

    @Override
    public Subtask getSubtaskById(int id) {
        if (subtasks.containsKey(id)) {
            historyManager.checkingHistoryLength();
            historyManager.add(subtasks.get(id));
            return subtasks.get(id);
        } else {
            System.out.println("Подзадачи с данным id не существует");
        }
        return null;
    }

    @Override
    public Collection<Task> getTasks() {
        for (Task task : tasks.values()) {
            historyManager.checkingHistoryLength();
            historyManager.add(task);
        }
        return tasks.values();
    }

    @Override
    public Collection<Epic> getEpics() {
        for (Epic epic : epics.values()) {
            historyManager.checkingHistoryLength();
            historyManager.add(epic);
        }
        return epics.values();
    }

    @Override
    public Collection<Subtask> getSubtasks() {
        for (Subtask subtask : subtasks.values()) {
            historyManager.checkingHistoryLength();
            historyManager.add(subtask);
        }
        return subtasks.values();
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    public Epic removeEpicById(int id) {
        if (epics.containsKey(id)) {
            Epic epic = epics.get(id);
            ArrayList<Integer> idsForRemove = epic.getSubtasksIds();

            for (int i : idsForRemove) {
                subtasks.remove(i);
            }
            epics.remove(id);
            return epic;
        } else {
            System.out.println("Эпика с данным id не существует");
            return null;
        }
    }

    @Override
    public void removeTasks() {
        tasks.clear();
    }

    @Override
    public void removeEpics() {
        epics.clear();
        subtasks.clear();
    }

    @Override
    public void removeSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.setTaskStatus(TaskStatus.NEW);
            epic.removeAllSubtasksIds();
        }
    }

    @Override
    public ArrayList<Task> getHistoryFromHistoryManager() {
        return historyManager.getHistory();
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
}
