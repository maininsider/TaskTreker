package service;

import model.Task;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {
    public ArrayList<Task> historyList = new ArrayList<>();
    private static final int MAX_HISTORY_SIZE = 10;

    @Override
    public void add(Task task) {
        if (historyList.size() == MAX_HISTORY_SIZE) {
            historyList.remove(0);
        }
        historyList.add(task);
    }

    @Override
    public ArrayList<Task> getHistory() {
        return new ArrayList<>(historyList);
    }
}
