package model;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subtasksIds = new ArrayList<>();

    //конструктор с id для целей тестирования
    public Epic(int id, String nameOfTask, String description, ArrayList<Integer> subtasksIds) {
        super(id, nameOfTask, description);
        this.subtasksIds = subtasksIds;
    }

    public Epic(String nameOfTask, String description) {
        super(nameOfTask, description);
    }

    public void setSubtaskId(int id) {
        subtasksIds.add(id);
    }

    public void removeAllSubtasksIds(){
        subtasksIds.clear();
    }

    public ArrayList<Integer> getSubtasksIds() {
        return subtasksIds;
    }

    public void removeSubtasksId(Integer subtaskId) {
        for (Integer id : subtasksIds) {
            if (id.equals(subtaskId)) {
                subtasksIds.remove(id);
            }
        }
    }
}
