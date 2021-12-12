package com.term.ckqltv.Vieww;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.term.ckqltv.R;
import com.term.ckqltv.frm.Homefrm;

public class Homes extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    MenuItem selectedFeature, selectedManager;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FragmentManager fragmentManager;
    TextView TXT_menu_tenkh;
    int maquyen = 0;
    SharedPreferences sharedPreferences;
    public String tendn;
    public int makh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homes);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.navigation_view_trangchu);
        toolbar=findViewById(R.id.toolbar);
        View view = navigationView.getHeaderView(0);
        TXT_menu_tenkh = (TextView) view.findViewById(R.id.txt_menu_tenkh);

      //  setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.appbar_scrolling_view_behavior,R.string.appbar_scrolling_view_behavior){
            @Override
            public void onDrawerOpened(View drawerView) {    super.onDrawerOpened(drawerView); }

            @Override
            public void onDrawerClosed(View drawerView) {    super.onDrawerClosed(drawerView); }
        };
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        makh=intent.getIntExtra("makh",0);

        tendn = intent.getStringExtra("tendn");

        TXT_menu_tenkh.setText( "Xin chào " + tendn +" !!");

        //lấy file share prefer
        sharedPreferences = getSharedPreferences("luuquyen", Context.MODE_PRIVATE);
        maquyen = sharedPreferences.getInt("maquyen",0);

      //  fragmentManager = getSupportFragmentManager();
//        FragmentTransaction tranDisplayHome = fragmentManager.beginTransaction();
//        Homefrm homefrm = new Homefrm();
//        tranDisplayHome.replace(R.id.contentView,homefrm);
//        tranDisplayHome.commit();
//        navigationView.setCheckedItem(R.id.nav_home);



    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return  true;
            case R.id.tcn:
                Intent intent=new Intent(Homes.this,TCN.class);

                intent.putExtra("makh",makh);

                startActivity(intent);


//                setResult(RESULT_OK,intent);
//                startActivity(intent);
//                finish();
//                return   true;
//            default:
           break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tcn, menu);
        return true;
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.nav_home:
                //hiển thị tương ứng trên navigation
                FragmentTransaction tranDisplayHome = fragmentManager.beginTransaction();
                Homefrm homefrm = new Homefrm();
                tranDisplayHome.replace(R.id.contentView,homefrm);
                tranDisplayHome.commit();
                navigationView.setCheckedItem(item.getItemId());
                drawerLayout.closeDrawers();
                break;

            case R.id.nav_statistic:
                //hiển thị tương ứng trên navigation
                FragmentTransaction tranDisplayStatistic = fragmentManager.beginTransaction();
                Homefrm homeloai = new Homefrm();
                //   tranDisplayStatistic.replace(R.id.contentView,displayStatisticFragment);
                tranDisplayStatistic.commit();
                navigationView.setCheckedItem(item.getItemId());
                drawerLayout.closeDrawers();
                break;


            case R.id.nav_category:
                //hiển thị tương ứng trên navigation
                FragmentTransaction tranDisplayMenu = fragmentManager.beginTransaction();
                //  DisplayCategoryFragment displayCategoryFragment = new DisplayCategoryFragment();
                //   tranDisplayMenu.replace(R.id.contentView, displayCategoryFragment);
                tranDisplayMenu.commit();
                navigationView.setCheckedItem(item.getItemId());
                drawerLayout.closeDrawers();

                break;

            case R.id.nav_staff:
                if(maquyen == 1){
                    //hiển thị tương ứng trên navigation
                    FragmentTransaction tranDisplayStaff = fragmentManager.beginTransaction();
                    //      DisplayStaffFragment displayStaffFragment = new DisplayStaffFragment();
                    //     tranDisplayStaff.replace(R.id.contentView,displayStaffFragment);
                    tranDisplayStaff.commit();
                    navigationView.setCheckedItem(item.getItemId());
                    drawerLayout.closeDrawers();
                }else {
                    Toast.makeText(getApplicationContext(),"Bạn không có quyền truy cập",Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.nav_logout:
                //gọi activity ra trang welcome
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                break;
        }

        return false;
    }
}