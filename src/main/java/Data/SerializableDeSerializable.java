package Data;

import Main.Main;
import java.io.*;
import java.util.List;

public class SerializableDeSerializable {

    private final String fileName = "d:coronaListStatistic.data";
    private Main m;

    public void serializable(Object obj) {
        try
                (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Main deSerializable() {
        try
                (FileInputStream fileInputStream = new FileInputStream(fileName);
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
           m = (Main) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Файл не найден");
        }return m;
    }

}
