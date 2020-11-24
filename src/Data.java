import java.io.*;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static java.lang.System.out;

public class Data {
    public static ArrayList<String>list = new ArrayList<>();
    public static void List(String nameList) {          //Метод выводящий все данные из файла
        try (Scanner Scan = new Scanner(new File(nameList))) {
            if (!Scan.hasNext()) {
                out.println("File is empty");
            }
            while (Scan.hasNextLine()) {
                list.add(Scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File [%s] is not found.\n");
        }
    }

    public static void SnilsRole (String role) {   //Метод выводящий людей с определенной ролью
        try (Scanner Scan = new Scanner(new File("Authorization.txt"))) {
            while (Scan.hasNextLine()) {
                String words[] = Scan.nextLine().split(" ");
                if (words[2].equals(role)) {
                    String snils = words[3];
                    try (Scanner ScanPeop = new Scanner(new File("ListOfPeople.txt"))) {
                        while (ScanPeop.hasNextLine()) {
                            String line = ScanPeop.nextLine();
                            if (line.contains(snils)) list.add(line);
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

    public static String GetFIO(String snils) {         //Метод для получения имени по СНИЛСу
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

    public static void registPeop(String FIO, String date, String snils, String tel) { //Метод записи в файл данных о пользователе
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

    public static void addLog(String log, String pas, String role, String q) { //Метод записи в файл данных регистрации
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

    public static void CreatePeople(String role) {  //Метод получения данных о пользователе и отправление их на запись
        Stage stage = new Stage();

        Text txtF = new Text("Фамилия:");
        txtF.setLayoutY(10);    // установка положения надписи по оси Y
        txtF.setLayoutX(10);   // установка положения надписи по оси X
        TextField f1 = new TextField();
        f1.setLayoutX(120);
        f1.setLayoutY(10);
        Text txtN = new Text("Имя:");
        txtN.setLayoutY(30);    // установка положения надписи по оси Y
        txtN.setLayoutX(10);   // установка положения надписи по оси X
        TextField f2 = new TextField();
        f2.setLayoutX(120);
        f2.setLayoutY(30);
        Text txtOt = new Text("Отчество:");
        txtOt.setLayoutY(60);    // установка положения надписи по оси Y
        txtOt.setLayoutX(10);   // установка положения надписи по оси X
        TextField f3 = new TextField();
        f3.setLayoutX(120);
        f3.setLayoutY(60);
        Text txtDate = new Text("Дата рождения (Пример: 02.01.2020):");
        txtDate.setLayoutY(90);    // установка положения надписи по оси Y
        txtDate.setLayoutX(10);   // установка положения надписи по оси X
        TextField f4 = new TextField();
        f4.setLayoutX(120);
        f4.setLayoutY(90);
        Text txtSn = new Text("СНИЛС(Пример: 70955214100):");
        txtSn.setLayoutY(120);    // установка положения надписи по оси Y
        txtSn.setLayoutX(10);   // установка положения надписи по оси X
        TextField f5 = new TextField();
        f5.setLayoutX(120);
        f5.setLayoutY(120);
        Text txtTel = new Text("Номер телефона(Пример: +79029706364");
        txtTel.setLayoutY(150);    // установка положения надписи по оси Y
        txtTel.setLayoutX(10);   // установка положения надписи по оси X
        TextField f6 = new TextField();
        f6.setLayoutX(120);
        f6.setLayoutY(150);

        Button btnCon = new Button("Продолжить");
        btnCon.setLayoutY(440);    // установка положения надписи по оси Y
        btnCon.setLayoutX(200);   // установка положения надписи по оси X
        btnCon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String F, I, O, date, snils, tel, all;
                F = Check.checkN(f1.getText());
                I = Check.checkN(f2.getText());
                O = Check.checkN(f3.getText());
                date = Check.checkDate(f4.getText());
                snils = Check.checkSn(f5.getText());
                tel = Check.checkTel(f6.getText());
                all = F + I + O + date + snils + tel;

                if (!all.contains("no")) {
                    Text txtLog = new Text("Login:");
                    txtLog.setLayoutY(10);    // установка положения надписи по оси Y
                    txtLog.setLayoutX(10);   // установка положения надписи по оси X
                    TextField log = new TextField();
                    log.setLayoutX(90);
                    log.setLayoutY(10);
                    Text txtPas = new Text("Password:");
                    txtPas.setLayoutY(30);    // установка положения надписи по оси Y
                    txtPas.setLayoutX(10);   // установка положения надписи по оси X
                    TextField pas = new TextField();
                    pas.setLayoutX(120);
                    pas.setLayoutY(30);



                    Button btnReg = new Button("Registration");
                    btnReg.setLayoutY(440);    // установка положения надписи по оси Y
                    btnReg.setLayoutX(200);   // установка положения надписи по оси X
                    btnReg.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String Login = Check.checkLog(log.getText());
                            String Password = pas.getText();
                            if (!Login.equals("no")) {
                                String FIO = F + " " + I + " " + O;
                                Data.registPeop(FIO, date, snils, tel);
                                Data.addLog(Login, Password, role, snils);
                                stage.hide();
                            }
                        }
                    });


                    Group group = new Group(txtLog, txtPas, log, pas, btnReg);
                    Scene scene = new Scene(group);
                    stage.setScene(scene);
                }
            }
        });

        Group group = new Group(txtF, txtN, txtOt, txtDate, txtSn, txtTel, f1, f2,f3,f4,f5,f6, btnCon);
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.setTitle("Регистрация"); // установка заголовка
        stage.setWidth(400);
        stage.setHeight(750);
        stage.show();
    }
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