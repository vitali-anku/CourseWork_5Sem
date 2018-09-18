package travel.avg.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArList {
    public static ArrayList<String> list = new ArrayList<>();
    //public static ArrayList<String> nam = new ArrayList<>();
    public static ArrayList<Integer> count = new ArrayList<>();
    public static Map<String, Integer> l = new HashMap<>();
    public static List<Integer[][]> lst = new ArrayList<>();
    public static List<Integer> lda = new ArrayList<>();
    //public static ArrayList<Map<String, Integer>> count = new ArrayList<>();
    public static void Victory(){
        for (int i = 0; i < lda.size() - 1; i++) {
            for (int j = i + 1; j <= lda.size() - 1; j++) {

                Integer[][] asd = new Integer[1][2];
                asd[0][0] = i;
                asd[0][1] = j;

                lst.add(asd);
            }
        }
    }
    public static void NewMethod(){
        for (String asd : list) {
            lda.add(list.indexOf(asd));
        }
    }

    public static void Sort(){
        Collections.shuffle(lst);
    }
}
