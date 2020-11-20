import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

        Text adm = new Text("Что вы хотите сделать?");
        adm.setLayoutY(80);    // установка положения надписи по оси Y
        adm.setLayoutX(100);   // установка положения надписи по оси X

        Button btnList = new Button("ListOfUsers");
        btnList.setLayoutY(400);    // установка положения надписи по оси Y
        btnList.setLayoutX(200);   // установка положения надписи по оси X
        btnList.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label lblUser = new Label();
                Data.SnilsRole("User");
                lblUser.setText(String.join("\n", Data.list));
                Group group = new Group(lblUser);
                Scene scene = new Scene(group);
                ShowInfo.setScene(scene);
                ShowInfo.show();
                Data.list.clear();
            }
        });
        Button btnList2 = new Button("ListOfAssistant");
        btnList2.setLayoutY(420);    // установка положения надписи по оси Y
        btnList2.setLayoutX(200);   // установка положения надписи по оси X
        btnList2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label lbl = new Label();
                Data.SnilsRole("Assistant");
                lbl.setText(String.join("\n", Data.list));
                Group group = new Group(lbl);
                Scene scene = new Scene(group);
                ShowInfo.setScene(scene);
                ShowInfo.show();
                Data.list.clear();
            }
        });
        Button btnList3 = new Button("ListOfAdministrator");
        btnList3.setLayoutY(440);    // установка положения надписи по оси Y
        btnList3.setLayoutX(200);   // установка положения надписи по оси X
        btnList3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label lbl = new Label();
                Data.SnilsRole("Admin");
                lbl.setText(String.join("\n", Data.list));
                Group group = new Group(lbl);
                Scene scene = new Scene(group);
                ShowInfo.setScene(scene);
                ShowInfo.show();
                Data.list.clear();
            }
        });
        Button btnEx = new Button("Exit");
        btnEx.setLayoutY(460);    // установка положения надписи по оси Y
        btnEx.setLayoutX(200);   // установка положения надписи по оси X
        btnEx.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
        Button btnExAu = new Button("Return auth");
        btnExAu.setLayoutY(480);    // установка положения надписи по оси Y
        btnExAu.setLayoutX(200);   // установка положения надписи по оси X
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
        stage.setTitle("Администратор"); // установка заголовка
        stage.setWidth(400);
        stage.setHeight(750);

        stage.show();                   // отображение окна на экране

//        Scanner scan = new Scanner(System.in);
//
//        out.println("\n\n -----------------------------------");
//        out.println("Приветствуем " + fio + "!");
//        out.println("Доступные действия: ");
//        out.println("\t1. Просмотреть информацию о пользователях");    +
//        out.println("\t2. Просмотреть информацию о лаборантах");       +
//        out.println("\t3. Просмотреть информацию об администраторах"); +
//        out.println("\t4. Добавить нового лаборанта");
//        out.println("\t5. Добавить нового администратора");
//        out.println("\t6. Выход");
//        out.print("Для выполнения действия введите номер: ");
//
//        int action = 0;
//        if (!scan.hasNextInt()) {               //Проверка, что введено число
//            out.println("Ты должен ввести число.\n");
//            main(FIO);
//        } else action = scan.nextInt();
//
//        switch (action) {
//            case 1: Data.SnilsRole("User"); main(FIO);     //Вызов метода выводящей инфромацию о пользователях с соответствующей ролью
//            case 2: Data.SnilsRole("Assistant"); main(FIO);
//            case 3: Data.SnilsRole("Admin"); main(FIO);
//            case 4: {
//                out.println("Для добавления нового лаборанта введите след. данные");
//                Data.CreatePeople("Assistant");  //Вызов метода для создания нового пользователя с соответствующей ролью
//                main(FIO);
//            }
//            case 5: {
//                out.println("Для добавления нового администратора введите след. данные");
//                Data.CreatePeople("Admin");
//                main(FIO);
//            }
//            case 6: Menu.Entry();
//            default: {
//                out.println("Введи число соответсвующее списку\n");
//                main(FIO);
//            }
//        }
    }
}