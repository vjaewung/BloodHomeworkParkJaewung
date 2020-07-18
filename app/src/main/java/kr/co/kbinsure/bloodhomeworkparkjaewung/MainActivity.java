package kr.co.kbinsure.bloodhomeworkparkjaewung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import kr.co.kbinsure.bloodhomeworkparkjaewung.fragment.DetailRequestFragment;
import kr.co.kbinsure.bloodhomeworkparkjaewung.fragment.ListOfRequestFragment;
import kr.co.kbinsure.bloodhomeworkparkjaewung.fragment.RequestInfoFragment;
import kr.co.kbinsure.bloodhomeworkparkjaewung.fragment.SettingRequestFragment;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search_for_information_restricted_traffic);
        setContentView(R.layout.activity_navigation_view_drawer);

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowTitleEnabled(true);
        //getSupportActionBar().setTitle("Blood Mngt");

        drawerLayout = findViewById(R.id.drawer_layout);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragmentContainer, DetailRequestFragment.newInstance());
            transaction.commit();
        }

//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer);
//        toggle.syncState();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.searchItem:
                        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                        ft1.replace(R.id.fragmentContainer, DetailRequestFragment.newInstance());
                        ft1.commit();
                        break;
                    case R.id.cameraItem:
                        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                        ft2.replace(R.id.fragmentContainer, ListOfRequestFragment.newInstance());
                        ft2.commit();
                        break;
                    case R.id.cameraItem1:
                        FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                        ft3.replace(R.id.fragmentContainer, RequestInfoFragment.newInstance());
                        ft3.commit();
                        break;
                    case R.id.callItem:
                        FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
                        ft4.replace(R.id.fragmentContainer, SettingRequestFragment.newInstance());
                        ft4.commit();
                        break;
                    default:
                        throw new IllegalStateException("Execption :" + item.getItemId());
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}