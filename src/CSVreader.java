import base.Status;
import base.Task;
import base.User;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CSVreader {

    private static final String USERS_CSV = "src/data/users.csv";
    private static final String TASKS_CSV = "src/data/tasks.csv";
    private final List<Task> tasks;
    private final List<User> users;

    public CSVreader() throws Exception {
        this.tasks = tasksList();
        this.users = usersList();
        addUserTasks();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<User> getUsers() {
        return users;
    }

    // поиск пользователя по id
    public Optional<User> findUserById(int id) {
        return users.stream()
                .filter(x -> x.getId() == id)
                .findFirst();
    }

    // поиск задания по id
    public Optional<Task> findTaskById(int id) {
        return tasks.stream()
                .filter(x -> x.getId() == id)
                .findFirst();
    }

    // создание списка пользователей из файла
    private List<User> usersList() throws Exception {
        List<User> users = new ArrayList<>();
        List<String> lines = Files.readAllLines(Path.of(USERS_CSV), StandardCharsets.UTF_8);

        for (String line: lines) {
            String[] newLine = line.split(", ");
            var user = new User();

            user.setId(Integer.parseInt(newLine[0]));
            user.setName(newLine[1]);
            users.add(user);
        }
        return users;
    }

    // создание списка заданий из файла
    private List<Task> tasksList() throws Exception {
        List<Task> tasks = new ArrayList<>();

        List<String> lines = Files.readAllLines(Path.of(TASKS_CSV), StandardCharsets.UTF_8);

        for (String line: lines) {
            String[] newLine = line.split(", ");
            var task = new Task();
            var dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            task.setId(Integer.parseInt(newLine[0]));
            task.setTitle(newLine[1]);
            task.setDescription(newLine[2]);
            task.setUserId(Integer.parseInt(newLine[3]));
            task.setDate(LocalDate.parse(newLine[4], dateFormat));
            task.setStatus(Status.NEW);

            tasks.add(task);
        }
        return tasks;
    }

    // добавление задания пользователю
    private void addUserTasks(){
        for (User user : users) {
            for (Task task : tasks) {
                if (task.getUserId() == user.getId()){
                    user.getTasks().add(task);
                }
            }
        }
    }
    }