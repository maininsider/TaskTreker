package modelTests;

import model.Task;
import model.TaskStatus;
import service.Managers;
import service.TaskManager;

public class TaskTest {

    Task task1 = new Task(1,"Уборка", "Собрать и вынести мусор",  TaskStatus.NEW);
    Task task2 = new Task(1,"Готовка", "Приготовить еду",  TaskStatus.NEW);
    Task task3 = new Task(1,"Стирка", "Постирать вещи",  TaskStatus.NEW);


}

