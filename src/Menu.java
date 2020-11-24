import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;
import static java.lang.System.*;
import java.io.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.scene.*;

public class Menu {
    public static Scanner scan = new Scanner(System.in);
    public static String Login, Password, NumFile;
    public static String UserFile, LoginFile, PasswordFile;
    public static boolean Flag = false, Input = false, Duplicate = true;
    // Main menu
    public static Stage stage = new Stage();
    public static void Entry() {


        Text auth = new Text("Авторизация: ");
        auth.setLayoutY(80);    // установка положения надписи по оси Y
        auth.setLayoutX(100);   // установка положения надписи по оси X

        Text txtLog = new Text("Login:");
        txtLog.setLayoutY(105);    // установка положения надписи по оси Y
        txtLog.setLayoutX(10);   // установка положения надписи по оси X
        TextField log = new TextField();
        log.setLayoutX(90);
        log.setLayoutY(90);

        Text txtPas = new Text("Password:");
        txtPas.setLayoutY(135);    // установка положения надписи по оси Y
        txtPas.setLayoutX(10);   // установка положения надписи по оси X
        PasswordField pas = new PasswordField();
        pas.setLayoutX(90);
        pas.setLayoutY(120);

        Button btnAuth = new Button("Enter");
        btnAuth.setLayoutY(160);    // установка положения надписи по оси Y
        btnAuth.setLayoutX(100);   // установка положения надписи по оси X
        btnAuth.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Login = log.getText();
                Password = pas.getText();
                Authorization();
            }
        });

        Button btnEx = new Button("Exit");
        btnEx.setLayoutY(400);    // установка положения надписи по оси Y
        btnEx.setLayoutX(200);   // установка положения надписи по оси X
        btnEx.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
        Button btnReg = new Button("reg");
        btnReg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Data.CreatePeople("User");
            }
        });

        Group group = new Group(auth, log, txtLog, pas, txtPas, btnAuth, btnReg, btnEx);
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.setTitle("Главное меню"); // установка заголовка
        stage.setWidth(400);
        stage.setHeight(750);

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
                out.println("Файл пустой");
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
            System.out.printf("File [%s] is not found.\n");
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
                        Laborant.work();
                        stage.hide();
                        break;
                    }
                }
            }
            else {                                          //Действия в случае неверно введенных данных
                if (Password.equals("")) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Поле пароля пустое", ButtonType.OK);
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.NONE, "Неверный логин ili пароль", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        } else {
            if (Login.equals("")) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Поле логин пустое", ButtonType.OK);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.NONE, "Неверный логин ili пароль", ButtonType.OK);
                alert.showAndWait();
            }
        }

    }
}