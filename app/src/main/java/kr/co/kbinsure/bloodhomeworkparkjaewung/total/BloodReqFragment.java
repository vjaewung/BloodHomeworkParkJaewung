package kr.co.kbinsure.bloodhomeworkparkjaewung.total;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.kbinsure.bloodhomeworkparkjaewung.R;
import kr.co.kbinsure.bloodhomeworkparkjaewung.common.BloodApplication;

/*
import kr.co.material.basic.R;
import kr.co.material.basic.common.GirlGroupApplication;
import kr.co.material.basic.common.GirlGroupRandomInit;
*/

public class BloodReqFragment extends Fragment{

    public static BloodReqFragment newInstance() {
        return new BloodReqFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView)inflater.inflate(R.layout.activity_material_text_and_button,
                container, false);
        rv.setLayoutManager(new LinearLayoutManager(BloodApplication.getGirlGroupContext()));
        rv.addItemDecoration(new DividerItemDecoration(
                BloodApplication.getGirlGroupContext(),
                LinearLayoutManager.VERTICAL
        ));
        //rv.setAdapter(new GirlGroupFinalRecyclerAdapter(kpop,getActivity()));
        //rv.setAdapter(new GirlGroupFinalRecyclerAdapter(GirlGroupRandomInit.shuffleTwice(),getActivity()));
        return  rv;
    }

     */

}
