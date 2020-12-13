import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;

public class Check {
    public static String checkN(String word) {  //Метод проверки таких данных как ФИО
        String regex = "[a-zA-Zа-яёА-ЯЁ]+";

        if (!word.matches(regex)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Input surname, name or patronymic again!", ButtonType.OK);
            alert.setTitle("Input error");
            alert.showAndWait();
            return "no";
        }
        return word;
    }

    public static String checkSn(String snils) {   //Метод проверки СНИЛСа
        String regex = "\\d+";

        if (snils.length() != 11 || !snils.matches(regex)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Input again! Number of symbols isn't equal 11", ButtonType.OK);
            alert.setTitle("Input error");
            alert.showAndWait();
            return "no";
        }
        try (Scanner Scan = new Scanner(new File("ListOfPeople.txt"))) {
            while (Scan.hasNextLine()) {
                String text[] = Scan.nextLine().split(" ");
                if (snils.equals(text[0])) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "This INIPA already exist!", ButtonType.OK);
                    alert.showAndWait();
                    return "no";
                }
            }
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "File [%s] is not found!", ButtonType.OK);
            alert.setTitle("File reading error");
            alert.showAndWait();
        }
        return snils;
    }

    public static String checkLog(String log) {          //Метод проверки логина
        Scanner scan = new Scanner(System.in);

        try (Scanner Scan = new Scanner(new File("Authorization.txt"))) {
            while (Scan.hasNextLine()) {
                String text[] = Scan.nextLine().split(" ");
                if (log.equals(text[0])) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "This login already exist!", ButtonType.OK);
                    alert.setTitle("Login error");
                    alert.showAndWait();
                    return "no";
                }
            }
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "File [%s] is not found!", ButtonType.OK);
            alert.setTitle("File reading error");
            alert.showAndWait();
        }
        return log;
    }

    public static String checkDate(String date) {  //Метод проверки даты
        Scanner scan = new Scanner(System.in);

        Pattern pattern = Pattern.compile("(0?[1-9]|[12][0-9]|3[01]).(0?[1-9]|1[012]).((19|20)\\d\\d)");
        Matcher matcher = pattern.matcher(date);

        if (!matcher.matches()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Input again! Check that the date format is correct ", ButtonType.OK);
            alert.setTitle("Input error");
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Input again!", ButtonType.OK);      //+7(910)-221-22-22
            alert.setTitle("Input error");                                                               //ну и в таком духе
            alert.showAndWait();                                            //+7(910)-221-22-22
            return "no";                                                      //ну и в таком духе
        }
        return tel;
    }
}