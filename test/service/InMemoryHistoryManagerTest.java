package service;

import model.Epic;
import model.Subtask;
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

        Epic epic = new Epic(1,"Сделать ремонт", "Покрасить стены на балконе");
        historyManager.add(epic);

        Subtask subtask = new Subtask(10, 1,"Купить шпатель",
                "Выбрать в магазине шпатель и купить", TaskStatus.NEW);
        historyManager.add(subtask);

        assertNotNull(historyManager.getHistory().get(0), "Задача не добавилась в историю.");
        assertEquals(task, historyManager.getHistory().get(0), "Задачи не совпадают");

        assertNotNull(historyManager.getHistory().get(1), "Эпик не добавился в историю.");
        assertEquals(epic, historyManager.getHistory().get(1), "Эпики не совпадают");

        assertNotNull(historyManager.getHistory().get(2), "Подзадачи не добавилась в историю.");
        assertEquals(subtask, historyManager.getHistory().get(2), "Подзадачи не совпадают");
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

    @Test
    void shouldSavePreviousVersionOfTask() {
        Task task1 = new Task("Уборка", "Собрать и вынести мусор",  TaskStatus.NEW);
        Task task2 = new Task("Готовка", "Приготовить еду",  TaskStatus.NEW);
        Task task3 = new Task("Стирка", "Постирать вещи",  TaskStatus.NEW);

        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(task3);

        Task task1FromHistory = historyManager.getHistory().get(0);
        Task task2FromHistory = historyManager.getHistory().get(1);

        assertNotNull(task1FromHistory);
        assertNotNull(task2FromHistory);
        assertEquals(task1, task1FromHistory);
        assertEquals(task2, task2FromHistory);
    }
}
