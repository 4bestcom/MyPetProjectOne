package Connect;

import Data.Statistic;
import Main.Main;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Connect implements IConnect, IParseJson{

    private int index = 1;
    private String jsonString;


    public void connectToApi() {
        String url = "https://coronavirus-tracker-api.herokuapp.com/v2/locations?sourse=jhu&country_code=RU";
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.connect();

            if (HttpURLConnection.HTTP_OK == httpURLConnection.getResponseCode()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                jsonString = reader.readLine();
                reader.close();
            }
            httpURLConnection.disconnect();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Statistic parseJson(Main obj) {
        JsonElement jsonElement = JsonParser.parseString(jsonString);
        JsonObject details = jsonElement.getAsJsonObject();
        JsonElement confJs = details.get("latest").getAsJsonObject().get("confirmed");
        int confirmed = confJs.getAsInt();
        JsonElement confJs1 = details.get("latest").getAsJsonObject().get("deaths");
        int deaths = confJs1.getAsInt();
        for (Statistic s : obj.getList()) {
            if (s.getDate().equals(new SimpleDateFormat("dd.MM.yyyy").format(new Date())))
                System.exit(0);
        }
        return new Statistic(confirmed, deaths);
    }

    public void connectionSql(Main main) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/firstdata?useSSL=false", "root", "admin");
            PreparedStatement ps = connection.prepareStatement("INSERT INTO countpeople (id, count, data, region_id) VALUES (?,?,?,?)");
            for (Statistic statistic : main.getList()) {

                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                String dateInString = statistic.getDate();
                Date date = formatter.parse(dateInString);

                ps.setInt(1, index);
                ps.setInt(2, statistic.getConfirmed());
                ps.setDate(3, new java.sql.Date(date.getTime()));
                ps.setInt(4, 1);
                ps.executeUpdate();
                index++;
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

    }
}
