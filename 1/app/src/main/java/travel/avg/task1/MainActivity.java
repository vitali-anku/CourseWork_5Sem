package travel.avg.task1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import travel.avg.task1.Adapters.Adapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private ArrayList<String> list = new ArrayList<>();
    ListView listView;
    Adapter adapter;
    public boolean opened = false;
    //static SharedPreferences pSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view1);
        navigationView.setNavigationItemSelectedListener(this);

        //pSharedPref = this.getSharedPreferences("MyCollection", Context.MODE_PRIVATE);

        //outputMap();

//        if(loadMap("Collection")!=null){
//            ArList.dateList.putAll(loadMap("Collection"));
//        }
//
        listView = findViewById(R.id.listView1);

        adapter = new Adapter(this, ArList.list);

        if (ArList.list.size() != 0) {
            listView.setAdapter(adapter);
        }

        if(savedInstanceState!=null){
            opened = savedInstanceState.getBoolean("opened");
            if(opened){
                Open();
            }
        }

        final LinearLayout btn1 = findViewById(R.id.view1);
        final LinearLayout btn2 = findViewById(R.id.view2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Open();
                opened = true;
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ArList.list.size()<3){
                    Toast.makeText(getApplicationContext(), "Нужно больше 3 элементов в списке!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(MainActivity.this, InterviewActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("opened", opened);
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

    public void Open(){
        LayoutInflater li = LayoutInflater.from(MainActivity.this);
        View promptsView = li.inflate(R.layout.prompt, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Введите слово!")
                .setView(promptsView);
        final EditText word = promptsView.findViewById(R.id.word);

        builder.setCancelable(false)
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String str = word.getText().toString();
                        if(!str.toString().equals("")) {
                            list.add(str);
                            ArList.list.add(str);
                            listView.setAdapter(adapter);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Неверный ввод", Toast.LENGTH_SHORT).show();
                        }
                        opened = false;
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        opened = false;
                    }
                });
        AlertDialog alert = builder.create();
        alert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        alert.show();
    }

//    private static Map<String, Map<String, Integer>> loadMap(String key){
//        Map<String, Map<String, Integer>> outputMap = new HashMap<>();
//        Map<String, Integer> value = new HashMap<>();
//        try{
//            if (pSharedPref != null){
//                String jsonString = pSharedPref.getString(key, (new JSONObject()).toString());
//                JSONObject jsonObject = new JSONObject(jsonString);
//                Iterator<String> keysItr = jsonObject.keys();
//                while(keysItr.hasNext()) {
//                    String k = keysItr.next();
//                    value.clear();
//                    JSONObject jsonObject2 = (JSONObject) jsonObject.get(k);
//                    Iterator<String> keystr2 = jsonObject2.keys();
//                    while (keystr2.hasNext()){
//                        String ke = keystr2.next();
//                        Integer val = (Integer) jsonObject2.get(ke);
//                        value.put(ke, val);
//                    }
//                    outputMap.put(k, value);
//                }
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return outputMap;
//    }

//    public void outputMap(){
//        DBHelper dbHelper = new DBHelper(this);
//        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
//
//        Map<String, Integer> listmap1= new HashMap<>();
//        //Map<String, Integer> listmap2= new HashMap<>();
//
//        try{
//            String query = "Select date, name, count from " + DBHelper.tableName1;
//            Cursor cursor = sqLiteDatabase.rawQuery(query,null);
//
//            String asd = "";
//            if(cursor.moveToFirst()){
//                int int1 = cursor.getColumnIndex("date");
//                int int2 = cursor.getColumnIndex("name");
//                int int3 = cursor.getColumnIndex("count");
//
//                do {
//                    String dat = cursor.getString(int1);
//                    String nam = cursor.getString(int2);
//                    Integer count = cursor.getInt(int3);
//                    if(asd.equals(dat) || asd.equals("")){
//                        listmap1.put(nam, count);
//                        asd = dat;
//                        ArList.dateList.put(dat, listmap1);
//                    }
//                    else {
//
//                        listmap1.remove(asd);
//                        listmap1.put(nam, count);
//                        asd = dat;
//                        ArList.dateList.put(dat, listmap1);
//                    }
//                }while (cursor.moveToNext());
//            }
//
//            cursor.close();
//        }
//        catch (Exception e){
//
//        }
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout1);
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
            // Handle the camera action
        } else if (id == R.id.nav_send) {

            Intent intent = new Intent(MainActivity.this, HistoryListActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout1);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //    public void OnClick(View view){
//        //adapter = new Adapter(MainActivity.this, ArList.list);
//        switch (view.getId()){
//            case R.id.btn1:
//                LayoutInflater li = LayoutInflater.from(MainActivity.this);
//                View promptsView = li.inflate(R.layout.prompt, null);
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("Введите слово!")
//                        .setView(promptsView);
//                final EditText word = promptsView.findViewById(R.id.word);
//                builder.setCancelable(false)
//                        .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                String str = word.getText().toString();
////                                list.add(str);
//                                ArList.list.add(str);
//                            }
//                        })
//                        .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.cancel();
//                            }
//                        });
//                AlertDialog alert = builder.create();
//                alert.show();
//                listView.setAdapter(adapter);
//
//                break;
//            case R.id.btn2:
//                if(ArList.list.size()<3||list.size()>25){
//                    Toast.makeText(getApplicationContext(), "Нужно от 3 до 25 элементов в списке!", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Intent intent = new Intent(MainActivity.this, InterviewActivity.class);
//                    startActivity(intent);
//                }
//                break;
//        }
//    }
}
