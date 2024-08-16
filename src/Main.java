import model.Epic;
import model.Subtask;
import model.Task;
import service.HistoryManager;
import service.InMemoryHistoryManager;
import service.InMemoryTaskManager;
import model.TaskStatus;
import service.Managers;
import service.TaskManager;

public class Main {

    public static void main (String[] args) {
        TaskManager taskManager = Managers.getDefault();

        Task task1 = new Task("Уборка", "Собрать и вынести мусор",  TaskStatus.NEW);
        Task task2 = new Task("Готовка", "Приготовить еду",  TaskStatus.NEW);
        Task task3 = new Task("Стирка", "Постирать вещи",  TaskStatus.NEW);
        Epic epic1 = new Epic("Поехать в отпуск", "Организовать путешествие");
        Epic epic2 = new Epic("Сделать ремонт", "Покрасить стены на балконе");
        Subtask subtask1 = new Subtask(10, 5,"Купить шпатель",
                "Выбрать в магазине шпатель и купить", TaskStatus.NEW);
        Subtask subtask2 = new Subtask(11, 5,"Купить краску",
                "Выбрать краску и купить", TaskStatus.DONE);
        Subtask subtask3 = new Subtask(12, 4,"Выбрать курорт",
                "Изучить варинты гостиниц и забронировать", TaskStatus.IN_PROGRESS);

        Subtask subtaskForUpdate = new Subtask(8, 4,"Выбрать курорт",
                "Изучить варинты гостиниц и забронировать", TaskStatus.DONE);

        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);
        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.addSubtask(subtask3);

        /*System.out.println("Печатаем эпики");
        System.out.println(taskManager.getEpics());
        System.out.println("Печатаем подзадачи");
        System.out.println(taskManager.getSubtasks());
        System.out.println("Печатаем задачи");
        System.out.println(taskManager.getTasks());

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
        System.out.println(taskManager.getEpics());
        System.out.println("Подзадачи до удаления");
        System.out.println(taskManager.getSubtasks());
        System.out.println("Задачи до удаения");
        System.out.println(taskManager.getTasks());
        taskManager.removeEpicById(epic2.getId());
        taskManager.removeSubtaskById(subtask1.getId());
        taskManager.removeTaskById(task1.getId());
        System.out.println("Эпики после удаления по id");
        System.out.println(taskManager.getEpics());
        System.out.println("Подзадачи после удаления по id");
        System.out.println(taskManager.getSubtasks());
        System.out.println("Задачи после удаления по id");
        System.out.println(taskManager.getTasks());

        System.out.println("Удаление всех задач");
        taskManager.removeTasks();
        taskManager.removeEpics();
        taskManager.removeSubtasks();

        System.out.println("Печатаем эпики");
        System.out.println(taskManager.getEpics());
        System.out.println("Печатаем подзадачи");
        System.out.println(taskManager.getSubtasks());
        System.out.println("Печатаем задачи");
        System.out.println(taskManager.getTasks());*/

        /*taskManager.getTaskById(task1.getId()); //1
        taskManager.getTaskById(task2.getId()); //2
        taskManager.getTaskById(task2.getId()); //3
        taskManager.getTaskById(task2.getId()); //4
        taskManager.getTaskById(task2.getId()); //5
        taskManager.getTaskById(task2.getId()); //6
        taskManager.getTaskById(task2.getId()); //7
        taskManager.getTaskById(task2.getId()); //8
        taskManager.getTaskById(task2.getId()); //9
        taskManager.getTaskById(task2.getId()); //10
        taskManager.getTaskById(task2.getId()); //11

        System.out.println("Исторя просмотров:");
        System.out.println("Длина списка: " + taskManager.getHistoryFromHistoryManager().size());
        System.out.println(taskManager.getHistoryFromHistoryManager());*/

        printAllTasks((InMemoryTaskManager) taskManager);

        taskManager.updateSubtask(subtaskForUpdate);


    }

    private static void printAllTasks(InMemoryTaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : manager.getEpics()) {
            System.out.println(epic);

            for (Task task : manager.getSubtasksOfEpic(epic.getId())) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : manager.getSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("История:");
        for (Task task : manager.getHistoryFromHistoryManager()) {
            System.out.println(task);
        }
    }
}
