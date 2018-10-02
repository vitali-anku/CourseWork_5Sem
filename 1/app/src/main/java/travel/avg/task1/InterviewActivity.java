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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InterviewActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    int n = ArList.list.size();

    TextView show;

    Button btn1, btn2;
    int current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState!=null){
            current=savedInstanceState.getInt("current");
        }else {
            current=0;
            ArList.listVictory.clear();
            ArList.listIndex.clear();
            ArList.listIndexOf();
            ArList.Victory();
            ArList.Sort();
        }

        show = findViewById(R.id.number);
        btn1 = findViewById(R.id.test1);
        btn2 = findViewById(R.id.test2);

        final int count = Count();

        for(Integer[]anArr : ArList.listVictory.get(current)){
            SetButton(ArList.list.get(anArr[0]), ArList.list.get(anArr[1]), count);
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = btn1.getText().toString();
                String str2 = btn2.getText().toString();
                Next(str1, str2, count);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = btn1.getText().toString();
                String str2 = btn2.getText().toString();
                Next(str2, str1, count);
            }
        });
    }

    private void Next(String str1, String str2, final int nn){
        if(ArList.interviewList.size() != 0){
            if(!ArList.interviewList.containsKey(str1)){
                ArList.interviewList.put(str1, 1);
            }
            else {
                int value = ArList.interviewList.get(str1);
                value++;
                ArList.interviewList.put(str1, value);
            }
            if(!ArList.interviewList.containsKey(str2)){
                ArList.interviewList.put(str2, 0);
            }
        }
        else {
            ArList.interviewList.put(str1, 1);
            ArList.interviewList.put(str2, 0);
        }
        if(current<nn-1){
            current++;
            for(Integer[]anArr : ArList.listVictory.get(current)){
                SetButton(ArList.list.get(anArr[0]), ArList.list.get(anArr[1]), nn);
            }
        }
        else if(current==nn-1){
            Intent intent1 = new Intent(InterviewActivity.this, StatisticActivity.class);
            startActivity(intent1);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("current", current);
    }

    public void SetButton(String str1, String str2, int count){
        btn1.setText(str1);
        btn2.setText(str2);
        show.setText("Вопрос " + (current+1) + " из " + count);
    }

    public int Count(){

        int count = 0;
        int das = ArList.list.size();

        for(int i=0; i<das-1; i++){
            for (int j=i+1; j<=das-1; j++){
                count++;
            }
        }

        return count;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout2);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.home:
                ArList.interviewList.clear();
                ArList.count.clear();
                ArList.listVictory.clear();
                ArList.listIndex.clear();

                Intent intent = new Intent(InterviewActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_send:
                Intent intent1 = new Intent(this, HistoryListActivity.class);
                startActivity(intent1);
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
}
