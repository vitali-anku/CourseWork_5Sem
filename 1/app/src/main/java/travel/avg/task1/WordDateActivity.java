package travel.avg.task1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import travel.avg.task1.Adapters.TwoAdapter;
import travel.avg.task1.DB.DBMethods;

public class WordDateActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> list1 = new ArrayList<>();
    ArrayList<Integer> list2 = new ArrayList<>();
    Map<String, Integer> sortedMap = new LinkedHashMap<>();
    Map<String, Integer> firstMap = new LinkedHashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_date);

        Bundle bundle = getIntent().getExtras();
        String key = bundle.getString("key");

        firstMap.putAll(DBMethods.outputMap1(this, key));
        Sort();

        Toolbar tolbar = findViewById(R.id.toolbar_back);
        setSupportActionBar(tolbar);
        //str.substring(0,str.length()-3)
        getSupportActionBar().setTitle(key);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listView = findViewById(R.id.dateWord);

        for (Object name : sortedMap.keySet()) {
            list1.add(name.toString());
            list2.add(sortedMap.get(name));
        }


        TwoAdapter adapter = new TwoAdapter(this, list2, list1);
        listView.setAdapter(adapter);

        //map.putAll(DBMethods.ouptupWordCountMax(this, key));

    }

    public void Sort(){
        //convert map to a List
        List<Map.Entry<String, Integer>> list = new LinkedList<>(firstMap.entrySet());

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            finish();
//            Intent intent = new Intent(this, HistoryListActivity.class);
//            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
