package kr.co.kbinsure.bloodhomeworkparkjaewung.beach;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import kr.co.kbinsure.bloodhomeworkparkjaewung.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchForInformationRestrictedTrafficActivity extends AppCompatActivity {

    String serviceKey = "TqG1MG9kgKm%2F8l7szzKNMW9mckCDAqAViWng8owRIqGGm%2FU9p0FY9Z0%2Fjt8i25B2KV%2F7uEQaQ3%2Bss3PirivjwQ%3D%3D";
    RecyclerView beachRV;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_information_restricted_traffic);
        beachRV = findViewById(R.id.beachRV);
        progressBar = findViewById(R.id.progress);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DataOpenTrafficAPIService service = OkHTTPTrafficIManager.getTrafficOpenAPIService();
        //Call<ResponseBody> call = service.trafficOpenAPIService(serviceKey);
        Call<ResponseBody> call = service.beachCongestion();
        progressBar.setVisibility(View.VISIBLE);
//        try {
//            Thread.sleep(2000);
//        } catch(InterruptedException e) {}
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressBar.setVisibility(View.INVISIBLE);
                try {
                    JSONObject jsonBeach = new JSONObject(response.body().string());
                    int beachSize = 50;
                    List<Beach>  beachList = new ArrayList<>();
                    for( int i = 0 ; i < beachSize ; i++){
                        Beach beach = new Beach();
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
                progressBar.setVisibility(View.GONE);
            }
        });
    }
    private void displayBeach(List<Beach> lists){
        LinearLayoutManager manager = new LinearLayoutManager(this);
        beachRV.setLayoutManager(manager);
        beachRV.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        BeachRecyclerViewAdapter beachAdapter = new BeachRecyclerViewAdapter(lists,this);
        beachRV.setAdapter(beachAdapter);
    }
}