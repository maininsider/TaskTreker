package modelTests;

import model.Task;
import model.TaskStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    Task task1 = new Task(1,"Уборка", "Собрать и вынести мусор",  TaskStatus.NEW);
    Task task2 = new Task(1,"Готовка", "Приготовить еду",  TaskStatus.NEW);

    @Test
    void tasksShouldBeEqualsIfIdEquals() {
        assertEquals(task1, task2, "Экземпляры Task не равны");
    }
}

