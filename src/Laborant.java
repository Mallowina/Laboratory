import java.util.*;
import java.io.*;

public class Laborant {
    public static void work(){
        boolean flag = false;
        while (flag == false) {
            System.out.println("_________________________________________________________________________");
            System.out.println("1.Обработать заявки\n2.Добавить пробирки на изучение\n3.Отправить результаты пациентам\n4.Выйти из меню лаборанта");
            System.out.println("-------------------------------------------------------------------------");
            Scanner scan = new Scanner(System.in);
            int A = scan.nextInt();
            switch (A){
                case 1:Laborant.Request();break;
                case 2:Laborant.Tube();break;
                case 3:Laborant.Information();break;
                case 4:flag = true;break;
                default:System.out.println("Вы ввели неверное значение!");
            }
        }
    }
    public static void Request(){
        ArrayList <String> Canceled = new ArrayList<String>();
        Scanner scan = new Scanner(System.in);
        try (Scanner TR = new Scanner(new File("application.txt"))) {
            if (!TR.hasNext()) {
                System.out.println("Файл пуст!");
            }
            while (TR.hasNextLine()) {
                String text = TR.nextLine();
                System.out.println(text);
                System.out.println("Одобрить заявку?\n1.Да\n2.Нет");
                int Act = scan.nextInt();
                boolean fl1 = false;
                while (fl1 == false){
                switch (Act)
                {
                    case 1:{ try(FileOutputStream writer = new FileOutputStream("Одобренные.txt", true))
                    {
                        writer.write(text.getBytes());
                        writer.write(" Статус:Одобрено".getBytes());
                        writer.write("\n".getBytes());
                    }
                    catch(IOException ex){

                        System.out.println(ex.getMessage());
                    }fl1=true;break;}
                    case 2:Canceled.add(text);fl1 = true; break;
                    default:System.out.println("Вы ввели некорректное действие");
                }}

            }
        } catch (FileNotFoundException e) {
            System.out.printf("File [%s] is not found.\n");
        }
        try(FileOutputStream writer = new FileOutputStream("application.txt", false))
        {
            writer.write("".getBytes());
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        try(FileOutputStream writer = new FileOutputStream("application.txt", true))
        {
            for (String PL : Canceled)
            {
                writer.write(PL.getBytes());
                writer.write("\n".getBytes());
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    public static void Tube(){
        ArrayList <String> Canceled = new ArrayList<String>();
        Scanner scan = new Scanner(System.in);
System.out.println("Выберите, какую пробирку отправить на анализ");
        try (Scanner TR = new Scanner(new File("Одобренные.txt"))) {
            if (!TR.hasNext()) {
                System.out.println("Файл пуст!");
            }
            while (TR.hasNextLine()) {
                String text = TR.nextLine();
                System.out.println(text);
                System.out.println("Добавить на анализ?\n1.Да\n2.Нет");
                int Act = scan.nextInt();

                    switch (Act)
                    {
                        case 1: try(FileOutputStream writer = new FileOutputStream("analysis.txt", true))
                        {
                            writer.write(text.getBytes());
                            writer.write("\n".getBytes());
                        }
                        catch(IOException ex){
                            System.out.println(ex.getMessage());
                        }break;
                        case 2: Canceled.add(text);break;
                        default:System.out.println("Вы ввели некорректное действие");
                    }
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File [%s] is not found.\n");
        }
        try(FileOutputStream writer = new FileOutputStream("Одобренные.txt", false))
        {
            writer.write("".getBytes());
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        try(FileOutputStream writer = new FileOutputStream("Одобренные.txt", true))
        {
            for (String PL : Canceled)
            {
                writer.write(PL.getBytes());
                writer.write("\n".getBytes());
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    public static void Information() {

        Scanner scan = new Scanner(System.in);
        try (Scanner TR = new Scanner(new File("analysis.txt"))) {
            if (!TR.hasNext()) {
                System.out.println("Файл пуст!");
            }
            while (TR.hasNextLine()) {
                String text = TR.nextLine();
                System.out.println(text);
            }
        }
        catch (FileNotFoundException e) {
                System.out.printf("File [%s] is not found.\n");
            }
        try (Scanner TR = new Scanner(new File("analysis.txt"))) {
            System.out.println("Введите номер человека, которому вы хотите отправить уведомление");
            String Pe = scan.next();
            while (TR.hasNextLine()) {
                String text = TR.nextLine();
                System.out.println(text);
                String words[] = text.split(" ");
                if (words[2].equals(Pe)){
                    try(FileOutputStream RTX = new FileOutputStream("Message.txt")){
                        RTX.write(text.getBytes());
                        RTX.write("\n".getBytes());
                    }
                        catch(IOException e){
                            System.out.println(e.getMessage());
                        }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File [%s] is not found.\n");
        }
    }

}
