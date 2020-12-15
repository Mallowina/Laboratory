import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static java.lang.System.*;
import java.io.*;
import java.util.*;

import static java.lang.System.out;

public class User {
    public static Scanner scan = new Scanner(System.in);
    public static String FIO;
    public static String SNILS;

    public static void User(String fio, String snils){
        FIO = fio;
        SNILS = snils;

        Stage stage = new Stage();

        Text greeting = new Text("Hello, "+fio+"!"); // c  входа
        greeting.setLayoutY(80);    // установка положения надписи по оси Y
        greeting.setLayoutX(100);   // установка положения надписи по оси X
        greeting.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 30));

        Text actVar = new Text("What do you want to do?");
        actVar.setLayoutY(120);    // установка положения надписи по оси Y
        actVar.setLayoutX(100);    // установка положения надписи по оси X
        actVar.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));

        Button request = new Button("Submit a request for tube testing");
        request.setLayoutY(140);    // установка положения надписи по оси Y
        request.setLayoutX(100);    // установка положения надписи по оси X
        request.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        String Prob;
        request.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.hide();

                Text Ask = new Text("Input number of tube");
                Ask.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
                Ask.setX(100);
                Ask.setY(80);

                TextField numTube = new TextField();
                numTube.setLayoutX(100);
                numTube.setLayoutY(120);

                Button send = new Button("Send");
                send.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
                send.setLayoutY(160);    // установка положения надписи по оси Y
                send.setLayoutX(100);   // установка положения надписи по оси X

                Stage stage1 = new Stage();
                stage1.setWidth(650);
                stage1.setHeight(500);
                Group group1 = new Group(Ask, numTube, send);
                Scene scene1 = new Scene(group1);
                stage1.setScene(scene1);

                send.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String L = numTube.getText();
                        try(FileOutputStream writer = new FileOutputStream("application.txt", true))
                            {
                                if (!L.equals("")) {
                                    writer.write(numTube.getText().getBytes());
                                    writer.write(" ".getBytes());
                                    writer.write(SNILS.getBytes());
                                    writer.write("\n".getBytes());
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have successfully submitted your application", ButtonType.OK);
                                    alert.setTitle("Success!");
                                    alert.showAndWait();
                                    stage1.hide();
                                    stage.show();
                                }
                                else {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please, enter a number of analysis!", ButtonType.OK);
                                    alert.setTitle("Fail!");
                                    alert.showAndWait();
                                }
                            }
                            catch(IOException ex) {
                                Alert alert = new Alert(Alert.AlertType.NONE, ex.getMessage(), ButtonType.OK);
                                alert.showAndWait();
                            }
                        }
                    });
                stage1.show();
            }
        });

        Button view = new Button("View existing analyses");
        view.setLayoutY(180);    // установка положения надписи по оси Y
        view.setLayoutX(100);    // установка положения надписи по оси X
        view.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));

        view.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage2 = new Stage();
                stage2.setWidth(650);
                stage2.setHeight(500);

                Data.List("analysis.txt");
                Label lb = new Label();
                lb.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
                lb.setText(String.join("\n", Data.list));
                Data.list.clear();

                Group group1 = new Group(lb);
                Scene scene1 = new Scene(group1);
                stage2.setScene(scene1);

                stage2.show();

            }
        });

        Button exit = new Button("Exit to menu");
        exit.setLayoutY(220);    // установка положения надписи по оси Y
        exit.setLayoutX(100);    // установка положения надписи по оси X
        exit.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.hide();
                Menu.Entry();
            }
        });

        Group group = new Group(greeting, actVar, request, view, exit);
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.setTitle("User"); // установка заголовка
        stage.setWidth(650);
        stage.setHeight(500);

        stage.show(); // отображение окна на экране
    }
}