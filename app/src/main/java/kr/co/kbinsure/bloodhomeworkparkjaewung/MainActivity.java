package kr.co.kbinsure.bloodhomeworkparkjaewung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import kr.co.kbinsure.bloodhomeworkparkjaewung.fragment.DetailRequestFragment;
import kr.co.kbinsure.bloodhomeworkparkjaewung.fragment.ListOfRequestFragment;
import kr.co.kbinsure.bloodhomeworkparkjaewung.fragment.CongestionOfBeachFragment;
import kr.co.kbinsure.bloodhomeworkparkjaewung.fragment.LocationOfMapFragment;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view_drawer);

        drawerLayout = findViewById(R.id.drawer_layout);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragmentContainer, DetailRequestFragment.newInstance());
            transaction.commit();
        }
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
                        ft3.replace(R.id.fragmentContainer, CongestionOfBeachFragment.newInstance());
                        ft3.commit();
                        break;
                    case R.id.callItem:
                        FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
                        ft4.replace(R.id.fragmentContainer, LocationOfMapFragment.newInstance());
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