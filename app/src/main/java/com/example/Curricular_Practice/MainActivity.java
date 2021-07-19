package com.example.Curricular_Practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FragmentTransaction ft;
    private FragmentManager fm;
    private Fragment_Home fragment_home;
    private Fragment_Curricular fragment_curricular;
    private Fragment_Recommend fragment_recommend;
    private Fragment_List fragment_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.e("main", "터치감지");
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Log.e("main", "1번");
                        setFrag(0);
                        break;
                    case R.id.action_curricular:
                        Log.e("main", "2번");
                        setFrag(1);
                        break;
                    case R.id.action_recommend:
                        Log.e("main", "3");
                        setFrag(2);
                        break;
                    case R.id.action_list:
                        Log.e("main", "4");
                        setFrag(3);
                        break;

                }
                return true;
            }
        });
        fragment_home = new Fragment_Home();
        fragment_curricular = new Fragment_Curricular();
        fragment_recommend = new Fragment_Recommend();
        fragment_list = new Fragment_List();
        setFrag(0);//첫화면을 지정하면된다.
    }
    //프래그먼트 교체가 일어날때
    public void setFrag(int n){
        Log.e("main","교체작업");
        fm = getSupportFragmentManager();
        ft= fm.beginTransaction();//실제 화면전환떄 ft를 사용함
        switch (n){
            case 0:
                Log.e("main","1");
                ft.replace(R.id.main_frame, fragment_home,"home");
                ft.commit();//저장을 의미
                bottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                break;
            case 1:
                Log.e("main","1");
                ft.replace(R.id.main_frame,fragment_curricular,"curricular");
                ft.commit();
                bottomNavigationView.getMenu().findItem(R.id.action_curricular).setChecked(true);
                break;
            case 2:
                Log.e("main","1");
                ft.replace(R.id.main_frame,fragment_recommend,"reccomend");
                ft.commit();
                bottomNavigationView.getMenu().findItem(R.id.action_recommend).setChecked(true);

                break;
            case 3:
                Log.e("main","1");
                ft.replace(R.id.main_frame,fragment_list,"list");
                ft.commit();
                bottomNavigationView.getMenu().findItem(R.id.action_list).setChecked(true);

                break;

        }
    }
}