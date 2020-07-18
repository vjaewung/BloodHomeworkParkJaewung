package kr.co.kbinsure.bloodhomeworkparkjaewung.fragment;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import kr.co.kbinsure.bloodhomeworkparkjaewung.CurrentLocationActivity;
import kr.co.kbinsure.bloodhomeworkparkjaewung.LocationSettingBox;
import kr.co.kbinsure.bloodhomeworkparkjaewung.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LocationOfMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocationOfMapFragment extends Fragment {

    private Handler handler = new Handler();
    private Boolean isNetworkLocation = false;
    private Boolean isGPSLocation = false;
    ViewGroup       rootView;

    public static LocationOfMapFragment newInstance() {
        LocationOfMapFragment fragment = new LocationOfMapFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_fragment4, container, false);

        LocationManager locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        if(locationManager != null) {
            isGPSLocation = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkLocation = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }
        if (locationManager != null) Log.e("gpsnetwork", "XXXXXX");

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                /*
                 * GPS 또는 Network로 위치설정이 되었다면 바로 이동한다
                 */
                if (isGPSLocation) {
                    Intent intent = new Intent(getActivity(), CurrentLocationActivity.class);
                    intent.putExtra("provider", LocationManager.GPS_PROVIDER);
                    getActivity().startActivity(intent);
                    getActivity().finish();
                } else if (isNetworkLocation) {
                    Intent intent = new Intent(getActivity(), CurrentLocationActivity.class);
                    intent.putExtra("provider", LocationManager.NETWORK_PROVIDER);
                    getActivity().startActivity(intent);
                    getActivity().finish();
                } else {
                    /*
                     * 단말기에 위치설정이 되어있지않으면 위치설정 화면으로 이동한다
                     * PermissionCheckUtil kotlin 파일의 Top-Level 클래스를 호출
                     */
                    FragmentManager manager = LocationOfMapFragment.this.getActivity().getSupportFragmentManager();
                    LocationSettingBox.newInstance().showNow(manager, "위치설정");
                }
            }
        }, 1500);
        return rootView;
    }


}