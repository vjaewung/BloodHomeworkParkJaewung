package kr.co.kbinsure.bloodhomeworkparkjaewung;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OkHttpRetrofitOpenAPIManager {

    private static String TARGET_ADDRESS = "https://8oi9s0nnth.apigw.ntruss.com/";

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(TARGET_ADDRESS);

    private static OkHttpClient okHttpClient;

    static {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS).build();
        retrofitBuilder.client(okHttpClient); //OkHttp와 연동
    }
    public static DATAOpenAPIService getOpenAPIRESTService(){
        return  retrofitBuilder.build().create(DATAOpenAPIService.class);
    }
}


