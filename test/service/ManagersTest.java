package service;

import model.Task;
import model.TaskStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ManagersTest {
    Managers managers = new Managers();

    @Test
    void shouldGetDefaultInMemoryTaskManager() {
        TaskManager taskManager = Managers.getDefault();
        Task task = new Task("Уборка", "Собрать и вынести мусор",  TaskStatus.NEW);

        taskManager.addTask(task);
        assertNotNull(taskManager, "Некорректный экземпляр менеджера TaskManager");
        assertEquals(taskManager.getTaskById(task.getId()), task, "Метод созданного менеджера не сработал");
    }

    @Test
    void shouldGetDefaultHistoryManager() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        Task task = new Task("Уборка", "Собрать и вынести мусор",  TaskStatus.NEW);

        historyManager.add(task);
        assertNotNull(historyManager, "Некорректный эклемпляр HistoryManager");
        assertEquals(historyManager.getHistory().get(0), task, "Метод созданного менеджера не сработал");
    }
}
