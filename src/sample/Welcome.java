package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import weather.WeatherData;

import java.net.URL;
import java.util.ResourceBundle;

public class Welcome implements Initializable {

    public void start() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        Scene scene=new Scene(root,865, 555);
        Main.stage.setTitle("Welcome W");
        Main.stage.hide();
        Main.stage.setScene(scene);
        Main.stage.show();
    }
    public void Click(Event event) throws Exception {

        Welcome.setVisible(true);
        showName();
    }
    public void Weather(ActionEvent actionEvent) throws Exception {

        data.start();

    }
    public void showName() {

        Name.setText(Main.un);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        data=new WeatherData();
    }

    WeatherData data;
    public javafx.scene.control.Label Name;
    public javafx.scene.control.Label Welcome;
}
