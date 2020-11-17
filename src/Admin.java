import static java.lang.System.*;
<<<<<<< HEAD
import java.io.*;
=======
>>>>>>> Auth
import java.util.*;

public class Admin {
    public static void main() {
        Scanner scan = new Scanner(System.in);

        out.println("Доступные действия: ");
        out.println("\t1. Просмотреть информацию о пользователях");
        out.println("\t2. Просмотреть информацию о лаборантах");
        out.println("\t3. Добавить нового лаборанта");
        out.println("\t4. Выход");
        out.print("Для выполнения действия введите номер: ");

        int action = 0;
        if (!scan.hasNextInt()) {
            out.println("Ты должен ввести число.\n");
            main();
        } else action = scan.nextInt();

        switch (action) {
            case 1: InfoPeople("ListOfPeople.txt");
            case 2: InfoPeople("ListOfAssistant.txt");
            case 3: CreatePeople();
            case 4: exit(0);
            default: {
                out.println("Введи число соответсвующее списку\n");
                main();
            }
        }
    }

    public static void InfoPeople(String nameList) {
        String result = Data.List(nameList);
        if (result.equals("File is empty")) out.println("File is empty");
        else out.println("Что-то пошло не так со списком (Data.ListOfPeople)");
        main();
    }
    public static void CreatePeople() {
        Scanner scan = new Scanner(System.in);
        String quantity = Data.QuantityPeople("ListOfAssistant.txt");
        out.println("Для добавления нового лаборанта введите след. данные");
        out.print("Фамилия: ");
        String FIO = Check.checkN(scan.next());
        out.print("Имя: ");
        FIO += " " + Check.checkN(scan.next());
        out.print("Отчество: ");
        FIO += " " + Check.checkN(scan.next());
        out.print("Дата рождения (Пример: 02.01.2020): "); //10
        String date = Check.checkDate(scan.next());
        out.print("СНИЛС(Пример: 70955214100): ");
        String snils = Check.checkSn(scan.next());
        out.print("Номер телефона(Примеры: \n+79029706364\n89103123167\n+7(910)-221-22-22\n+7-910-221-22-22): ");
        String tel = Check.checkTel(scan.next());

        out.println("Для пользователя будет необходима авторизация. Введите данные");
        out.print("Логин: ");
        String log = scan.next();
        out.print("Пароль: ");
        String pas = scan.next();

        Data.registPeop(quantity, FIO, date, snils, tel, "ListOfAssistant.txt");
        Data.addLog(log, pas, quantity);

        main();
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> Auth
