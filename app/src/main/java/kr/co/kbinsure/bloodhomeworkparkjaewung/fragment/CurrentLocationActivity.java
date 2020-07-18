package kr.co.kbinsure.bloodhomeworkparkjaewung.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import kr.co.kbinsure.bloodhomeworkparkjaewung.R;
import kr.co.kbinsure.bloodhomeworkparkjaewung.RuntimePermissionCheckUtil;

public class CurrentLocationActivity extends AppCompatActivity {
    /*
     * Fused Location Provider Api 에서
     * 위치 업데이트를위한 서비스 품질등 다양한요청을
     * 설정하는데 사용하는 데이터객체.
     */
    private LocationRequest mLocationRequest;

    /*
     * 현재위치정보를 나타내는 객체
     */
    private Location mCurrentLocation;

    /*
     * 현재 위치제공자(Provider)와 상호작용하는 진입점
     * (융합된 제공자 정보를 사용가능 11.0.0이후 이 클래스로 바뀜)
     */
    private FusedLocationProviderClient mFusedLocationClient;
    /*
     * 현재 단말기에 설정된 위치 Provider
     */
    private String currentProvider;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_location);

        /*
         * 현재 설정된 위치제공자를 가져온다
         */
        currentProvider = getIntent().getStringExtra("provider");

        /*
         * 6.0 이상일 경우 위치퍼미션에 대한 사용자 허락을 받는다
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkMyPermissionLocation();
        } else {
            initGoogleMapLocation();
        }
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.supportMap);
        /*
         * 비동기 방식으로 GoogleMap 초기설정을 진행한다
         */
        mapFragment.getMapAsync(googleMap -> {
            mMap = googleMap;
            mMap.getUiSettings().setZoomControlsEnabled(true);
            MarkerOptions options = new MarkerOptions();
            /*
             * 처음 위치를 적도로 놓는다
             */
            options.position(new LatLng(0.0, 0.0));
            /*
             * 마커 등록
             */
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
            Marker marker = mMap.addMarker(options);

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(),1f));
        });
    }
    /*
     * Permission Check 여부를 확인하는 메소드
     */
    private void  checkMyPermissionLocation() {
        /*
         * Permission 허락을 받지않았다면 Permission Check 를 진행
         */
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            RuntimePermissionCheckUtil.requestPermission(this);
        } else { //Permission 허락을 받은 상태라면 위치 정보 초기화를 진행한다
            initGoogleMapLocation();
        }
    }
    /*
     * 위치 이벤트에 대한 콜백을 제공.
     * 단말기위치정보가 update되면 자동으로 호출
     * Fused Location Provider Api에 등록된
     * 위치알림을 수신하는 데 사용
     */
    private  LocationCallback mLocationCallback = new LocationCallback(){
        /*
         *  성공적으로 위치정보와 넘어왔을때를 동작하는 Call back 함수
         */

        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            mCurrentLocation = locationResult.getLocations().get(0);

            MarkerOptions options = new MarkerOptions();
            options.position(new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()));
            BitmapDescriptor icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN);
            options.icon(icon);
            options.title("내 현재 위치");

            Marker marker = mMap.addMarker(options);

            /*
             * 단말기 현재 위치로 이동한다
             */
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    marker.getPosition(),
                    16f
            ));
            /*
             * 지속적으로 위치정보를 받으려면
             * mLocationRequest.setNumUpdates(1)을 주석처리하고
             * 밑에 코드 주석을 푼다
             */
            //mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
        /*
         * 현재 콜백이 동작가능한지에 대한 여부
         */
        public void onLocationAvailability(LocationAvailability availability) {
            //boolean isLocation = availability.isLocationAvailable();
        }
    };
    /*
     * 현재 위치를 알아내는 코드구성
     */
    @SuppressLint("MissingPermission")
    void initGoogleMapLocation(){
        /*
         * FusedLocationProviderApi 에서
         * 위치 업데이트를위한 서비스 품질등 다양한요청을
         * 설정하는데 사용하는 데이터객체인
         * LocationRequest를 획득
         */
        mLocationRequest = new LocationRequest();
        /*
         *위치가 update되는 주기
         */
        mLocationRequest.setInterval(10000);
        /*
         * 위치 획득후 update되는 주기
         */
        mLocationRequest.setFastestInterval(10000);

        /*
         * update되는 횟수 여기선 1번만 설정한다
         */
        mLocationRequest.setNumUpdates(1);

        if (currentProvider.equalsIgnoreCase(LocationManager.GPS_PROVIDER)) {
            //배터리소모에 상관없이 정확도를 최우선으로 고려
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        } else {
            //배터리와 정확도의 밸런스를 고려하여 위치정보를 획득(정확도 다소 높음)
            mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        }
        /*
         * 위치서비스 설정 정보를 저장하기 위한 빌더객체획득
         */
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        /*
         * 현재 위치정보 Setting정보가 저장된 LocationRequest
         * 객체를 등록
         */
        builder.addLocationRequest(mLocationRequest);

        /*
         * 위치정보 요청을 수행하기 위해 단말기에서
         * 관련 시스템 설정(Gps,Network)이 활성화되었는지 확인하는 클래스인
         * SettingClient를 획득한다
         */

        SettingsClient mSettingsClient = LocationServices.getSettingsClient(this);

        /*
         * 위치 서비스 유형을 저장하고
         * 위치 설정에도 사용하기위해
         * LocationSettingsRequest 객체를 획득
         */
        LocationSettingsRequest mLocationSettingsRequest = builder.build();
        Task<LocationSettingsResponse> locationResponse = mSettingsClient.checkLocationSettings(mLocationSettingsRequest);

        /*
         * 현재 위치제공자(Provider)와 상호작용하는 진입점인
         * FusedLocationProviderClient 객체를 획득
         */
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        /*
         * 정상적으로 위치정보가 설정되었다면
         * 위치업데이트를 요구하고, 설정이 잘못되었다면
         */
        locationResponse.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                Toast.makeText(getApplicationContext(), "위치 받기 성공" ,Toast.LENGTH_SHORT).show();
                mFusedLocationClient.requestLocationUpdates(
                        mLocationRequest, mLocationCallback, Looper.myLooper());
            }
        });
        locationResponse.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                ApiException exception = (ApiException)e;
                Toast.makeText(getApplicationContext(), exception.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    /*
     * 사용자가 Permission Check 대화상자(허락,거부)에서 선택한 결과를
     * 처리하는 콜백 메소드
     */

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //요청코드가 맞지 않는다면
        if (requestCode != RuntimePermissionCheckUtil.REQUEST_CODE) {
            return ;
        }
        /*
         * RuntimePermissionCheckUtil.java 파일의 isPermissionGranted 함수를 호출
         */
        if(RuntimePermissionCheckUtil.isPermissionGranted(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                grantResults)){
            initGoogleMapLocation();
        }else { //사용자가 허락하지 않을경우
            Toast.makeText(this, "위치정보사용을 허락 하지않아 앱을 중지합니다",
                    Toast.LENGTH_SHORT).show();
            //finish();
        }
    }

    /*
     * 현재 화면을 나갈때 반드시 등록된
     * 위치정보 알림을 제거
     */
    public void onStop() {
        super.onStop();
        if(mFusedLocationClient != null)  mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }
}