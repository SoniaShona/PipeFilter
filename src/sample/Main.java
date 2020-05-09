package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.code.*;

public class Main extends Application {

    private  static Pipe p1 = new BlockingQueue();
    private  static Pipe p2 = new BlockingQueue();
    private  static Pipe p3 = new BlockingQueue();
    private  static Filter a1;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();
        GUI controller = fxmlLoader.getController();
        controller.setPipes(p3,p1);

        primaryStage.setTitle("Filtre Calculator");
        primaryStage.setScene(new Scene(root, 622, 426));
        primaryStage.show();

        a1=controller;
        initThreads();
    }
    public void initThreads(){
        Filter b1 = new Calcul(p1,p2);
        Filter c1 = new Trace(p2,p3);

        Thread th1 = new Thread( a1 );
        Thread th2 = new Thread( b1 );
        Thread th3 = new Thread( c1 );

        th1.start();
        th2.start();
        th3.start();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
