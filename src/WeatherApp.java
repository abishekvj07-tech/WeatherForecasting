import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;

public class WeatherApp {

    // Replace with your actual API key
    private static final String API_KEY = "718202a1eff4851d8dbb07154d86bef2".trim();

    public static void main(String[] args) {
        Scanner obj=new Scanner(System.in);
        System.out.print("Please enter your City: ");
        String city= obj.next();

        getWeather(city);
    }
    public static void getWeather(String city) {
        try {

            String urlString = "https://api.openweathermap.org/data/2.5/weather?q="
                    + city + "&appid=" + API_KEY + "&units=metric";


            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");


            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();


            JSONObject obj = new JSONObject(response.toString());
            JSONObject main = obj.getJSONObject("main");
            JSONObject weather = obj.getJSONArray("weather").getJSONObject(0);

            double temperature = main.getDouble("temp");
            int humidity = main.getInt("humidity");
            String description = weather.getString("description");


            System.out.println("📍 City: " + city);
            System.out.println("🌡️ Temperature: " + temperature + " °C");
            System.out.println("💧 Humidity: " + humidity + "%");
            System.out.println("🌤️ Description: " + description);

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
