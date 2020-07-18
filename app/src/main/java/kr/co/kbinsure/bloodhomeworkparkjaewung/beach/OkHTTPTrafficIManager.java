package kr.co.kbinsure.bloodhomeworkparkjaewung.beach;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import kr.co.kbinsure.bloodhomeworkparkjaewung.beach.DataOpenTrafficAPIService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class OkHTTPTrafficIManager {

    private static String TARGET_ADDRESS = "https://seantour.com/";

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    //.addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(TARGET_ADDRESS);

    private static OkHttpClient okHttpClient;

    static {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS).build();
        retrofitBuilder.client(okHttpClient);
    }
    public static DataOpenTrafficAPIService getTrafficOpenAPIService(){
        return  retrofitBuilder.build().create(DataOpenTrafficAPIService.class);
    }
}


