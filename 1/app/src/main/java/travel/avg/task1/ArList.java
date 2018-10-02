package travel.avg.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArList {
    public static ArrayList<String> list = new ArrayList<>();
    public static ArrayList<Integer> count = new ArrayList<>();
    public static Map<String, Integer> interviewList = new HashMap<>();
    public static List<Integer[][]> listVictory = new ArrayList<>();
    public static List<Integer> listIndex = new ArrayList<>();
    public static Map<String, Map<String, Integer>>  dateList = new HashMap<>();

    public static void Victory(){
        for (int i = 0; i < listIndex.size() - 1; i++) {
            for (int j = i + 1; j <= listIndex.size() - 1; j++) {

                Integer[][] asd = new Integer[1][2];
                asd[0][0] = i;
                asd[0][1] = j;

                listVictory.add(asd);
            }
        }
    }

    public static void listIndexOf(){
        for (String asd : list) {
            listIndex.add(list.indexOf(asd));
        }
    }

    public static void Sort(){
        Collections.shuffle(listVictory);
    }
}
