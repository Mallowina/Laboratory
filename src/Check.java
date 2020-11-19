import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;

import static java.lang.System.out;

public class Check {
    public static String checkN(String word) {  //Метод проверки таких данных как ФИО
        Scanner scan = new Scanner(System.in);

        String regex = "[a-zA-Zа-яёА-ЯЁ]+";

        if (!word.matches(regex)) {
            out.println("Ошибка ввода. Введите заново. ");
            word = checkN(scan.next());
        }
        return word;
    }

    public static String checkSn(String snils) {   //Метод проверки СНИЛСа
        Scanner scan = new Scanner(System.in);

        String regex = "\\d+";

        if (snils.length() != 11 || !snils.matches(regex)) {
            out.println("Ошибка ввода. Введите заново. Количество символов не 11.");
            snils = checkSn(scan.next());
        }
        try (Scanner Scan = new Scanner(new File("ListOfPeople.txt"))) {
            while (Scan.hasNextLine()) {
                String text[] = Scan.nextLine().split(" ");
                if (snils.equals(text[0])) {
                    out.println("Такой СНИЛС уже существует.");
                    snils = checkSn(scan.next());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File [%s] is not found.\n");
        }
        return snils;
    }

    public static String checkLog(String log) {          //Метод проверки логина
        Scanner scan = new Scanner(System.in);

        try (Scanner Scan = new Scanner(new File("Authorization.txt"))) {
            while (Scan.hasNextLine()) {
                String text[] = Scan.nextLine().split(" ");
                if (log.equals(text[0])) {
                    out.println("Такой логин уже существует.");
                    log = checkLog(scan.next());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File [%s] is not found.\n");
        }
        return log;
    }

    public static String checkDate(String date) {  //Метод проверки даты
        Scanner scan = new Scanner(System.in);

        Pattern pattern = Pattern.compile("(0?[1-9]|[12][0-9]|3[01]).(0?[1-9]|1[012]).((19|20)\\d\\d)");
        Matcher matcher = pattern.matcher(date);

        if (!matcher.matches()) {
            out.println("Ошибка ввода. Введите заново. Проверьте правильность формата даты.");
            date = checkDate(scan.next());
        }
        return date;
    }

    public static String checkTel(String tel) {  //Метод проверки телефона
        Scanner scan = new Scanner(System.in);

        String regex = "^\\+?[78][-\\(]?\\d{3}\\)?-?\\d{3}-?\\d{2}-?\\d{2}$"; //допускает варианты:
                                                                              //89103123167
        if (!tel.matches(regex)) {                                            //+7-910-221-22-22
            out.println("Ошибка ввода. Введите заново. ");                    //+7(910)-221-22-22
            tel = checkTel(scan.next());                                      //ну и в таком духе
        }
        return tel;
    }
}