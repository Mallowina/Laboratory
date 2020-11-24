import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;

import static java.lang.System.out;

public class Check {
    public static String checkN(String word) {  //Метод проверки таких данных как ФИО
        String regex = "[a-zA-Zа-яёА-ЯЁ]+";

        if (!word.matches(regex)) {
            Alert alert = new Alert(Alert.AlertType.NONE, "Неверно введены данные ФИО.", ButtonType.OK);
            alert.showAndWait();
            return "no";
        }
        return word;
    }

    public static String checkSn(String snils) {   //Метод проверки СНИЛСа
        String regex = "\\d+";

        if (snils.length() != 11 || !snils.matches(regex)) {
            Alert alert = new Alert(Alert.AlertType.NONE, "Неверно введены данные СНИЛС. Проверьте правильность ввода и количество символов", ButtonType.OK);
            alert.showAndWait();
            return "no";
        }
        try (Scanner Scan = new Scanner(new File("ListOfPeople.txt"))) {
            while (Scan.hasNextLine()) {
                String text[] = Scan.nextLine().split(" ");
                if (snils.equals(text[0])) {
                    Alert alert = new Alert(Alert.AlertType.NONE, "Такой СНИЛС уже используется", ButtonType.OK);
                    alert.showAndWait();
                    return "no";
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
                    Alert alert = new Alert(Alert.AlertType.NONE, "Такой логин уже существует", ButtonType.OK);
                    alert.showAndWait();
                    return "no";
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
            Alert alert = new Alert(Alert.AlertType.NONE, "Неверно введена дата", ButtonType.OK);
            alert.showAndWait();
            return "no";
        }
        return date;
    }

    public static String checkTel(String tel) {  //Метод проверки телефона
        Scanner scan = new Scanner(System.in);

        String regex = "^\\+?[78][-\\(]?\\d{3}\\)?-?\\d{3}-?\\d{2}-?\\d{2}$"; //допускает варианты:
                                                                              //89103123167
        if (!tel.matches(regex)) {                                            //+7-910-221-22-22
            Alert alert = new Alert(Alert.AlertType.NONE, "Неверно введен номер телефона", ButtonType.OK);
            alert.showAndWait();                                              //+7(910)-221-22-22
            return "no";                                                      //ну и в таком духе
        }
        return tel;
    }
}