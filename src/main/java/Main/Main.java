package Main;

import Connect.Connect;
import Data.CalcStatistic;
import Data.SerializableDeSerializable;
import Data.Statistic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main implements Serializable {

    private final List<Statistic> list = new ArrayList<>();
    private static Main m;

    public static void main(String[] args) throws IOException {

        SerializableDeSerializable serilizAndDesiriliz = new SerializableDeSerializable();
        checkObjMain(serilizAndDesiriliz);
        m = serilizAndDesiriliz.deSerializable();
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

    public static Main checkObjMain (SerializableDeSerializable ser) {
        if (m == null){
            m = new Main();
            ser.serializable(m);
        }
        return m;
    }
}
