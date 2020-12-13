import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;
import java.io.*;

import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.text.*;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Menu {
    public static Scanner scan = new Scanner(System.in);
    public static String Login, Password, NumFile;
    public static String UserFile, LoginFile, PasswordFile;
    public static boolean Flag = false, Input = false, Duplicate = true;
    // Main menu
    public static Stage stage = new Stage();
    public static void Entry() {


        Text auth = new Text("Authorization: ");
        auth.setLayoutY(80);    // установка положения надписи по оси Y
        auth.setLayoutX(400);   // установка положения надписи по оси X
        auth.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 30));

        Text txtLog = new Text("Login:");
        txtLog.setLayoutY(140);    // установка положения надписи по оси Y
        txtLog.setLayoutX(370);   // установка положения надписи по оси X
        txtLog.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        TextField log = new TextField();
        log.setLayoutX(360);
        log.setLayoutY(115);
        log.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        //   @Override
        log.setOnMouseEntered(event -> {
            txtLog.setText("");
        });

        Text txtPas = new Text("Password:");
        txtPas.setLayoutY(190);    // установка положения надписи по оси Y
        txtPas.setLayoutX(370);   // установка положения надписи по оси X
        txtPas.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        PasswordField pas = new PasswordField();
        pas.setLayoutX(360);
        pas.setLayoutY(165);
        pas.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        pas.setOnMouseEntered(event -> {
            txtPas.setText("");
        });


        Button btnAuth = new Button("    Log in     ");
        btnAuth.setLayoutY(220);    // установка положения надписи по оси Y
        btnAuth.setLayoutX(420);   // установка положения надписи по оси X
        btnAuth.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        btnAuth.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Login = log.getText();
                Password = pas.getText();
                Authorization();
            }
        });

        Button btnEx = new Button("      Exit       ");
        btnEx.setLayoutY(320);    // установка положения надписи по оси Y
        btnEx.setLayoutX(420);   // установка положения надписи по оси X
        btnEx.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        btnEx.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });

        Button btnReg = new Button("   Register   ");
        btnReg.setLayoutY(265);    // установка положения надписи по оси Y
        btnReg.setLayoutX(420);   // установка положения надписи по оси X
        btnReg.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        btnReg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Data.CreatePeople("User");
            }
        });
        Button que = new Button("?");
        que.setLayoutY(50);    // установка положения надписи по оси Y
        que.setLayoutX(350);   // установка положения надписи по оси X
        que.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        que.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "If you are non-registered user - click Registration button", ButtonType.OK);
                alert.setTitle("About registration...");
                alert.showAndWait();
            }
        });


        Group group = new Group(que, auth, btnReg, log, txtLog, pas, txtPas, btnAuth, btnEx);
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.setTitle("Main menu"); // установка заголовка
        stage.setWidth(1000);
        stage.setHeight(450);
        stage.show();                   // отображение окна на экране

    }

    // Authorization user
    public static void Authorization() {
        ArrayList<String> Log = new ArrayList<String>();   //Переменные для поиска, куда загружаются данные из файла
        ArrayList<String> Pas = new ArrayList<String>();
        ArrayList<String> role = new ArrayList<String>();
        ArrayList<String> numPeop = new ArrayList<String>();


        try (Scanner Scan = new Scanner(new File("Authorization.txt"))) {
            if (!Scan.hasNext()) {
                Alert alert = new Alert(Alert.AlertType.NONE, "File is empty", ButtonType.OK);
                alert.showAndWait();
            }
            while (Scan.hasNextLine()) {
                String text = Scan.nextLine();
                String words[] = text.split(" ");
                Log.add(words[0]);
                Pas.add(words[1]);
                role.add(words[2]);
                numPeop.add(words[3]);
            }
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.NONE, "File [%s] is not found", ButtonType.OK);
            alert.showAndWait();
        }

        // Access check

        if (Log.contains(Login)) {    //Проверяем существует ли уже введенный логин
            int index = Log.indexOf(Login);
            if (Pas.get(index).equals(Password)) {  //Проверяем пароль от логина
                String Rol = role.get(index);
                String numF = numPeop.get(index);
                switch (Rol) {                  //Переходы на формы в зависимости от роли и снилса
                    case "Admin": {
                        Admin.main(Data.GetFIO(numF));
                        stage.hide();
                        break;
                    }
                    case "User": {
                        User.User(Data.GetFIO(numF), numF);
                        stage.hide();
                        break;
                    }
                    case "Assistant": {
                        Laborant.work(Data.GetFIO(numF));
                        stage.hide();
                        break;
                    }
                }
            }
            else {                                          //Действия в случае неверно введенных данных
                if (Password.equals("")) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "The Password field must be filled in", ButtonType.OK);
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.NONE, "The Password or Login are incorrect!", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        } else {
            if (Login.equals("")) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "The Login field must be filled in", ButtonType.OK);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.NONE, "The Password or Login are incorrect!", ButtonType.OK);
                alert.showAndWait();
            }
        }

    }
}