import model.Epic;
import model.Subtask;
import model.Task;
import service.TaskManager;
import service.TaskStatus;

public class Main {
    public static void main (String[] args) {
        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Уборка", "Собрать и вынести мусор",  TaskStatus.NEW);
        Task task2 = new Task("Готовка", "Приготовить еду",  TaskStatus.NEW);
        Task task3 = new Task("Стирка", "Постирать вещи",  TaskStatus.NEW);
        Epic epic1 = new Epic("Поехать в отпуск", "Организовать путишествие");
        Epic epic2 = new Epic("Сделать ремонт", "Покрасить стены на балконе");
        Subtask subtask1 = new Subtask("Купить шпатель",
                "Выбрать в магазине шпатель и купить", TaskStatus.NEW);
        Subtask subtask2 = new Subtask("Купить краску",
                "Выбрать краску и купить", TaskStatus.DONE);
        Subtask subtask3 = new Subtask("Выбрать курорт",
                "Изучить варинты гостиниц и забронировать", TaskStatus.IN_PROGRESS);


        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);
        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);

        taskManager.addSubtask(subtask1, epic2);
        taskManager.addSubtask(subtask2, epic2);
        taskManager.addSubtask(subtask3, epic1);


      System.out.println("Печатаем эпики");
        System.out.println(taskManager.epics);
        System.out.println("Печатаем подзадачи");
        System.out.println(taskManager.subtasks);
        System.out.println("Печатаем задачи");
        System.out.println(taskManager.tasks);

        taskManager.tasks.get(task1.getId()).setTaskStatus(TaskStatus.DONE);
        taskManager.tasks.get(task2.getId()).setTaskStatus(TaskStatus.DONE);
        taskManager.tasks.get(task3.getId()).setTaskStatus(TaskStatus.DONE);

        subtask1 = new Subtask("Купить шпатель",
                "Выбрать в магазине шпатель и купить", TaskStatus.DONE);
        subtask2 = new Subtask("Купить краску",
                "Выбрать краску и купить", TaskStatus.DONE);

        System.out.println("Печатаем эпики");
        System.out.println(taskManager.epics);
        System.out.println("Статус 1 эпика");
        System.out.println(epic1.getTaskStatus());
        System.out.println("Статус 2 эпика");
        System.out.println(epic2.getTaskStatus());

        System.out.println("Статусы подзадач");
        System.out.println(subtask1.getTaskStatus());
        System.out.println(subtask2.getTaskStatus());
        System.out.println(subtask3.getTaskStatus());

        System.out.println("Статусы задач");
        System.out.println(task1.getTaskStatus());
        System.out.println(task2.getTaskStatus());
        System.out.println(task3.getTaskStatus());

        System.out.println(taskManager.subtasks);
        System.out.println("Печатаем задачи");
        System.out.println(taskManager.tasks);

        System.out.println("Эпики до удаления");
        System.out.println(taskManager.epics);
        System.out.println("Подзадачи до удаления");
        System.out.println(taskManager.subtasks);
        System.out.println("Эпики после удаления");
        taskManager.removeEpicById(epic2.getId());
        System.out.println(taskManager.epics);
        System.out.println("Подзадачи после удаления");
        System.out.println(taskManager.subtasks);
    }
}
