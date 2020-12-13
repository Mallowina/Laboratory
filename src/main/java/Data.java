import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import static java.lang.System.out;

public class Data {
    public static ArrayList<String>list = new ArrayList<>();
    public static void List(String nameList) {          //Метод выводящий все данные из файла
        try (Scanner Scan = new Scanner(new File(nameList))) {
            if (!Scan.hasNext()) {
                Alert alert = new Alert(Alert.AlertType.NONE, "File is empty.", ButtonType.OK);
                alert.showAndWait();
            }
            while (Scan.hasNextLine()) {
                list.add(Scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.NONE, "File [%s] is not found.", ButtonType.OK);
            alert.showAndWait();
        }
    }//Как мне его вывести?

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
                        Alert alert = new Alert(Alert.AlertType.NONE, "File [%s] is not found.", ButtonType.OK);
                        alert.showAndWait();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.NONE, "File [%s] is not found.", ButtonType.OK);
            alert.showAndWait();
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
            Alert alert = new Alert(Alert.AlertType.NONE, "File [%s] is not found.", ButtonType.OK);
            alert.showAndWait();
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
        Text reg = new Text("Registration");
        reg.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
        reg.setX(410);
        reg.setY(30);

        Text txtF = new Text("Surname:");
        txtF.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        txtF.setLayoutY(70);    // установка положения надписи по оси Y
        txtF.setLayoutX(240);   // установка положения надписи по оси X

        TextField f1 = new TextField();
        f1.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        f1.setLayoutX(260);
        f1.setLayoutY(85);

        Text txtN = new Text("Name:");
        txtN.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        txtN.setLayoutY(150);    // установка положения надписи по оси Y
        txtN.setLayoutX(240);   // установка положения надписи по оси X

        TextField f2 = new TextField();
        f2.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        f2.setLayoutX(260);
        f2.setLayoutY(165);

        Text txtOt = new Text("Patronymic:");
        txtOt.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        txtOt.setLayoutY(230);    // установка положения надписи по оси Y
        txtOt.setLayoutX(240);   // установка положения надписи по оси X

        TextField f3 = new TextField();
        f3.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        f3.setLayoutX(260);
        f3.setLayoutY(245);

        Text txtDate = new Text("Date of birth (Пример: 02.01.2020):");
        txtDate.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        txtDate.setLayoutY(310);    // установка положения надписи по оси Y
        txtDate.setLayoutX(240);   // установка положения надписи по оси X

        TextField f4 = new TextField();
        f4.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        f4.setLayoutX(260);
        f4.setLayoutY(315);

        Text txtSn = new Text("INIPA (Example: 70955214100):");
        txtSn.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        txtSn.setLayoutY(390);    // установка положения надписи по оси Y
        txtSn.setLayoutX(240);   // установка положения надписи по оси X

        TextField f5 = new TextField();
        f5.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        f5.setLayoutX(260);
        f5.setLayoutY(405);

        Text txtTel = new Text("Phone number (Пример: +79029706364):");
        txtTel.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        txtTel.setLayoutY(470);    // установка положения надписи по оси Y
        txtTel.setLayoutX(240);   // установка положения надписи по оси X

        TextField f6 = new TextField();
        f6.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        f6.setLayoutX(260);
        f6.setLayoutY(485);

        Button btnCon = new Button("Continue");
        btnCon.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        btnCon.setLayoutY(560);    // установка положения надписи по оси Y
        btnCon.setLayoutX(450);   // установка положения надписи по оси X
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

                    Text reg = new Text("Registration");
                    reg.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
                    reg.setX(420);
                    reg.setY(30);

                    Text txtLog = new Text("Login:");
                    txtLog.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
                    txtLog.setLayoutY(70);    // установка положения надписи по оси Y
                    txtLog.setLayoutX(250);   // установка положения надписи по оси X

                    TextField log = new TextField();
                    log.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
                    log.setLayoutX(270);
                    log.setLayoutY(85);

                    Text txtPas = new Text("Password:");
                    txtPas.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
                    txtPas.setLayoutY(150);    // установка положения надписи по оси Y
                    txtPas.setLayoutX(250);   // установка положения надписи по оси X

                    TextField pas = new TextField();
                    pas.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
                    pas.setLayoutX(270);
                    pas.setLayoutY(165);

                    Button btnReg = new Button("Register");
                    btnReg.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
                    btnReg.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
                    btnReg.setLayoutY(250);    // установка положения надписи по оси Y
                    btnReg.setLayoutX(450);    // установка положения надписи по оси X
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

                    Group group = new Group(reg, txtLog, txtPas, log, pas, btnReg);
                    Scene scene = new Scene(group);
                    stage.setScene(scene);
                }
            }
        });

        Group group = new Group(txtF, txtN, txtOt, txtDate, txtSn, txtTel, f1, f2,f3,f4,f5,f6, btnCon, reg);
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.setTitle("Registration"); // установка заголовка
        stage.setWidth(1000);
        stage.setHeight(700);
        stage.show();
    }
}