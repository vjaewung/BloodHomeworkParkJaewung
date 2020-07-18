package kr.co.kbinsure.bloodhomeworkparkjaewung.androidMaterialWidget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import kr.co.kbinsure.bloodhomeworkparkjaewung.R;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;

public class TabLayoutBasicActivity extends AppCompatActivity {

    private BadgeDrawable badge;
    private int badgeCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_basic);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        badge = tabLayout.getTabAt(0).getOrCreateBadge();
        badge.setVisible(true);
        badge.setNumber(badgeCount);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String param = "";
                switch (tab.getPosition()) {
                    case 0:
                        param = "상품검색";
                        badge.setNumber(++badgeCount);
                        break;
                    case 1:
                        param = "카메라";
                        break;
                    case 2:
                        param = "이용안내";
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + tab.getPosition());
                }
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.tabContent, FragmentContent.newInstance(param));
                ft.commit();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    public static class FragmentContent extends Fragment {

        public static Fragment newInstance(String param) {
            Fragment fragment = new TabLayoutBasicActivity.FragmentContent();
            Bundle bundle = new Bundle();
            bundle.putString("title", param);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.bottom_fragment, container, false);
            TextView titleTV = root.findViewById(R.id.contentTitle);
            String paramValue = getArguments().getString("title");
            titleTV.setText(paramValue);
            return root;
        }
    }
}