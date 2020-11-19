import static java.lang.System.*;
import java.io.*;
import java.util.*;

public class Admin {
    public static String FIO;
    public static void main(String fio) {
        FIO = fio;  //Переменная для вывозова главной функии из любого метода
        Scanner scan = new Scanner(System.in);

        out.println("\n\n -----------------------------------");
        out.println("Приветствуем " + fio + "!");
        out.println("Доступные действия: ");
        out.println("\t1. Просмотреть информацию о пользователях");
        out.println("\t2. Просмотреть информацию о лаборантах");
        out.println("\t3. Просмотреть информацию об администраторах");
        out.println("\t4. Добавить нового лаборанта");
        out.println("\t5. Добавить нового администратора");
        out.println("\t6. Выход");
        out.print("Для выполнения действия введите номер: ");

        int action = 0;
        if (!scan.hasNextInt()) {               //Проверка, что введено число
            out.println("Ты должен ввести число.\n");
            main(FIO);
        } else action = scan.nextInt();

        switch (action) {
            case 1: Data.SnilsRole("User"); main(FIO);     //Вызов метода выводящей инфромацию о пользователях с соответствующей ролью
            case 2: Data.SnilsRole("Assistant"); main(FIO);
            case 3: Data.SnilsRole("Admin"); main(FIO);
            case 4: {
                out.println("Для добавления нового лаборанта введите след. данные");
                Data.CreatePeople("Assistant");  //Вызов метода для создания нового пользователя с соответствующей ролью
                main(FIO);
            }
            case 5: {
                out.println("Для добавления нового администратора введите след. данные");
                Data.CreatePeople("Admin");
                main(FIO);
            }
            case 6: Menu.Entry();
            default: {
                out.println("Введи число соответсвующее списку\n");
                main(FIO);
            }
        }
    }

}
