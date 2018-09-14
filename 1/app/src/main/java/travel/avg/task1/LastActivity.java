package travel.avg.task1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class LastActivity extends AppCompatActivity {

    Button btn;
    ListView listView;
    ArrayList<String> list1 = new ArrayList<>();
    ArrayList<Integer> list2 = new ArrayList<>();
    Map<String, Integer> sortedMap = new LinkedHashMap<>();
//    private Map<String, Integer> myymap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);

        listView = findViewById(R.id.listStatistic);
        btn = findViewById(R.id.again);

        Sort();
        for (Object name : sortedMap.keySet()) {
            list1.add(name.toString());
            list2.add(ArList.l.get(name));
        }

        TwoAdapter adapter = new TwoAdapter(this, list2, list1);
        listView.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArList.list.clear();
                ArList.l.clear();
                ArList.count.clear();

                Intent intent = new Intent(LastActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void Sort(){
        //convert map to a List
        List<Map.Entry<String, Integer>> list = new LinkedList<>(ArList.l.entrySet());

        //sorting the list with a comparator
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        //convert sortedMap back to Map

        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
    }

}
