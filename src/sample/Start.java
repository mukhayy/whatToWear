package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import java.net.URL;
import java.util.ResourceBundle;

public class Start implements Initializable{


    public void start() throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        Scene scene=new Scene(root,798.0,536);
        Main.stage.setTitle("Weather W");
        Main.stage.setScene(scene);
        Main.stage.setResizable(false);
        Main.stage.show();
    }

    public void Enter1(ActionEvent actionEvent) throws Exception {
        n=UName.getText();
        p=Pass.getText();
        Check(n,p);
    }

    public void Register(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene scene=new Scene(root,798.0,536);
        Main.stage.setTitle("Weather W");
        Main.stage.hide();
        Main.stage.setScene(scene);
        Main.stage.show();
    }

    String query;
    String dbName, dbPassword;

    public void Check(String uname, String password ){

        n=UName.getText();
        p=Pass.getText();
        DatabaseConnection db=new DatabaseConnection();

        try {
            query = "SELECT UserName, UserPassword FROM AppUser;";
            db.getData(query);

            while(db.resultSet.next()){

                dbName = db.resultSet.getString("UserName");
                dbPassword = db.resultSet.getString("UserPassword");

                if( dbName.equals(uname)  && dbPassword.equals(password)){

                    ObjectOfWelcome=new Welcome();
                    Main.un=dbName;
                    Main.up=dbPassword;
                    ObjectOfWelcome.start();

                }else if(n.isEmpty() || p.isEmpty()) {

                    empty.setVisible(true);

                }else{

                    invalid.setVisible(true);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
     Welcome ObjectOfWelcome;
     public String n, p;
     public javafx.scene.control.TextField UName;
     public PasswordField Pass;
     public Label invalid;
     public Label empty;
}
