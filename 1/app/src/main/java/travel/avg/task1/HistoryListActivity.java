package travel.avg.task1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import travel.avg.task1.Adapters.HistoryAdapter;
import travel.avg.task1.Adapters.WordAdapter;
import travel.avg.task1.DB.DBMethods;

public class HistoryListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    ArrayList<String> list1 = new ArrayList<>();
    ArrayList<String> list2_1 = new ArrayList<>();
    ArrayList<Integer> list2_2 = new ArrayList<>();
    ListView listView1, listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        listView1 = findViewById(R.id.listHistory1);
        listView2 = findViewById(R.id.listHistory2);
        list1.addAll(DBMethods.outputMap(this));
        DBMethods.WordCount(this);

        //region toolbar, drawer, navigationView
        Toolbar toolbar = findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout4);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view4);
        navigationView.setNavigationItemSelectedListener(this);
        //endregion

        //region Tab
        TabHost tabHost = findViewById(R.id.tabHost);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setContent(R.id.attempts);
        tabSpec.setIndicator("Попытки");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setContent(R.id.words);
        tabSpec.setIndicator("Слова");
        tabHost.addTab(tabSpec);

        tabHost.setCurrentTab(0);
        //endregion

        if(list1.size()!=0){
            HistoryAdapter adapter = new HistoryAdapter(this, list1);
            listView1.setAdapter(adapter);
        }

        Map<String, Integer> map = new LinkedHashMap<>();

        map.putAll(Sort(DBMethods.WordCount(this)));

        if(map.size()!=0){
            for (Object word : map.keySet()) {
                list2_1.add(word.toString());
                list2_2.add(map.get(word));
            }
            WordAdapter wordAdapter = new WordAdapter(this, list2_1, list2_2);
            listView2.setAdapter(wordAdapter);
        }
    }

    public Map<String, Integer> Sort(Map<String, Integer> map){
        Map<String, Integer> sortedmap = new LinkedHashMap<>();
        //convert map to a List
        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());

        //sorting the list with a comparator
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        //convert sortedMap back to Map

        for (Map.Entry<String, Integer> entry : list) {
            sortedmap.put(entry.getKey(), entry.getValue());
        }
        return sortedmap;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout4);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            return false;
        }
        else{
            return true;
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {

            Intent intent = new Intent(HistoryListActivity.this, MainActivity.class);
            startActivity(intent);
            // Handle the camera action
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout4);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
