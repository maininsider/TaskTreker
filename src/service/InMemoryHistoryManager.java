package service;

import model.Task;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {
    public ArrayList<Task> historyList = new ArrayList<>();

    @Override
    public void add(Task task) {
        historyList.add(task);
    }

    @Override
    public ArrayList<Task> getHistory() {
        return historyList;
    }

    @Override
    public void checkingHistoryLength() {
        if (historyList.size() == 10)
            historyList.remove(0);
    }

}
