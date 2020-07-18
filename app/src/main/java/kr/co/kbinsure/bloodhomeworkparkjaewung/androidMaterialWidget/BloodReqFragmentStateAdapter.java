package kr.co.kbinsure.bloodhomeworkparkjaewung.androidMaterialWidget;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.ArrayList;

public class BloodReqFragmentStateAdapter extends FragmentStateAdapter {

    private ArrayList<Fragment> fragments = new ArrayList();

    public BloodReqFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    public void appendFragment(Fragment fragment){
        fragments.add(fragment);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }
    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
