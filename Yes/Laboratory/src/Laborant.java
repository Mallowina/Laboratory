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

public class Laborant {
    public static String FIO;

    public static void work(String fio) {

        ArrayList<String> Canceled = new ArrayList<String>();
        Scanner scan = new Scanner(System.in);

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

                Button yes = new Button("Approve");
                yes.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
                yes.setLayoutY(200);    // установка положения надписи по оси Y
                yes.setLayoutX(100);    // установка положения надписи по оси X


                Button no = new Button(" Reject ");
                no.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
                no.setLayoutY(200);    // установка положения надписи по оси Y
                no.setLayoutX(220);    // установка положения надписи по оси X

                Button GOT = new Button("Show request");
                GOT.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
                GOT.setLayoutY(160);
                GOT.setLayoutX(300);
                Group group1 = new Group(Od, req, lb, yes, no, GOT);
                Scene scene1 = new Scene(group1);
                stage2.setScene(scene1);

                stage2.show();

                GOT.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        try (Scanner TR = new Scanner(new File("application.txt"))) {

                            if (!TR.hasNextLine()) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "No more requests!", ButtonType.OK);
                                alert.showAndWait();
                            }
                            while (TR.hasNextLine()) {

                                yes.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        req.setText("Approve?");
                                        lb.setText(TR.nextLine());
                                        try (FileOutputStream writer = new FileOutputStream("Одобренные.txt", true)) {
                                            writer.write(lb.getText().getBytes());
                                            writer.write(" Статус:Одобрено".getBytes());
                                            writer.write("\n".getBytes());
                                        } catch (IOException ex) {
                                            Alert alert = new Alert(Alert.AlertType.NONE, ex.getMessage(), ButtonType.OK);
                                            alert.showAndWait();
                                        }
                                        lb.setText("");
                                    }
                                });
                                no.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        req.setText("Approve?");
                                        lb.setText(TR.nextLine());
                                        Canceled.add(lb.getText());
                                        lb.setText("");
                                    }
                                });
                            }
                        }
                        catch (FileNotFoundException e) {
                            Alert alert = new Alert(Alert.AlertType.NONE, "File [%s] is not found.", ButtonType.OK);
                            alert.showAndWait();
                        }
                try (FileOutputStream writer = new FileOutputStream("application.txt", false)) {
                    writer.write("".getBytes());
                } catch (IOException ex) {
                    Alert alert = new Alert(Alert.AlertType.NONE, ex.getMessage(), ButtonType.OK);
                    alert.showAndWait();
                }
                try (FileOutputStream writer = new FileOutputStream("application.txt", true)) {
                    for (String PL : Canceled) {
                        writer.write(PL.getBytes());
                        writer.write("\n".getBytes());
                    }
                } catch (IOException ex) {
                    Alert alert = new Alert(Alert.AlertType.NONE, ex.getMessage(), ButtonType.OK);
                    alert.showAndWait();
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

              /*  ArrayList<String> Canceled = new ArrayList<String>();
                Scanner scan = new Scanner(System.in);
                System.out.println("Выберите, какую пробирку отправить на анализ");
                try (Scanner TR = new Scanner(new File("Одобренные.txt"))) {
                    if (!TR.hasNext()) {
                        System.out.println("Файл пуст!");
                    }
                    while (TR.hasNextLine()) {
                        String text = TR.nextLine();
                        System.out.println(text);
                        System.out.println("Добавить на анализ?\n1.Да\n2.Нет");
                        int Act = scan.nextInt();

                        switch (Act) {
                            case 1:
                                try (FileOutputStream writer = new FileOutputStream("analysis.txt", true)) {
                                    writer.write(text.getBytes());
                                    writer.write("\n".getBytes());
                                } catch (IOException ex) {
                                    System.out.println(ex.getMessage());
                                }
                                break;
                            case 2:
                                Canceled.add(text);
                                break;
                        }
                    }
                }
                catch (FileNotFoundException e) {
                    Alert alert = new Alert(Alert.AlertType.NONE, "File [%s] is not found.", ButtonType.OK);
                    alert.showAndWait();
                }
                try (FileOutputStream writer = new FileOutputStream("Одобренные.txt", false)) {
                    writer.write("".getBytes());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                try (FileOutputStream writer = new FileOutputStream("Одобренные.txt", true)) {
                    for (String PL : Canceled) {
                        writer.write(PL.getBytes());
                        writer.write("\n".getBytes());
                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
*/
            }
        });

        Button sendRes = new Button("To send results to users");
        sendRes.setLayoutY(220);    // установка положения надписи по оси Y
        sendRes.setLayoutX(100);    // установка положения надписи по оси X
        sendRes.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        sendRes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

/*
                Scanner scan = new Scanner(System.in);
                try (Scanner TR = new Scanner(new File("analysis.txt"))) {
                    if (!TR.hasNext()) {
                        Alert alert = new Alert(Alert.AlertType.NONE, "File is empty.", ButtonType.OK);
                        alert.showAndWait();
                    }
                    while (TR.hasNextLine()) {
                        String text = TR.nextLine();
                        System.out.println(text);
                    }
                } catch (FileNotFoundException e) {
                    Alert alert = new Alert(Alert.AlertType.NONE, "File [%s] is not found.", ButtonType.OK);
                    alert.showAndWait();
                }
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
}
