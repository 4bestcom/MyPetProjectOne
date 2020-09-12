package Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Statistic implements Serializable {

    private int confirmed;
    private int deaths;
    private String date;

    public Statistic(int confirmed, int deaths) {
        this.confirmed = confirmed;
        this.deaths = deaths;
        date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
    }

    public String getDate() {
        return date;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    @Override
    public String toString() {
        return "Статистика за: " + date + "\r\n"+
                "Заболевших: " + confirmed +"\r\n"+
                "Умерших: " + deaths;
    }
}
