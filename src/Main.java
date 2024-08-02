import model.Epic;
import model.Subtask;
import model.Task;
import service.TaskManager;
import model.TaskStatus;

public class Main {
    public static void main (String[] args) {
        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Уборка", "Собрать и вынести мусор",  TaskStatus.NEW);
        Task task2 = new Task("Готовка", "Приготовить еду",  TaskStatus.NEW);
        Task task3 = new Task("Стирка", "Постирать вещи",  TaskStatus.NEW);
        Epic epic1 = new Epic("Поехать в отпуск", "Организовать путишествие");
        Epic epic2 = new Epic("Сделать ремонт", "Покрасить стены на балконе");
        Subtask subtask1 = new Subtask(10, 4,"Купить шпатель",
                "Выбрать в магазине шпатель и купить", TaskStatus.NEW);
        Subtask subtask2 = new Subtask(11, 4,"Купить краску",
                "Выбрать краску и купить", TaskStatus.DONE);
        Subtask subtask3 = new Subtask(12, 5,"Выбрать курорт",
                "Изучить варинты гостиниц и забронировать", TaskStatus.IN_PROGRESS);

        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);
        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.addSubtask(subtask3);

        System.out.println("Печатаем эпики");
        System.out.println(taskManager.epics);
        System.out.println("Печатаем подзадачи");
        System.out.println(taskManager.subtasks);
        System.out.println("Печатаем задачи");
        System.out.println(taskManager.tasks);

        taskManager.tasks.get(task1.getId()).setTaskStatus(TaskStatus.DONE);
        taskManager.tasks.get(task2.getId()).setTaskStatus(TaskStatus.DONE);
        taskManager.tasks.get(task3.getId()).setTaskStatus(TaskStatus.DONE);

        System.out.println("Статусы эпиков");
        System.out.println(epic1.getTaskStatus());
        System.out.println(epic2.getTaskStatus());

        System.out.println("Статусы подзадач");
        System.out.println(subtask1.getTaskStatus());
        System.out.println(subtask2.getTaskStatus());
        System.out.println(subtask3.getTaskStatus());

        System.out.println("Статусы задач");
        System.out.println(task1.getTaskStatus());
        System.out.println(task2.getTaskStatus());
        System.out.println(task3.getTaskStatus());

        System.out.println("Получнеие по id");
        System.out.println(taskManager.getTaskById(task3.getId()));
        System.out.println(taskManager.getEpicById(epic2.getId()));
        System.out.println(taskManager.getSubtaskById(subtask3.getId()));

        System.out.println("Получнеие всех задач");
        System.out.println(taskManager.getTasks());
        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubtasks());

        System.out.println("Обновление всех задач");
        System.out.println(taskManager.updateTask(task1));
        System.out.println(taskManager.updateEpic(epic1));
        System.out.println(taskManager.updateSubtask(subtask1));

        System.out.println("Эпики до удаления");
        System.out.println(taskManager.epics);
        System.out.println("Подзадачи до удаления");
        System.out.println(taskManager.subtasks);
        System.out.println("Задачи до удаения");
        System.out.println(taskManager.tasks);
        taskManager.removeEpicById(epic2.getId());
        taskManager.removeSubtaskById(subtask1.getId());
        taskManager.removeTaskById(task1.getId());
        System.out.println("Эпики после удаления по id");
        System.out.println(taskManager.epics);
        System.out.println("Подзадачи после удаления по id");
        System.out.println(taskManager.subtasks);
        System.out.println("Задачи после удаления по id");
        System.out.println(taskManager.tasks);

        System.out.println("Удаление всех задач");
        taskManager.removeTasks();
        taskManager.removeEpics();
        taskManager.removeSubtasks();

        System.out.println("Печатаем эпики");
        System.out.println(taskManager.epics);
        System.out.println("Печатаем подзадачи");
        System.out.println(taskManager.subtasks);
        System.out.println("Печатаем задачи");
        System.out.println(taskManager.tasks);
    }
}
