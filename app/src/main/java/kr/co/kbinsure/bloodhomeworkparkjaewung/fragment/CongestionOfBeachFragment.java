package kr.co.kbinsure.bloodhomeworkparkjaewung.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.co.kbinsure.bloodhomeworkparkjaewung.R;
import kr.co.kbinsure.bloodhomeworkparkjaewung.beach.BeachDataObject;
import kr.co.kbinsure.bloodhomeworkparkjaewung.beach.BeachRecyclerViewAdapter;
import kr.co.kbinsure.bloodhomeworkparkjaewung.beach.OpenAPIBeachInterface;
import kr.co.kbinsure.bloodhomeworkparkjaewung.common.OkhttpRetrofitBuilderManager;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CongestionOfBeachFragment extends Fragment {

    RecyclerView    beachRV;
    ViewGroup       rootView;
    Context         thisContext;
    BeachRecyclerViewAdapter beachRecyclerViewAdapter;

    RecyclerView    recyclerView; // = rootView.findViewById(R.id.beachRV);
    List<BeachDataObject>     beachList0 = new ArrayList<>();

    public static CongestionOfBeachFragment newInstance() {
        CongestionOfBeachFragment fragment = new CongestionOfBeachFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = (ViewGroup)inflater.inflate(R.layout.activity_fragment3, container, false);

        thisContext = container.getContext();
        beachRV     = rootView.findViewById(R.id.beachRV);
        beachRV.setLayoutManager(new LinearLayoutManager(thisContext));
        beachRecyclerViewAdapter = new BeachRecyclerViewAdapter(beachList0, (Activity) thisContext);

        beachRV.setAdapter(beachRecyclerViewAdapter);
//      beachRV.setAdapter(beachAdapter);

        return rootView;

    }

    @Override
    public void onResume() {

        super.onResume();

        OpenAPIBeachInterface service = OkhttpRetrofitBuilderManager.getTrafficOpenAPIService();
        Log.e("service : ", String.valueOf(service));
        Call<ResponseBody> call = service.beachCongestion();
        Log.e("service : ", String.valueOf(call));

        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonBeach = new JSONObject(response.body().string());
                    int beachSize = 50;
                    List<BeachDataObject> beachList = new ArrayList<>();
                    Log.e("onResponse", String.valueOf(beachList));

                    for( int i = 0 ; i < beachSize ; i++){
                        BeachDataObject beach = new BeachDataObject();
                        JSONObject jsonObject = jsonBeach.getJSONObject("Beach" + i);
                        beach.etlDt = jsonObject.getString("etlDt");
                        beach.seqId = jsonObject.getInt("seqId");
                        beach.poiNm = jsonObject.getString("poiNm");
                        beach.uniqPop = jsonObject.getInt("uniqPop");
                        beach.congestion = jsonObject.getString("congestion");
                        beachList.add(beach);
                    }
                    displayBeach(beachList);
                }catch (Exception e){}
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("onFailure", "**********************************************************");
            }
        });
    }
    private void displayBeach(List<BeachDataObject> lists){
        LinearLayoutManager manager = new LinearLayoutManager(thisContext);
        beachRV.setLayoutManager(manager);
        beachRV.addItemDecoration(new DividerItemDecoration(thisContext, DividerItemDecoration.VERTICAL));
        BeachRecyclerViewAdapter beachAdapter = new BeachRecyclerViewAdapter(lists, (Activity) thisContext);
        beachRV.setAdapter(beachAdapter);
    }




    /*
    //BloodAdapter adapter;
    ViewGroup rootView;

    public static RequestInfoFragment newInstance() {
        RequestInfoFragment fragment = new RequestInfoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = (ViewGroup)inflater.inflate(R.layout.activity_fragment3, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.beachRV);
        //recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));

//        adapter = new BloodAdapter();
//        recyclerView.setAdapter(adapter);

        return rootView;

//        return inflater.inflate(R.layout.activity_fragment3, container, false);
    }


    /*
    // Inflate the layout for this fragment
        rootView = (ViewGroup)inflater.inflate(R.layout.fragment_outputblood, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));


        adapter = new BloodAdapter();

        //그냥 하드코딩하겠음. Blood에다 get/set하는 처리는 skip
        adapter.addItem(new Blood("펭수 / 사고", "RH-A / 1L / 전혈"));
        adapter.addItem(new Blood("범이 / 질병 ", "RH-O / 500ml/ 혈소판"));
        adapter.addItem(new Blood("북극곰 / 사고", "RH-AB / 1.5L / 전혈"));

        recyclerView.setAdapter(adapter);



        return rootView;
     */
}