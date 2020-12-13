import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.css.*;
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
        adm.setLayoutY(60);    // установка положения надписи по оси Y
        adm.setLayoutX(580);   // установка положения надписи по оси X
        adm.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 30));

        Button btnList = new Button("Get list of users");
        btnList.setLayoutY(100);    // установка положения надписи по оси Y
        btnList.setLayoutX(655);   // установка положения надписи по оси X
        btnList.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        btnList.setId("btn");
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
        btnList.setOnMouseEntered(event ->{
            btnList.setLayoutX(btnList.getLayoutX()-20);
            btnList.setLayoutY(btnList.getLayoutY()-5);
        });
        btnList.setOnMouseExited(event ->{
            btnList.setLayoutX(btnList.getLayoutX()+20);
            btnList.setLayoutY(btnList.getLayoutY()+5);
        });

        Button btnList2 = new Button("    Get list of assistants    ");
        btnList2.setLayoutY(150);    // установка положения надписи по оси Y
        btnList2.setLayoutX(730);   // установка положения надписи по оси X
        btnList2.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        btnList2.setId("btn");
        btnList2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label lbl = new Label();
                lbl.setLayoutY(40);
                lbl.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
                Data.SnilsRole("Assistant");
                lbl.setText(String.join("\n", Data.list));
                Group group = new Group(lbl);
                Scene scene = new Scene(group);
                scene.getStylesheets().add(0, "styleButton.css");
                ShowInfo.setTitle("List of assistans");
                ShowInfo.setScene(scene);
                ShowInfo.show();
                Data.list.clear();
            }
        });
        Button btnReg1 = new Button("    Add assistant    ");
        btnReg1.setId("btn");
        btnReg1.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        btnReg1.setLayoutY(150);    // установка положения надписи по оси Y
        btnReg1.setLayoutX(525);   // установка положения надписи по оси X

        btnReg1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Data.CreatePeople("Assistant");
            }
        });
        btnReg1.setOnMouseEntered(event1 ->{
            btnReg1.setLayoutX(btnReg1.getLayoutX()-20);
            btnReg1.setLayoutY(btnReg1.getLayoutY()-5);
        });
        btnReg1.setOnMouseExited(event1 ->{
            btnReg1.setLayoutX(btnReg1.getLayoutX()+20);
            btnReg1.setLayoutY(btnReg1.getLayoutY()+5);
        });

        btnList2.setOnMouseEntered(event ->{
            btnList2.setLayoutX(btnList2.getLayoutX()-20);
            btnList2.setLayoutY(btnList2.getLayoutY()-5);
        });
        btnList2.setOnMouseExited(event ->{
            btnList2.setLayoutX(btnList2.getLayoutX()+20);
            btnList2.setLayoutY(btnList2.getLayoutY()+5);
        });

        Button btnList3 = new Button("Get list of administrators");
        btnList3.setLayoutY(200);    // установка положения надписи по оси Y
        btnList3.setLayoutX(730);   // установка положения надписи по оси X
        btnList3.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        btnList3.setId("btn");
        btnList3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label lbl = new Label();
              lbl.setLayoutY(40);
              lbl.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
                Data.SnilsRole("Admin");
                lbl.setText(String.join("\n", Data.list));
                Group group = new Group(lbl);
                Scene scene = new Scene(group);
                scene.getStylesheets().add(0, "styleButton.css");
                ShowInfo.setTitle("List of administrators");
                ShowInfo.setScene(scene);
                ShowInfo.show();
                Data.list.clear();
            }
        });
        Button btnReg2 = new Button("Add administrator");
        btnReg2.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        btnReg2.setId("btn");
        btnReg2.setLayoutY(200);    // установка положения надписи по оси Y
        btnReg2.setLayoutX(525);   // установка положения надписи по оси X
        btnReg2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Data.CreatePeople("Admin");
            }
        });
        btnReg2.setOnMouseEntered(event1 ->{
            btnReg2.setLayoutX(btnReg2.getLayoutX()-20);
            btnReg2.setLayoutY(btnReg2.getLayoutY()-5);
        });
        btnReg2.setOnMouseExited(event1 ->{
            btnReg2.setLayoutX(btnReg2.getLayoutX()+20);
            btnReg2.setLayoutY(btnReg2.getLayoutY()+5);
        });

        btnList3.setOnMouseEntered(event ->{
            btnList3.setLayoutX(btnList3.getLayoutX()-20);
            btnList3.setLayoutY(btnList3.getLayoutY()-5);
        });
        btnList3.setOnMouseExited(event ->{
            btnList3.setLayoutX(btnList3.getLayoutX()+20);
            btnList3.setLayoutY(btnList3.getLayoutY()+5);
        });

        Button btnEx = new Button("Exit");
       btnEx.setLayoutY(270);    // установка положения надписи по оси Y
        btnEx.setLayoutX(705);   // установка положения надписи по оси X
        btnEx.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        btnEx.setId("btn");
        btnEx.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
        btnEx.setOnMouseEntered(event ->{
            btnEx.setLayoutX(btnEx.getLayoutX()-5);
            btnEx.setLayoutY(btnEx.getLayoutY()-5);
        });
        btnEx.setOnMouseExited(event ->{
            btnEx.setLayoutX(btnEx.getLayoutX()+5);
            btnEx.setLayoutY(btnEx.getLayoutY()+5);
        });

        Button btnExAu = new Button("Return to authorization");
        btnExAu.setId("btn");
        btnExAu.setLayoutY(320);    // установка положения надписи по оси Y
        btnExAu.setLayoutX(620);   // установка положения надписи по оси X
        btnExAu.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        btnExAu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Menu.Entry();
                stage.hide();
            }
        });
        btnExAu.setOnMouseEntered(event ->{
            btnExAu.setLayoutX(btnExAu.getLayoutX()-20);
            btnExAu.setLayoutY(btnExAu.getLayoutY()-5);
        });
        btnExAu.setOnMouseExited(event ->{
            btnExAu.setLayoutX(btnExAu.getLayoutX()+20);
            btnExAu.setLayoutY(btnExAu.getLayoutY()+5);
        });


        BackgroundImage bgI = new BackgroundImage(new Image("background_image.jpg",1000,450,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        Pane root = new Pane(adm, btnList, btnList2, btnList3, btnEx, btnExAu, btnReg1, btnReg2);
        root.setBackground(new Background(bgI));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Administrator"); // установка заголовка
        stage.setWidth(1000);
        stage.setHeight(450);
        scene.getStylesheets().add(0, "styleButton.css");
        stage.show();                   // отображение окна на экране
        stage.setMaxWidth(1000);
        stage.setMinWidth(1000);
        stage.setMaxHeight(450);
        stage.setMinHeight(450);

    }
}