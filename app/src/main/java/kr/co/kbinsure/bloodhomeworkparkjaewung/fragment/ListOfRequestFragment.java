package kr.co.kbinsure.bloodhomeworkparkjaewung.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import kr.co.kbinsure.bloodhomeworkparkjaewung.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListOfRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListOfRequestFragment extends Fragment {

    public static ListOfRequestFragment newInstance() {
        ListOfRequestFragment fragment = new ListOfRequestFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e("activity_fragment2", "--------------------------------");
        return inflater.inflate(R.layout.activity_fragment2, container, false);
    }
}