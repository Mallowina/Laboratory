import java.io.*;
import java.util.Scanner;

import static java.lang.System.out;

public class User {
    public static Scanner scan = new Scanner(System.in);
    public static String FIO;
    public static String SNILS;

    public static void User(String fio, String snils){
        FIO = fio;
        SNILS = snils;

        out.println("\n\n —---------------------------------");
        out.println("Приветствуем " + fio + "!");// c входа
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("1.Оставить заявку на исследование пробирки");
        System.out.println("2.Просмотреть существующие анализы");
        System.out.println("3.Выйти");

        String Prob="";

        int action = 0;
        if (!scan.hasNextInt()) {
            out.println("Ты должен ввести число.\n");
            User(FIO, SNILS);
        } else action = scan.nextInt();

        switch (action) {
            case 1: {
                try(FileWriter writer = new FileWriter("Application.txt", true))
                {
                    System.out.println("Введите номер анализа");

                    //Добавить номер анализа

                    writer.write(Prob + " " + SNILS);
                    writer.append('\n');
                    System.out.println("Вы успешно подали заявку!");
                    writer.flush();
                    writer.close();
                    User(FIO, SNILS);
                }
                catch(IOException ex) {
                    System.out.println(ex.getMessage());
                }
                User(FIO, SNILS);
            }
            case 2: Data.List("analysis.txt"); //User(FIO, SNILS)
                try (Scanner Scan = new Scanner(new File("application.txt"))) {
                    while (Scan.hasNextLine()) {
                        String words[] = Scan.nextLine().split(" ");
                        if (words[1].equals(snils)) {
                            System.out.print("Пробирка "+ words[0]);
                            System.out.println(" Не одобрена!");
                        }
                    }
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                try (Scanner Scan = new Scanner(new File("Одобренные.txt"))) {
                    while (Scan.hasNextLine()) {
                        String worde[] = Scan.nextLine().split(" ");
                        if (worde[1].equals(snils)) {
                            System.out.print("Пробирка "+ worde[0]);
                            System.out.println(" Одобрена!");
                        }
                    }
                    User(FIO, SNILS);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            case 3: Menu.Entry();
            default: {
                out.println("Введи число соответсвующее списку\n");
                User(FIO, SNILS);
            }
        }
    }
}