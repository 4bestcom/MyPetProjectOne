package Main;

import Connect.Connect;
import Data.CalcStatistic;
import Data.SerializableDeSerializable;
import Data.Statistic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main implements Serializable {

    private  List<Statistic> list = new ArrayList<>();


    public static void main(String[] args) throws IOException {

        SerializableDeSerializable serilizAndDesiriliz = new SerializableDeSerializable();
        Main m = serilizAndDesiriliz.deSerializable();
        Connect connect = new Connect();
        connect.connectToApi();
        m.getList().add(connect.parseJson(m));
        serilizAndDesiriliz.serializable(m);
        CalcStatistic calcStatistic = new CalcStatistic();
        calcStatistic.calculateStatistic(m);
    }



    public  List<Statistic> getList() {
        return list;
    }
}
