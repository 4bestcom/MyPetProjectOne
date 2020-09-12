package Data;

import Main.Main;

import java.util.ArrayList;
import java.util.List;

public class CalcStatistic {

    private final List<Integer> list = new ArrayList<>();
    private int day = 1;


    public void calculateStatistic(Main main) {
        if (main.getList().size() >= 4) {
            for (int i = main.getList().size(); i > 0; i--) {
                list.add(main.getList().get(i - 1).getConfirmed());
            }
            if (!list.isEmpty()) {
                System.out.println("вчера кол-во зараженных " + list.get(1) + " человек");
                System.out.println("сегодня кол-во зараженных " + list.get(0) + " человек" + " прирост составил: " +
                        (list.get(0) - list.get(1)));
            }
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i - 1) < list.get(i)) {
                    day++;
                } else break;

            }
            if (day >= 2) {
                System.out.println("Кол-во зараженных снижается " + (day - 1) + " день подряд");
                for (int i = 1; i < day; i++) {
                    System.out.println(main.getList().get(main.getList().size() - i).getDate() + " заразилось на " +
                            String.format("%.2f ", ((1 - ((double) list.get(i - 1) / list.get(i))) * 100)) +
                            "% меньше предыдущего дня");
                }
            }
        }
    }
}
