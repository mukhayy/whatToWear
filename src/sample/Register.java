package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class Register implements Initializable{

    public void Register1(ActionEvent actionEvent) throws Exception {

        String name, password, city,country, query;
        name=Name.getText();
        password=Pass.getText();
        city=City.getText();
        country=Country.getText();
        try{
        query="INSERT INTO AppUser (UserName, UserPassword, UserCountry, UserCity) Values('"+ name + "','" + password + "','" + country + "','" + city + "');";
        dbc.storeData(query);
            Main.uCountry=country;
            Main.UCity=city;
        Start s= new Start();
        s.start();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbc = new DatabaseConnection();
    }

      public javafx.scene.control.TextField Name;
      public javafx.scene.control.TextField City;
      public javafx.scene.control.TextField Country;
      public javafx.scene.control.PasswordField Pass;
      DatabaseConnection dbc;
}
