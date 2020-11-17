import java.util.Scanner;
import java.lang.String;
import static java.lang.System.*;
import java.io.*;

public class Menu {
    public static Scanner scan = new Scanner(System.in);
    public static int NumFile;
    public static String User = null, Login, Password, Entrance;
    public static String UserFile, LoginFile, PasswordFile;
    public static boolean Flag = false, Input = false, Duplicate = true;
    // Main menu
    public static void Entry() {
        System.out.println("-------------------------------------------");
        System.out.println("Главное меню лаборатории");
        System.out.println("\"Чтобы вернуться в главное меню введите: 0\"");
        System.out.println("1 (Авторизация)");
        System.out.println("2 (Регистрация)");
        System.out.print("Вы хотите: ");
        Entrance = scan.next();
        if (Entrance.equals("1")) {
            Menu.Authorization();
        } else if (Entrance.equals("2")) {
            Menu.Registration();
        } else Menu.Entry();
    }
    // Authorization user
    public static void Authorization() {
        // Access check
        System.out.println("-------------------------------------------");
        System.out.println("(Авторизация)");
        while (!Flag) {
            System.out.print("Введите логин: ");
            Login = scan.next();
            if (Login.equals("0")) Menu.Entry();
            System.out.print("Введите пароль: ");
            Password = scan.next();
            if (Password.equals("0")) Menu.Entry();
            // Reading file
            try (Scanner Peop = new Scanner(new File("Authorization.txt"))) {
                while (Peop.hasNext()) {
                    LoginFile = Peop.next();
                    PasswordFile = Peop.next();
                    User = Peop.next();
                    NumFile = Peop.nextInt();
                    if (LoginFile.equals(Login) && PasswordFile.equals(Password)) {
                        System.out.println("!Авторизация выполнена успешно!");
                        System.out.println("-------------------------------------------");
                        Input = true;
                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("!Файла нет, зарегистрируйтесь!");
            }

            if (!Input) {
                System.out.println("!Логин или пароль неверный!");
                System.out.println("-------------------------------------------");
            } else {
                switch (User) {
                    case "Admin": Flag = true; Admin.main(); break;
//                    case "Client": Flag = true; user.User(); break;
//                    case "Assistant": Flag = true; Assistant.AssistantProfile(); break;
                }
            }
        }
    }
    // Registration user
    public static void Registration() {
        System.out.println("-------------------------------------------");
        System.out.println("(Регистрация)");
        String quantity = Data.QuantityPeople("ListOfPeople.txt");
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

        do {
            System.out.print("Введите логин: ");
            Login = scan.next();
            if (Login.equals("0")) Menu.Entry();
            // Duplicate check
            try (Scanner Peop = new Scanner(new File("Authorization.txt"))) {
                while (Peop.hasNext()) {
                    LoginFile = Peop.next();
                    PasswordFile = Peop.next();
                    UserFile = Peop.next();
                    NumFile = Peop.nextInt();
                    if (LoginFile.equals(Login)) {
                        System.out.println("!Такой логин уже существует!");
                        System.out.println("-------------------------------------------");
                        Duplicate = false;
                        break;
                    }
                }
                Duplicate = !Duplicate;
            } catch (FileNotFoundException e) {
                System.out.println("Попробуйте ещё раз!");
                System.out.println("-------------------------------------------");
                try(FileWriter writer = new FileWriter("Authorization.txt", false))
                {
                    writer.append("\n");
                    writer.flush();
                }
                catch(IOException ex) {
                    System.out.println(ex.getMessage());
                }
                Duplicate = true;
            }
        } while (Duplicate);

        out.print("Введите пароль: ");
        Password = scan.next();
        // Data recording
        Data.registPeop(quantity, FIO, date, snils, tel, "ListOfPeople.txt");
        Data.addLog(Login, Password, quantity);
        out.println("!Вы успешно зарегистрировались!");
        Menu.Entry();
    }
}