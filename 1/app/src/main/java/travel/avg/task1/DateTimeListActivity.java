package travel.avg.task1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DateTimeListActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> list1 = new ArrayList<>();
    ArrayList<Integer> list2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time_list);

        listView = findViewById(R.id.timelist);

        Bundle bundle = getIntent().getExtras();
        String str = bundle.getString("key");

        for (Object name : ArList.dateList.get(str).keySet()) {
            list1.add(name.toString());
            list2.add(ArList.dateList.get(str).get(name));
        }

        TwoAdapter adapter = new TwoAdapter(this, list2, list1);
        listView.setAdapter(adapter);

    }
}
