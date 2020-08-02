package sample;

import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


        public static String un, up, uCountry, UCity;
        public static Stage stage;
    @Override
        public void start(Stage primaryStage) throws Exception
    {
        stage=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        primaryStage.setScene(new Scene(root, 798.0, 536.0));
        primaryStage.getIcons().add(new Image("/pictures/weatherIcon/weather-2-icon.png"));
        primaryStage.setTitle("Weather W");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

        public static void main(String[] args)
       {
           launch(args);
       }
}
