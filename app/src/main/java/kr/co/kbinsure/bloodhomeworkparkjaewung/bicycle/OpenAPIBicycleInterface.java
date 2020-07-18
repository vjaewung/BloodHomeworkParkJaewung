package kr.co.kbinsure.bloodhomeworkparkjaewung.bicycle;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenAPIBicycleInterface {
//    @GET("http://openapi.seoul.go.kr:8088/")
//    Call<ResponseBody> getBicyleStatusInfo(@Query("ServiceKey") String serviceKey);

    @GET("http://openapi.seoul.go.kr:8088/446a56726a766a6136344a74746842/json/bikeList/1/5/")
    Call<ResponseBody> getBicyleStatusInfo();

//    @GET("http://openapi.seoul.go.kr:8088/json?")
//    Call<ResponseBody> getBicyleStatusInfo(@Query("address") String address);
}
