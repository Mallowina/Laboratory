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

public class Admin {
    public static String FIO;
    public static void main(String fio) {
        FIO = fio;  //Переменная для вывозова главной функии из любого метода

        Stage stage = new Stage();
        Stage ShowInfo = new Stage();
        ShowInfo.setHeight(800);
        ShowInfo.setWidth(650);

        Text adm = new Text("What do you want to do?");
        adm.setLayoutY(80);    // установка положения надписи по оси Y
        adm.setLayoutX(100);   // установка положения надписи по оси X
        adm.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 30));

        Button btnList = new Button("Get list of users");
        btnList.setLayoutY(120);    // установка положения надписи по оси Y
        btnList.setLayoutX(100);   // установка положения надписи по оси X
        btnList.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        btnList.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label lblUser = new Label();
                lblUser.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
                Data.SnilsRole("User");
                lblUser.setText(String.join("\n", Data.list));
                Group group = new Group(lblUser);
                Scene scene = new Scene(group);
                ShowInfo.setTitle("List of users");
                ShowInfo.setScene(scene);
                ShowInfo.show();
                Data.list.clear();
            }
        });
        Button btnList2 = new Button("Get list of assistants");
        btnList2.setLayoutY(160);    // установка положения надписи по оси Y
        btnList2.setLayoutX(100);   // установка положения надписи по оси X
        btnList2.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        btnList2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label lbl = new Label();
                lbl.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
                Data.SnilsRole("Assistant");
                lbl.setText(String.join("\n", Data.list));
                Group group = new Group(lbl);
                Scene scene = new Scene(group);
                ShowInfo.setTitle("List of assistans");
                ShowInfo.setScene(scene);
                ShowInfo.show();
                Data.list.clear();
            }
        });
        Button btnList3 = new Button("Get list of administrators");
        btnList3.setLayoutY(200);    // установка положения надписи по оси Y
        btnList3.setLayoutX(100);   // установка положения надписи по оси X
        btnList3.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        btnList3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label lbl = new Label();
                lbl.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
                Data.SnilsRole("Admin");
                lbl.setText(String.join("\n", Data.list));
                Group group = new Group(lbl);
                Scene scene = new Scene(group);
                ShowInfo.setTitle("List of administrators");
                ShowInfo.setScene(scene);
                ShowInfo.show();
                Data.list.clear();
            }
        });
        Button btnEx = new Button("Exit");
        btnEx.setLayoutY(280);    // установка положения надписи по оси Y
        btnEx.setLayoutX(100);   // установка положения надписи по оси X
        btnEx.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        btnEx.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
        Button btnExAu = new Button("Return to authorization");
        btnExAu.setLayoutY(320);    // установка положения надписи по оси Y
        btnExAu.setLayoutX(100);   // установка положения надписи по оси X
        btnExAu.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        btnExAu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Menu.Entry();
                stage.hide();
            }
        });

        Group group = new Group(adm, btnList, btnList2, btnList3, btnEx, btnExAu);
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.setTitle("Administrator"); // установка заголовка
        stage.setWidth(600);
        stage.setHeight(450);


        stage.show();
        // отображение окна на экране

    }
}