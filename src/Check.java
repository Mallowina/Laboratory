import java.util.Scanner;
import java.util.regex.*;

import static java.lang.System.out;

public class Check {
    public static String checkN(String word) {
        Scanner scan = new Scanner(System.in);

        String regex = "[a-zA-Zа-яёА-ЯЁ]+";

        if (!word.matches(regex)) {
            out.println("Ошибка ввода. Введите заново. ");
            word = checkN(scan.next());
        }
        return word;
    }

    public static String checkSn(String snils) {
        Scanner scan = new Scanner(System.in);

        String regex = "\\d+";

        if (snils.length() != 11 || !snils.matches(regex)) {
            out.println("Ошибка ввода. Введите заново. Количество символов не 11.");
            snils = checkSn(scan.next());
        }
        return snils;
    }

    public static String checkDate(String date) {
        Scanner scan = new Scanner(System.in);

        Pattern pattern = Pattern.compile("(0?[1-9]|[12][0-9]|3[01]).(0?[1-9]|1[012]).((19|20)\\d\\d)");
        Matcher matcher = pattern.matcher(date);

        if (!matcher.matches()) {
            out.println("Ошибка ввода. Введите заново. Проверьте правильность формата даты.");
            date = checkDate(scan.next());
        }
        return date;
    }

    public static String checkTel(String tel) {
        Scanner scan = new Scanner(System.in);

        String regex = "^\\+?[78][-\\(]?\\d{3}\\)?-?\\d{3}-?\\d{2}-?\\d{2}$"; //допускает варианты:
<<<<<<< HEAD
                                                                              //89103123167
=======
        //89103123167
>>>>>>> Auth
        if (!tel.matches(regex)) {                                            //+7-910-221-22-22
            out.println("Ошибка ввода. Введите заново. ");                    //+7(910)-221-22-22
            tel = checkTel(scan.next());                                      //ну и в таком духе
        }
        return tel;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> Auth
