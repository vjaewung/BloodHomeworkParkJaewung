package kr.co.kbinsure.bloodhomeworkparkjaewung.beach;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface OpenAPIBeachInterface {
    @GET("seantour_map/travel/getBeachCongestionApi.do")
    Call<ResponseBody>   beachCongestion();
}
