package kr.co.kbinsure.bloodhomeworkparkjaewung.bicycle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
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
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BicycleInfoRequestActivity extends AppCompatActivity {

    String serviceKey = "446a56726a766a6136344a74746842";
    RecyclerView bicycleRV;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bicycle_status_info);
        bicycleRV = findViewById(R.id.bicycleRV);
        progressBar = findViewById(R.id.progress);
    }

    @Override
    protected void onResume() {
        super.onResume();
        OpenAPIBicycleInterface service = BicycleOkhttpRetrofitBuilderManager.getBicycleOpenAPIService();
//        Call<ResponseBody> call = service. .trafficOpenAPIService(serviceKey);
//        Call<ResponseBody> call = service.getBicyleStatusInfo(serviceKey);
        Call<ResponseBody> call = service.getBicyleStatusInfo();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressBar.setVisibility(View.INVISIBLE);
                try {
                    JSONObject jsonBicycle = new JSONObject(response.body().string());
                    int bicycleSize = 50;
                    List<BicycleDataObject>  bicycleList = new ArrayList<>();
                    for( int i = 0 ; i < bicycleSize ; i++){
                        BicycleDataObject bicycle = new BicycleDataObject();
                        JSONObject jsonObject = jsonBicycle.getJSONObject("Beach" + i);
                        bicycle.stationId = jsonObject.getString("stationId");
                        bicycle.stationName = jsonObject.getString("stationName");
                        bicycle.stationLatitude = jsonObject.getString("stationLatitude");
                        bicycle.stationLongitude = jsonObject.getString("stationLongitude");
                        bicycle.parkingBikeTotCnt = jsonObject.getInt("parkingBikeTotCnt");
                        bicycleList.add(bicycle);
                    }
                    displayBicycle(bicycleList);
                }catch (Exception e){}

            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
    private void displayBicycle(List<BicycleDataObject> lists){
        LinearLayoutManager manager = new LinearLayoutManager(this);
        bicycleRV.setLayoutManager(manager);
        bicycleRV.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        BicycleRecyclerViewAdapter bicycleAdapter = new BicycleRecyclerViewAdapter(lists,this);
        bicycleRV.setAdapter(bicycleAdapter);
    }
}