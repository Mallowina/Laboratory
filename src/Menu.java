import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;
import static java.lang.System.*;
import java.io.*;

public class Menu {
    public static Scanner scan = new Scanner(System.in);
    public static int NumFile;
    public static String Login, Password;
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
        int Entrance = 0;
        if (!scan.hasNextInt()) {
            out.println("Ты должен ввести число.\n");
            Entry();
        } else Entrance = scan.nextInt();

        switch (Entrance) {
            case 1: Authorization();
            case 2: Registration();
            case 3: exit(0);
            default: {
                out.println("Введи число соответсвующее списку\n");
                Entry();
            }
        }

    }
    // Authorization user
    public static void Authorization() {
        ArrayList<String> Log = new ArrayList<String>();
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

        if (Log.contains(Login)) {
            int index = Log.indexOf(Login);
            out.print("Введите пароль: ");
            Password = scan.next();
            if (Pas.get(index).equals(Password)) {
                String Rol = role.get(index);
                String numF = numPeop.get(index);
                switch (Rol) {
                    case "Admin": {
                        Admin.main(Data.GetFIO(numF));
                        break;
                    }
                    case "User": {
                        User.User(Data.GetFIO(numF));
                    }
                    case "Assistant": {
                        Laborant.work();
                    }
                }
            }
            else {
                out.println("Неверный пароль.");
                Entry();
            }
        } else {
            out.println("Неверный логин.");
            Entry();
        }

    }
    // Registration user
    public static void Registration() {
        System.out.println("-------------------------------------------");
        System.out.println("(Регистрация)");
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
        Data.registPeop(FIO, date, snils, tel);
        Data.addLog(Login, Password, "User", snils);
        out.println("!Вы успешно зарегистрировались как пользователь!");
        Menu.Entry();
    }
}