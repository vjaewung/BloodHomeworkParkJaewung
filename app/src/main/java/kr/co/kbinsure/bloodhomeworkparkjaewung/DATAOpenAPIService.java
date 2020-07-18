package kr.co.kbinsure.bloodhomeworkparkjaewung;

import kr.co.kbinsure.bloodhomeworkparkjaewung.pojo.StoreSaleResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DATAOpenAPIService {

    @GET("corona19-masks/v1/storesByAddr/json?")
    Call<StoreSaleResult> maskStoreByAddressInfo(@Query("address") String address);
}