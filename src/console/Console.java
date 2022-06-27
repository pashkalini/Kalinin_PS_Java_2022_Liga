package console;

import java.util.Scanner;

public class Console {
    private final Scanner scanner = new Scanner(System.in);

    public int[] consoleHandler() {
        int[] inputInfo = {-1, -1};
        String line = scanner.nextLine();

        // обработка операции вывода заданий пользователя - SHOW
        if (line.toUpperCase().startsWith(ConsoleOperation.SHOW.name())) {
            int id = idFromInput(line);

            if (id != -1) {
                inputInfo[0] = ConsoleOperation.SHOW.getOperationId();
                inputInfo[1] = id;
                return inputInfo;

            } else {
                System.out.println("Введите строку в формате: SHOW id=*идентификатор пользователя*");
                return inputInfo;
            }

            // обработка операции выхода - EXIT
        } else if (line.toUpperCase().startsWith(ConsoleOperation.EXIT.name())) {
            inputInfo[0] = ConsoleOperation.EXIT.getOperationId();
            return inputInfo;

            // обработка операции изменения статуса - EDIT_STATUS
        } else if (line.toUpperCase().startsWith(ConsoleOperation.EDIT_STATUS.name())) {
            int id = idFromInput(line);

            if (id != -1) {
                inputInfo[0] = ConsoleOperation.EDIT_STATUS.getOperationId();
                inputInfo[1] = id;
                return inputInfo;

            } else {
                System.out.println("Введите строку в формате: EDIT_STATUS id=*идентификатор пользователя*");
                return inputInfo;
            }
        }
        return inputInfo;

    }

    // вытаскивание id из строки, введенной пользователем
    public static int idFromInput(String input) {
        int id = -1;
        String[] splitString = input.split(" ");

        for (String s : splitString) {
            if (s.startsWith("id")) {
                return Integer.parseInt(s.split("=")[1]);
            }
        }
        return id;
    }

    // вспомогательная функция чтения ввода
    public String readLine() {
        return scanner.nextLine();
    }


}
