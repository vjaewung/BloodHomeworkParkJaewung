package kr.co.kbinsure.bloodhomeworkparkjaewung.material;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import kr.co.kbinsure.bloodhomeworkparkjaewung.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class BottomNavigationViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_view);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragmentContainer, BottomFragment.newInstance("상품검색"));
            transaction.commit();
        }
        BottomNavigationView navigationView = findViewById(R.id.bottomNavigation);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                String param = "";
                switch (item.getItemId()) {
                    case R.id.searchItem:
                        break;
                    case R.id.cameraItem:
                        break;
                    case R.id.cameraItem1:
                        break;
                    case R.id.callItem:
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + item.getItemId());
                }
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContainer, BottomFragment.newInstance(param));
                ft.commit();
                return true;
            }
        });
    }
    public static class BottomFragment extends Fragment {

        public static Fragment newInstance(String param) {
            Fragment fragment = new BottomFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", param);
            fragment.setArguments(bundle);
            return fragment;
        }
    }
}