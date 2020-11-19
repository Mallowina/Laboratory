import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;
import static java.lang.System.*;
import java.io.*;

public class Menu {
    public static Scanner scan = new Scanner(System.in);
    public static String Login, Password, NumFile;
    public static String UserFile, LoginFile, PasswordFile;
    public static boolean Flag = false, Input = false, Duplicate = true;
    // Main menu
    public static void Entry() {
        out.println("-------------------------------------------");
        out.println("Главное меню лаборатории");
        out.println("\"Чтобы вернуться в главное меню введите: 0\"");
        out.println("1. Авторизация");
        out.println("2. Регистрация");
        out.println("3. Выход");
        out.print("Введите число соответствующее действию: ");
        int Entrance = 0;                       //Проверка, что введено число
        if (!scan.hasNextInt()) {
            out.println("Ты должен ввести число.\n");
            Entry();
        } else Entrance = scan.nextInt();

        switch (Entrance) {                 //Выбор действия из списка
            case 1: Authorization();
            case 2: {
                System.out.println("-------------------------------------------");
                System.out.println("(Регистрация)");
                Data.CreatePeople("User");                  //Вызов "формы" регистрации
                out.println("!Вы успешно зарегистрировались как пользователь!");
                Menu.Entry();
            }
            case 3: exit(0);
            default: {
                out.println("Введи число соответсвующее списку\n");
                Entry();
            }
        }

    }
    // Authorization user
    public static void Authorization() {
        ArrayList<String> Log = new ArrayList<String>();   //Переменные для поиска, куда загружаются данные из файла
        ArrayList<String> Pas = new ArrayList<String>();
        ArrayList<String> role = new ArrayList<String>();
        ArrayList<String> numPeop = new ArrayList<String>();


        try (Scanner Scan = new Scanner(new File("Authorization.txt"))) {
            if (!Scan.hasNext()) {
                out.println("Файл пустой");
            }
            while (Scan.hasNextLine()) {
                String text = Scan.nextLine();
                String words[] = text.split(" ");
                Log.add(words[0]);
                Pas.add(words[1]);
                role.add(words[2]);
                numPeop.add(words[3]);
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File [%s] is not found.\n");
        }

        // Access check
        out.println("-------------------------------------------");
        out.println("(Авторизация)");
        out.print("Введите логин: ");
        Login = scan.next();

        if (Log.contains(Login)) {    //Проверяем существует ли уже введенный логин
            int index = Log.indexOf(Login);
            out.print("Введите пароль: ");
            Password = scan.next();
            if (Pas.get(index).equals(Password)) {  //Проверяем пароль от логина
                String Rol = role.get(index);
                String numF = numPeop.get(index);
                switch (Rol) {                  //Переходы на формы в зависимости от роли и снилса
                    case "Admin": {
                        Admin.main(Data.GetFIO(numF));
                        break;
                    }
                    case "User": {
                        User.User(Data.GetFIO(numF), numF);
                    }
                    case "Assistant": {
                        Laborant.work();
                    }
                }
            }
            else {                                          //Действия в случае неверно введенных данных
                out.println("Неверный пароль.");
                Authorization();
            }
        } else {
            out.println("Неверный логин.");
            Authorization();
        }

    }
}