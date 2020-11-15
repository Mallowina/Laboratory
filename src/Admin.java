import static java.lang.System.*;
import java.io.*;
import java.util.Scanner;

public class Admin {
    public static void main() {
        Scanner scan = new Scanner(System.in);

        out.println("Доступные действия: ");
        out.println("\t1. Просмотреть информацию о пользователях");
        out.println("\t2. Добавить нового лаборанта");
        out.println("\t3. Выход");
        out.print("Для выполнения действия введите номер: ");

        int action = 0;
        if (!scan.hasNextInt()) {
            out.println("Ты должен ввести число.\n");
            main();
        } else action = scan.nextInt();

        switch (action) {
            case 1: InfoPeople();
            case 2: CreatePeople();
            case 3: exit(0);
            default: {
                out.println("Введи число соответсвующее списку\n");
                main();
            }
        }
    }

    public static void InfoPeople() {
        String result = Data.ListOfPeople();
        if (result.equals("File is empty")) out.println("File is empty");
        else if (result.equals("ok")) out.println("ok");
        else out.println("Что-то пошло не так со списком (Data.ListOfPeople)");
        main();
    }
    public static void CreatePeople() {
        Scanner scan = new Scanner(System.in);
        String quantity = Data.QuantityPeople("ListOfAssistant.txt");
        out.println("Для добавления нового лаборанта введите след. данные");
        out.print("Фамилия: ");
        String FIO = scan.next();
        out.print("Имя: ");
        FIO += " " + scan.next();
        out.print("Отчество: ");
        FIO += " " + scan.next();
        out.print("Дата рождения (Пример: 12/01/2020): ");
        String date = scan.next();
        out.print("СНИЛС(Пример: 70955214100): ");
        String snils = scan.next();
        out.print("Номер телефона(Пример: +79029706364): ");
        String tel = scan.next();

        out.println("Для пользователя будет необходима авторизация. Введите данные");
        out.print("Логин: ");
        String log = scan.next();
        out.print("Пароль: ");
        String pas = scan.next();

        Data.registPeop(quantity, FIO, date, snils, tel, "ListOfAssistant.txt");
        Data.addLog(log, pas, quantity);

        main();
    }
}
