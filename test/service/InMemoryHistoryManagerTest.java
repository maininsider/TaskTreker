package service;

import model.Task;
import model.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InMemoryHistoryManagerTest {
    HistoryManager historyManager;

    @BeforeEach
    public void beforeEach() {
        historyManager = Managers.getDefaultHistory();
    }

    @Test
    void shouldAddTaskToHistoryList() {
        Task task = new Task("Уборка", "Собрать и вынести мусор",  TaskStatus.NEW);
        historyManager.add(task);
        assertNotNull(historyManager.getHistory().get(0), "Задача не добавилась в историю.");
    }

    @Test
    void shouldGetHistory() {
        Task task = new Task("Уборка", "Собрать и вынести мусор",  TaskStatus.NEW);
        historyManager.add(task);
        assertNotNull(historyManager.getHistory().get(0), "История не получгена.");
    }

    @Test
    void shouldCheckingHistoryLength() {
        Task task = new Task("Уборка", "Собрать и вынести мусор",  TaskStatus.NEW);
        for (int i = 0; i <= 15; i++) {
            historyManager.checkingHistoryLength();
            historyManager.add(task);
        }
        int historyListSize = historyManager.getHistory().size();

        assertTrue(10 >= historyListSize, "В историю добалвено болье 10 задач");
    }
}
