package weather;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sample.DatabaseConnection;
import sample.Main;
import java.sql.SQLException;
import java.lang.String;


public class WeatherData{

    public void start() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Wear.fxml"));
        Scene scene = new Scene(root, 865, 555);
        Main.stage.setTitle("Welcome W");
        Main.stage.hide();
        Main.stage.setScene(scene);
        Main.stage.show();
    }

    private City city;

    private Temperature temperature;
    public Weather weather;

    public City getCity() {
        return city;
    }


    public Temperature getTemperature() {
        return temperature;
    }

    public Weather getWeather() {
        return weather;
    }

    private void setAttribute(String currentTagName, Element rootElement) {

        NodeList currentList = rootElement.getElementsByTagName(currentTagName);
        Node currentNode = currentList.item(0);
        Element currentElement = (Element) currentNode;

        switch (currentTagName) {
            case "city":
                city = new City(currentElement.getAttribute("id"), currentElement.getAttribute("name"));
                break;
            case "country":
                city.setCountry(currentElement.getTextContent());
                break;
            case "temperature":
                temperature = new Temperature(currentElement.getAttribute("value"), currentElement.getAttribute("min"),
                        currentElement.getAttribute("max"), currentElement.getAttribute("unit"));
                break;

            case "weather":
                weather = new Weather(currentElement.getAttribute("value"));
                break;

        }
    }

    public void setAllWeatherDataByTagNames(Document doc) {
        NodeList list = doc.getElementsByTagName("current");
        Node rootNode = list.item(0);
        Element rootElement = (Element) rootNode;
        setAttribute("city", rootElement);
        setAttribute("country", rootElement);
        setAttribute("sun", rootElement);
        setAttribute("temperature", rootElement);
        setAttribute("humidity", rootElement);
        setAttribute("weather", rootElement);

    }

    @Override
    public String toString() {

        return String.valueOf(getWeather());

    }
        Image image;
        Temperature t = new Temperature();

        public javafx.scene.control.Label WICON;
        public javafx.scene.control.Label WCOND;
        public ImageView gif;
        public javafx.scene.control.Label Temperature;
        public javafx.scene.control.Label Advice;
        public javafx.scene.control.Label Umbrella;

        WeatherData weatherData;
        public void Display() throws SQLException {

            DatabaseConnection db = new DatabaseConnection();
            IWeatherDataService dataService = WeatherDataServiceFactory.getWeatherDataService(WeatherDataServiceFactory.service.OPEN_WEATHER_MAP);

            try {

                String query;
                String dbCountry, dbCity;
                query = "SELECT UserCountry, UserCity FROM AppUser;";
                db.getData(query);

                while (db.resultSet.next()) {

                    dbCountry = db.resultSet.getString("UserCountry");
                    dbCity = db.resultSet.getString("UserCity");

                    Main.UCity = dbCity;
                    Main.uCountry = dbCountry;

                    weatherData=dataService.getWeatherData(new Location(Main.UCity, Main.uCountry));

                   // System.out.println(toString());

                    WCOND.setText(weatherData.toString());
                    Temperature.setText(t.temprature());
                }
            } catch (WeatherDataServiceException e) {
                e.printStackTrace();
            }


            int temp = Integer.parseInt(t.getValue());
            String cond=weatherData.toString();
            //System.out.println(cond);

            if (temp > 25 && cond.equals("clear sky")) {
                image = new Image(getClass().getResourceAsStream("/pictures/weathericon/Sunny.png"));
                WICON.setGraphic(new ImageView(image));
                Advice.setText(" Today's weather pretty good,\nWear jeans and cushy top \n Be free on your clothes today \n Enjoy it ");
            }

            if (temp <= 15 && cond.equals("rain") && temp>=10) {
                image = new Image(getClass().getResourceAsStream("/pictures/weathericon/heavy_rain.png"));
                WICON.setGraphic(new ImageView(image));
                Advice.setText(" Today's weather rainy but warm \n Take your umbrella \n  Choose for your top jeans \n   Or you can wear windcheater");
                image=new Image(getClass().getResourceAsStream("/pictures/weathericon/Umbrella.png"));
                Umbrella.setGraphic(new ImageView(image));
            }

            if (temp >= 10 && temp <= 15 && cond.equals("few clouds") ) {
                image = new Image(getClass().getResourceAsStream("/pictures/weathericon/PartlyCloud.png"));
                WICON.setGraphic(new ImageView(image));
                Advice.setText(" Today's weather is few cloudy \n  Not so cold, Take your jacket \n   ");
            }

            if (temp >= 5 && temp <= 10 && cond.equals("clouds")) {

                image = new Image(getClass().getResourceAsStream("/pictures/weathericon/Cumulonimbus_Cloud_PNG_Clipart-871.png"));
                WICON.setGraphic(new ImageView(image));
                Advice.setText(" Dress regularly as today will be moderate\n You should probably wear jacket\n or hoodie, normal top\n and shorts. It most likely won't rain\n today so no need for an \numbrella   ");
            }

            if (temp >= 0 && temp <= 5 && cond.equals("clouds")) {
                image = new Image(getClass().getResourceAsStream("/pictures/weathericon/Cumulonimbus_Cloud_PNG_Clipart-871.png"));
                WICON.setGraphic(new ImageView(image));
                Advice.setText("      ");
            }

            if (temp >=0 && temp <= 5 && cond.equals("rain and snow")) {
                image = new Image(getClass().getResourceAsStream("/pictures/weathericon/snowwithrain.png"));
                WICON.setGraphic(new ImageView(image));
                Advice.setText("      ");
                image=new Image(getClass().getResourceAsStream("/pictures/weathericon/Umbrella.png"));
                Umbrella.setGraphic(new ImageView(image));
            }

            if (temp <= 1 && temp > -5  && cond.equals("snow")) {
                image = new Image(getClass().getResourceAsStream("/pictures/weathericon/Status-weather-snow-icon.png"));
                WICON.setGraphic(new ImageView(image));
                Advice.setText("  Dress warmly as today will be \nvery cold, You should probably wear\n snowcoat, warm layered tops.    ");
            }

            if (temp <= 5 && temp >= -5  && cond.equals("mist")) {
                image = new Image(getClass().getResourceAsStream("/pictures/weathericon/mist1.png"));
                WICON.setGraphic(new ImageView(image));
                Advice.setText("    Dress warmly as today will be \n" +
                        "very cold, You should probably wear\n" +
                        " snowcoat, warm layered tops.   ");
            }

            if (temp <= 7 && temp >= -1  && cond.equals("light rain")) {
                image = new Image(getClass().getResourceAsStream("/pictures/weathericon/sunrain.png"));
                WICON.setGraphic(new ImageView(image));
                Advice.setText("   Dress warmly as today will be cold\n. You should probably wear warm raincoat\n warm top, and jeans.\n It will rain today so bring an umbrella");
                image=new Image(getClass().getResourceAsStream("/pictures/weathericon/Umbrella.png"));
                Umbrella.setGraphic(new ImageView(image));
            }
            if (temp <= 17 && temp >= 10  && cond.equals("haze")) {
                image = new Image(getClass().getResourceAsStream("/pictures/weathericon/mist1.png"));
                WICON.setGraphic(new ImageView(image));
                Advice.setText("      ");
            }
            if (temp <= 10 && temp >= 3  && cond.equals("")) {
                image = new Image(getClass().getResourceAsStream("/pictures/weathericon/mist1.png"));
                WICON.setGraphic(new ImageView(image));
                Advice.setText("   Dress warmly as today will be cold\n" +
                        ". You should probably wear warm raincoat\n" +
                        " warm top, and jeans.\n" +
                        " It will rain today  so bring an umbrella    ");
            }
            if (temp <= 10 && temp >= 5  && cond.equals("heavy intensity rain")) {
                image = new Image(getClass().getResourceAsStream("/pictures/heavy_rain.png"));
                WICON.setGraphic(new ImageView(image));
                image=new Image(getClass().getResourceAsStream("/pictures/weathericon/Umbrella.png"));
                Umbrella.setGraphic(new ImageView(image));
                Advice.setText("      ");
            }
            if (temp <= 10 && temp > 0  && cond.equals("heavy rain")) {
                image = new Image(getClass().getResourceAsStream("/pictures/heavy_rain.png"));
                WICON.setGraphic(new ImageView(image));
                Advice.setText(" Dress slightly warm as today\n will be cold. You should probably wear \n warm coat or jacket, warm top\n It will rain today take your barin with you  ");
                image=new Image(getClass().getResourceAsStream("/pictures/weathericon/Umbrella.png"));
                Umbrella.setGraphic(new ImageView(image));
            }
            if (temp <= 8 && temp >= 1  && cond.equals("freezing rain")) {
                image = new Image(getClass().getResourceAsStream("/pictures/weathericon/heavy_rain.png"));
                WICON.setGraphic(new ImageView(image));
                Advice.setText(" Do not forget your umbrella \n    Wear warm sweater for your top \nas today will be"+
                        "freezing cold, You should probably wear\n" +
                                " snowcoat, warm layered tops.");
                image=new Image(getClass().getResourceAsStream("/pictures/weathericon/Umbrella.png"));
                Umbrella.setGraphic(new ImageView(image));
            }

            if (temp <= 12 && temp >= 5  && cond.equals("light intensity shower rain")) {
                image = new Image(getClass().getResourceAsStream("/pictures/weathericon/sunrain.png"));
                WICON.setGraphic(new ImageView(image));
                Advice.setText("  Dress warmly as today\n will be be cold, You\n should probably wear warm raincoat\n warm top. It will rain today \n so take an umbrella");
                image=new Image(getClass().getResourceAsStream("/pictures/weathericon/Umbrella.png"));
                Umbrella.setGraphic(new ImageView(image));
            }

            if (temp >= 0 && temp <= 5 && cond.equals("clear sky") ) {
                image = new Image(getClass().getResourceAsStream("/pictures/weathericon/PartlyCloud.png"));
                WICON.setGraphic(new ImageView(image));
                Advice.setText(" Today's weather drily, but cold \n   Wear warm sweater, coat\n or warm layered tops   ");
            }

            if (temp >= 0 && temp <= 5 && cond.equals("scattered clouds") ) {
                image = new Image(getClass().getResourceAsStream("/pictures/weathericon/PartlyCloud.png"));
                WICON.setGraphic(new ImageView(image));
                Advice.setText(" Today's weather cloudy and cold\n Wear warm sweater\n   Take on your top coat or warm jacket   ");
            }

            if (temp >= -4 && temp <= 8  ) {
                image = new Image(getClass().getResourceAsStream("/pictures/weathericon/PartlyCloud.png"));
                WICON.setGraphic(new ImageView(image));
                Advice.setText(" Today's weather is cold Dress warmly as today will be \n" + "very cold, You should probably wear" +"\n snowcoat, warm layered tops.    ");
            }
            if (temp >= 0 && temp <= 8 && cond.equals("smoke") ) {
                image = new Image(getClass().getResourceAsStream("/pictures/weathericon/PartlyCloud.png"));
                WICON.setGraphic(new ImageView(image));
                Advice.setText(" Today's weather is cold  Dress warmly as today will be \n"
                        + "very cold, You should probably wear\n" +
                        " snowcoat, warm layered tops.");
            }
        }

        public void OnClick(Event event) throws SQLException {

            Display();
            gif.setVisible(false);

        }

    }





