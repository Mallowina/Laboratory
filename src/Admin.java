import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
        Button btnReg1 = new Button("Добавить лаборанта");
        btnReg1.setLayoutY(500);    // установка положения надписи по оси Y
        btnReg1.setLayoutX(200);   // установка положения надписи по оси X
        btnReg1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Data.CreatePeople("Assistant");
            }
        });
        Button btnReg2 = new Button("Добавть администратора");
        btnReg2.setLayoutY(520);    // установка положения надписи по оси Y
        btnReg2.setLayoutX(200);   // установка положения надписи по оси X
        btnReg2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Data.CreatePeople("Admin");
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

        Group group = new Group(adm, btnList, btnList2, btnList3, btnEx, btnExAu, btnReg1, btnReg2);
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.setTitle("Администратор"); // установка заголовка
        stage.setWidth(400);
        stage.setHeight(750);

        stage.show();                   // отображение окна на экране
    }
}