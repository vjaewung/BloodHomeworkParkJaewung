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
import kr.co.kbinsure.bloodhomeworkparkjaewung.beach.BeachOkhttpRetrofitBuilderManager;
import kr.co.kbinsure.bloodhomeworkparkjaewung.bicycle.BicycleDataObject;
import kr.co.kbinsure.bloodhomeworkparkjaewung.bicycle.BicycleRecyclerViewAdapter;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RentStatusOfBicycleFragment extends Fragment {

    RecyclerView    bicycleRV;
    ViewGroup       rootView;
    Context         thisContext;
    BicycleRecyclerViewAdapter bicycleRecyclerViewAdapter;

    RecyclerView    recyclerView; // = rootView.findViewById(R.id.beachRV);
    List<BicycleDataObject>     bicycleList0 = new ArrayList<>();

    public static RentStatusOfBicycleFragment newInstance() {
        RentStatusOfBicycleFragment fragment = new RentStatusOfBicycleFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = (ViewGroup)inflater.inflate(R.layout.activity_fragment5, container, false);

        thisContext = container.getContext();
        bicycleRV     = rootView.findViewById(R.id.bicycleRV);
        bicycleRV.setLayoutManager(new LinearLayoutManager(thisContext));
        bicycleRecyclerViewAdapter = new BicycleRecyclerViewAdapter(bicycleList0, (Activity) thisContext);

        bicycleRV.setAdapter(bicycleRecyclerViewAdapter);
//      bicycleRV.setAdapter(bicycleAdapter);

        return rootView;

    }

    @Override
    public void onResume() {

        super.onResume();

        OpenAPIBeachInterface service = BeachOkhttpRetrofitBuilderManager.getTrafficOpenAPIService();
        Log.e("service :------> ", String.valueOf(service));
        Call<ResponseBody> call = service.beachCongestion();
        Log.e("service :------> ", String.valueOf(call));

        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonBicycle = new JSONObject(response.body().string());
                    Log.e("response.body", response.body().string());

                    Log.e("onResponse", String.valueOf(jsonBicycle));

                    int bicycleSize = 10;
                    List<BicycleDataObject> bicycleList = new ArrayList<>();
                    Log.e("onResponse", String.valueOf(bicycleList));

                    for( int i = 0 ; i < bicycleSize ; i++){
                        BicycleDataObject bicycle = new BicycleDataObject();
                        JSONObject jsonObject = jsonBicycle.getJSONObject("row" + i);
                        bicycle.stationId = jsonObject.getString("stationId");
                        bicycle.stationName = jsonObject.getString("stationName");
                        bicycle.stationLatitude = jsonObject.getString("stationLatitude");
                        bicycle.stationLongitude = jsonObject.getString("stationLongitude");
                        bicycle.parkingBikeTotCnt = jsonObject.getInt("parkingBikeTotCnt");
                        bicycleList.add(bicycle);
                    }
                    displayBeach(bicycleList);
                }catch (Exception e){}
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("onFailure", "**********************************************************");
            }
        });
    }
    private void displayBeach(List<BicycleDataObject> lists){
        LinearLayoutManager manager = new LinearLayoutManager(thisContext);
        bicycleRV.setLayoutManager(manager);
        bicycleRV.addItemDecoration(new DividerItemDecoration(thisContext, DividerItemDecoration.VERTICAL));
        BicycleRecyclerViewAdapter beachAdapter = new BicycleRecyclerViewAdapter(lists, (Activity) thisContext);
        bicycleRV.setAdapter(beachAdapter);
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