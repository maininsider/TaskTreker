package model;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subtasksIds = new ArrayList<>();

    //конструктор с id для целей тестирования
    public Epic(int id, String nameOfTask, String description) {
        super(id, nameOfTask, description);
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

    public void setSubtasksIds(ArrayList<Integer> subtasksIds) {
        this.subtasksIds = subtasksIds;
    }

    public void removeSubtaskIdById(Integer subtaskId) {
                if (subtasksIds.contains(subtaskId)) {
                subtasksIds.remove(subtaskId);
            } else {
                    System.out.println("Такого id нет в списке subtasksIds");
                }
    }
}
