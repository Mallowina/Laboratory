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
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.out;

public class Laborant {
    public static String FIO;
    public static ArrayList<String> Canceled = new ArrayList<String>();
    public static void work(String fio) {


//        Scanner scan = new Scanner(System.in);

        Stage stage = new Stage();
        FIO = fio;
        Text greeting = new Text("Hello, " + fio + "!"); // c  входа
        greeting.setLayoutY(80);    // установка положения надписи по оси Y
        greeting.setLayoutX(100);   // установка положения надписи по оси X
        greeting.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 30));

        Text actVar = new Text("What do you want to do?");
        actVar.setLayoutY(120);    // установка положения надписи по оси Y
        actVar.setLayoutX(100);    // установка положения надписи по оси X
        actVar.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));

        Button request = new Button("To process requests");
        request.setLayoutY(140);    // установка положения надписи по оси Y
        request.setLayoutX(100);    // установка положения надписи по оси X
        request.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));

        request.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage2 = new Stage();
                stage2.setWidth(650);
                stage2.setHeight(500);

                Text Od = new Text("Approval of requests"); // c  входа
                Od.setLayoutY(80);    // установка положения надписи по оси Y
                Od.setLayoutX(100);   // установка положения надписи по оси X
                Od.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 30));

                Text req = new Text("");
                req.setLayoutY(140);    // установка положения надписи по оси Y
                req.setLayoutX(100);    // установка положения надписи по оси X
                req.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));

                Label lb = new Label();
                lb.setLayoutY(160);    // установка положения надписи по оси Y
                lb.setLayoutX(100);    // установка положения надписи по оси X
                lb.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));

                final Button yes = new Button("Approve");
                yes.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
                yes.setLayoutY(200);    // установка положения надписи по оси Y
                yes.setLayoutX(100);    // установка положения надписи по оси X


                final Button no = new Button(" Reject ");
                no.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
                no.setLayoutY(200);    // установка положения надписи по оси Y
                no.setLayoutX(220);    // установка положения надписи по оси X


                Group group1 = new Group(Od, req, lb, yes, no);
                Scene scene1 = new Scene(group1);
                stage2.setScene(scene1);

                stage2.show();

                Data.List("application.txt");
                AtomicInteger i = new AtomicInteger(0);
                lb.setText(Data.list.get(0));


                yes.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try (FileOutputStream writer = new FileOutputStream("Одобренные.txt", true)) {
                            writer.write(lb.getText().getBytes());
                            writer.write(" Одобрено".getBytes());
                            writer.write("\n".getBytes());
                        } catch (IOException ex) {
                            Alert alert = new Alert(Alert.AlertType.NONE, ex.getMessage(), ButtonType.OK);
                            alert.showAndWait();
                        }

                        int num = i.get() + 1;
                        if (num == Data.list.size()) {
                            Alert alert = new Alert(Alert.AlertType.NONE, "End of list", ButtonType.OK);
                            alert.showAndWait();
                            Clear("application.txt");
                            stage2.close();
                        } else {
                            req.setText("Approve?");
                            lb.setText(Data.list.get(num));
                            i.set(num);
                        }
                    }
                });

                no.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        int num = i.get() + 1;
                        if (num == Data.list.size()) {
                            Alert alert = new Alert(Alert.AlertType.NONE, "End of list", ButtonType.OK);
                            alert.showAndWait();
                            Canceled.add(lb.getText());
                            Clear("application.txt");
                            stage2.close();
                        } else {
                            req.setText("Approve?");
                            Canceled.add(lb.getText());
                            lb.setText(Data.list.get(num));
                            i.set(num);
                        }
                    }
                });
            }
        });

        Button add = new Button("To add tubes for analysis");
        add.setLayoutY(180);    // установка положения надписи по оси Y
        add.setLayoutX(100);    // установка положения надписи по оси X
        add.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage2 = new Stage();
                stage2.setWidth(650);
                stage2.setHeight(500);

                Text Od = new Text("Approval of requests"); // c  входа
                Od.setLayoutY(80);    // установка положения надписи по оси Y
                Od.setLayoutX(100);   // установка положения надписи по оси X
                Od.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 30));

                Text req = new Text("");
                req.setLayoutY(140);    // установка положения надписи по оси Y
                req.setLayoutX(100);    // установка положения надписи по оси X
                req.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));

                Label lb = new Label();
                lb.setLayoutY(160);    // установка положения надписи по оси Y
                lb.setLayoutX(100);    // установка положения надписи по оси X
                lb.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));

                final Button yes = new Button("Approve");
                yes.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
                yes.setLayoutY(200);    // установка положения надписи по оси Y
                yes.setLayoutX(100);    // установка положения надписи по оси X


                final Button no = new Button(" Reject ");
                no.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
                no.setLayoutY(200);    // установка положения надписи по оси Y
                no.setLayoutX(220);    // установка положения надписи по оси X


                Group group1 = new Group(Od, req, lb, yes, no);
                Scene scene1 = new Scene(group1);
                stage2.setScene(scene1);

                stage2.show();

                Data.List("Одобренные.txt");
                AtomicInteger i = new AtomicInteger(0);
                lb.setText(Data.list.get(0));


                yes.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try (FileOutputStream writer = new FileOutputStream("processing.txt", true)) {
                            writer.write(lb.getText().getBytes());
                            writer.write("\n".getBytes());
                        } catch (IOException ex) {
                            Alert alert = new Alert(Alert.AlertType.NONE, ex.getMessage(), ButtonType.OK);
                            alert.showAndWait();
                        }

                        int num = i.get() + 1;
                        if (num == Data.list.size()) {
                            Alert alert = new Alert(Alert.AlertType.NONE, "End of list", ButtonType.OK);
                            alert.showAndWait();
                            Clear("Одобренные.txt");
                            stage2.close();
                        } else {
                            req.setText("Approve?");
                            lb.setText(Data.list.get(num));
                            i.set(num);
                        }
                    }
                });

                no.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        int num = i.get() + 1;
                        if (num == Data.list.size()) {
                            Alert alert = new Alert(Alert.AlertType.NONE, "End of list", ButtonType.OK);
                            alert.showAndWait();
                            Canceled.add(lb.getText());
                            Clear("Одобренные.txt");
                            stage2.close();
                        } else {
                            req.setText("Approve?");
                            Canceled.add(lb.getText());
                            lb.setText(Data.list.get(num));
                            i.set(num);
                        }
                    }
                });
            }
        });

        Button sendRes = new Button("To send results to users");    //УБРАТЬ КНОПКУ, ПОЛЬЗОВАТЕЛЬ БУДЕТ ПОЛУЧАТЬ СООБЩЕНИЕ ИЗ ГОТОВЫХ АНАЛИЗОВ
        sendRes.setLayoutY(220);    // установка положения надписи по оси Y
        sendRes.setLayoutX(100);    // установка положения надписи по оси X
        sendRes.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        sendRes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {



/*

                try (Scanner TR = new Scanner(new File("analysis.txt"))) {
                    System.out.println("Введите номер человека, которому вы хотите отправить уведомление");
                    String Pe = scan.next();
                    while (TR.hasNextLine()) {
                        String text = TR.nextLine();
                        System.out.println(text);
                        String words[] = text.split(" ");
                        if (words[2].equals(Pe)) {
                            try (FileOutputStream RTX = new FileOutputStream("Message.txt")) {
                                RTX.write(text.getBytes());
                                RTX.write("\n".getBytes());
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                } catch (FileNotFoundException e) {
                    Alert alert = new Alert(Alert.AlertType.NONE, "File [%s] is not found.", ButtonType.OK);
                    alert.showAndWait();
                }
*/
            }
        });

        Button exit = new Button("Exit to main menu");
        exit.setLayoutY(260);    // установка положения надписи по оси Y
        exit.setLayoutX(100);    // установка положения надписи по оси X
        exit.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.hide();
                Menu.Entry();
            }
        });

        Group group = new Group(greeting, actVar, request, add, sendRes, exit);
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.setTitle("User"); // установка заголовка
        stage.setWidth(700);
        stage.setHeight(500);
        stage.show(); // отображение окна на экране
    }

    public static void Clear(String nameTXT) {
        try (FileOutputStream writer = new FileOutputStream(nameTXT, false)) {
            writer.write("".getBytes());
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.NONE, ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
        try (FileOutputStream writer = new FileOutputStream(nameTXT, true)) {
            for (String PL : Canceled) {
                writer.write(PL.getBytes());
                writer.write("\n".getBytes());
            }
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.NONE, ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
        Canceled.clear();
        Data.list.clear();
    }
}
