package travel.avg.task1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import travel.avg.task1.Adapters.WordAdapter;
import travel.avg.task1.DB.DBMethods;

public class WordActivity extends AppCompatActivity {

    Map<String, String> map = new HashMap<>();
    ArrayList<String> calendar = new ArrayList<>();
    ArrayList<String> statistic = new ArrayList<>();
    ListView dateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        Bundle bundle = getIntent().getExtras();
        String key = bundle.getString("key");
        dateList = findViewById(R.id.dateList);

        map.putAll(DBMethods.ouptupWordCountMax(this, key));

        Toolbar tolbar = findViewById(R.id.toolbar_back);
        setSupportActionBar(tolbar);

        getSupportActionBar().setTitle(key);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        for (Object name : map.keySet()) {
            calendar.add(name.toString());
            statistic.add(map.get(name));
        }

        WordAdapter wordAdapter = new WordAdapter(this, calendar, statistic, WordDateActivity.class);
        dateList.setAdapter(wordAdapter);
//        dateList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), "Hi!!!", Toast.LENGTH_SHORT).show();
//                view.findViewById(R.id.qwerty).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        Intent intent = new Intent(this, WordActivity.class);
////                        startActivity(intent);
//                    }
//                });
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
