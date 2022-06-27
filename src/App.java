import base.Status;
import base.Task;
import base.User;
import console.Console;
import console.ConsoleOperation;

import java.util.*;


public class App {
    public static void main(String[] args) throws Exception {
        new App(new CSVreader()).run();
    }

    private CSVreader data;
    private Console console;

    public App(CSVreader data) {
        this.console = new Console();
        this.data = data;

    }

    public void run() {
        System.out.println("Введите строку в формате: *SHOW/EDIT_STATUS* id=*идентификатор пользователя/задания*,где\n" +
                "SHOW - показать список заданий пользователя,\n" +
                "EDIT_STATUS - изменение статуса выполнения задания.\n" +
                "Для выхода используйте операцию EXIT.");

        while (true) {
            int[] inputInfo = console.consoleHandler();

            if (inputInfo[0] == -1) {
                System.out.println("Введите строку в верном формате");
                continue;
            }

            // проверка операции на существование
            var operationCheck = getOperationById(inputInfo[0]);
            if (operationCheck.isEmpty()) {
                System.out.println("Операция не распознана, введите строку в верном формате");
                continue;
            }

            switch (operationCheck.get()) {
                case SHOW -> showUserTasks(inputInfo[1]);
                case EDIT_STATUS -> changeTaskStatus(inputInfo[1]);
                case EXIT -> System.exit(0);
            }
        }
    }

    // вывод заданий пользователя - операция SHOW
    private void showUserTasks(int userId) {
        Optional<User> userCheck = data.findUserById(userId);

        if (userCheck.isPresent()) {
            userCheck.get().getTasks().stream()
                    .forEach(System.out::println);
        } else {
            System.out.printf("Пользователь с id = %d не существует", userId);
        }
    }

    // изменение статуса задачи - операция EDIT_STATUS
    private void changeTaskStatus(int taskId) {
        Optional<Task> taskCheck = data.findTaskById(taskId);
        if (taskCheck.isEmpty()) {
            System.out.printf(" Задача с id = %d не существует  ", taskId);
            return;
        }
        Task currentTask = taskCheck.get();

        while (true) {
            System.out.println("Введите новый статус для задачи: new - новая, work - в работе, ready - выполнено");
            String newStatusInput = console.readLine();
            //StringJoiner joiner = new StringJoiner("", "[", "]");
            List<String> keyWords = new ArrayList<>();

            for (Status status: Status.values()) {
                String keyword = status.getKeyword();
                keyWords.add(keyword);
            }

            //String pattern = joiner.toString();

            for (String key: keyWords) {
                if (Objects.equals(newStatusInput, key)) {
                    Status newStatus = Arrays.stream(Status.values())
                            .filter(s -> s.getKeyword().equals(newStatusInput))
                            .findFirst()
                            .get();
                    currentTask.setStatus(newStatus);
                    System.out.printf("Статус задачи с id = %d изменен\n", taskId);
                    return;
                }
            }
        }
    }

    // получение операции по её id
    private Optional<ConsoleOperation> getOperationById(int id) {
        return Arrays.stream(ConsoleOperation.values())
                .filter(x -> x.getOperationId() == id)
                .findFirst();
    }
}