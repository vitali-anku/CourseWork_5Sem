package travel.avg.task1;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HistoryListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    ArrayList<String> list = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        listView = findViewById(R.id.listHistory);

        Toolbar toolbar = findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout4);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view4);
        navigationView.setNavigationItemSelectedListener(this);

        for (Object key: ArList.dateList.keySet()) {
            list.add(key.toString());
        }

        if(ArList.dateList.size()!=0){
            HistoryAdapter adapter = new HistoryAdapter(this, list);
            listView.setAdapter(adapter);

//            listView.setOnClickListener(new ListView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Intent intent = new Intent(view.getContext(), DateTimeListActivity.class);
//                    intent.putExtra("key", list);
//                }
//            });
        }
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
