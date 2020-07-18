package kr.co.kbinsure.bloodhomeworkparkjaewung.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.kbinsure.bloodhomeworkparkjaewung.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailRequestFragment extends Fragment {

    public static DetailRequestFragment newInstance() {
        DetailRequestFragment fragment = new DetailRequestFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_fragment1, container, false);
    }
}