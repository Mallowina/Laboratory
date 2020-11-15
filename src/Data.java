import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.out;


public class Data {
    public static String ListOfPeople() {
        try (Scanner ScanPeop = new Scanner(new File("ListOfPeople.txt"))) {
            if (!ScanPeop.hasNext()) {
                return "File is empty";
            }
            while (ScanPeop.hasNextLine()) {
                out.println(ScanPeop.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File [%s] is not found.\n");
        }
        return "ok";
    }

    public static String QuantityPeople(String NameOfList) {
        int count = 0;
        try (Scanner ScanPeop = new Scanner(new File(NameOfList))) {
            while (ScanPeop.hasNextLine()) {
                ScanPeop.nextLine();
                count++;
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File [%s] is not found.\n");
        }

        return String.valueOf(++count);
    }

    public static void registPeop(String quan, String FIO, String date, String snils, String tel, String NameOfList) {
        try(FileOutputStream fos=new FileOutputStream(NameOfList, true))
        {
            fos.write(quan.getBytes());
            fos.write(" ".getBytes());
            fos.write(FIO.getBytes());
            fos.write(" ".getBytes());
            fos.write(date.getBytes());
            fos.write(" ".getBytes());
            fos.write(snils.getBytes());
            fos.write(" ".getBytes());
            fos.write(tel.getBytes());
            fos.write("\n".getBytes());
        }
        catch(IOException ex){
            out.println(ex.getMessage());
        }
    }
    public static void addLog(String log, String pas, String q) {
        try(FileOutputStream fos=new FileOutputStream("Authorization.txt", true))
        {
            fos.write(log.getBytes());
            fos.write(" ".getBytes());
            fos.write(pas.getBytes());
            fos.write(" ".getBytes());
            fos.write("Assistant ".getBytes());
            fos.write(q.getBytes());
            fos.write("\n".getBytes());
        }
        catch(IOException ex){
            out.println(ex.getMessage());
        }
    }
}
