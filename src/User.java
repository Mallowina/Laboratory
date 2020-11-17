import java.io.*;
import java.util.Scanner;

public class User {
    public static Scanner scan = new Scanner(System.in);
    public static void User(String fio){

        String SNILS = "SNILS";//////////////////////////////////////////////////////////////

        Scanner scan = new Scanner(System.in);
        System.out.println("Добро пожаловать "+ fio);// c  входа
        System.out.println(SNILS);
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("1.Оставить заявку на исследование пробирки");
        System.out.println("2.Просмотреть существующие анализы");
        System.out.println("3.Выйти");
        String N = scan.next();
        String Prob;

        if (N.equals("1")){
            try(FileWriter writer = new FileWriter("Prob.txt", true))
            {
                System.out.println("Введите номер пробирки");
                Prob = scan.next();
                writer.write(Prob + " " + SNILS);
                writer.append('\n');
                System.out.println("!Вы успешно подали заявку!");
                writer.flush();
                writer.close();
                User(fio);
            }
            catch(IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
        //записываем заявку

        if (N.equals("2")){
            try(FileReader reader = new FileReader("notes3.txt"))
            {
                // читаем посимвольно
                int c;
                while((c=reader.read())!=-1){

                    System.out.print((char)c);
                }
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
        }
        //смотрим документ анализов

        if (N.equals("3")){
            // выходим в вход
            Menu.Entry();
        }
    }
}