import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.out;

public class Data {
    public static void List(String nameList) {
        try (Scanner Scan = new Scanner(new File(nameList))) {
            if (!Scan.hasNext()) {
                out.println("File is empty");
            }
            while (Scan.hasNextLine()) {
                out.println(Scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File [%s] is not found.\n");
        }
    }

    public static void SnilsRole (String role) {
        try (Scanner Scan = new Scanner(new File("Authorization.txt"))) {
            while (Scan.hasNextLine()) {
                String words[] = Scan.nextLine().split(" ");
                if (words[2].equals(role)) {
                    String snils = words[3];
                    try (Scanner ScanPeop = new Scanner(new File("ListOfPeople.txt"))) {
                        while (ScanPeop.hasNextLine()) {
                            String line = ScanPeop.nextLine();
                            if (line.contains(snils)) out.println(line);
                        }
                    } catch (FileNotFoundException e) {
                        System.out.printf("File [%s] is not found.\n");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File [%s] is not found.\n");
        }
    }

    public static String GetFIO(String snils) {
        try (Scanner Scan = new Scanner(new File("ListOfPeople.txt"))) {
            if (!Scan.hasNext()) {
                return "File is empty";
            }
            while (Scan.hasNextLine()) {
                String words[] = Scan.nextLine().split(" ");
                if (words[0].equals(snils)) {
                    String fio = words[1] + " " + words[2] + " " + words[3];
                    return fio;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File [%s] is not found.\n");
        }
        return "";
    }


//    public static String QuantityPeople(String NameOfList) {
//        int count = 0;
//        try (Scanner ScanPeop = new Scanner(new File(NameOfList))) {
//            while (ScanPeop.hasNextLine()) {
//                ScanPeop.nextLine();
//                count++;
//            }
//        } catch (FileNotFoundException e) {
//            System.out.printf("File [%s] is not found.\n");
//        }
//
//        return String.valueOf(++count);
//    }

    public static void registPeop(String FIO, String date, String snils, String tel) {
        try(FileOutputStream fos=new FileOutputStream("ListOfPeople.txt", true))
        {
            fos.write(snils.getBytes());
            fos.write(" ".getBytes());
            fos.write(FIO.getBytes());
            fos.write(" ".getBytes());
            fos.write(date.getBytes());
            fos.write(" ".getBytes());
            fos.write(tel.getBytes());
            fos.write("\n".getBytes());
        }
        catch(IOException ex){
            out.println(ex.getMessage());
        }
    }

    public static void addLog(String log, String pas, String role, String q) {
        try(FileOutputStream fos=new FileOutputStream("Authorization.txt", true))
        {
            fos.write(log.getBytes());
            fos.write(" ".getBytes());
            fos.write(pas.getBytes());
            fos.write(" ".getBytes());
            fos.write(role.getBytes());
            fos.write(" ".getBytes());
            fos.write(q.getBytes());
            fos.write("\n".getBytes());
        }
        catch(IOException ex){
            out.println(ex.getMessage());
        }
    }

    public static void CreatePeople(String role) {
        Scanner scan = new Scanner(System.in);

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

        out.print("Введите логин: ");
        String Login = Check.checkLog(scan.next());
        out.print("Введите пароль: ");
        String Password = scan.next();
        // Data recording
        Data.registPeop(FIO, date, snils, tel);
        Data.addLog(Login, Password, role, snils);

    }
}