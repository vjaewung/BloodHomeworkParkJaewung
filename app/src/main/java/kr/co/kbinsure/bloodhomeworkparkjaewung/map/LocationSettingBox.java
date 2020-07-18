package kr.co.kbinsure.bloodhomeworkparkjaewung.map;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class LocationSettingBox extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState)  {

        return new AlertDialog.Builder(getContext())
                .setMessage("단말기의 위치설정이 필요합니다")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent  intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }).create();
    }
    public static LocationSettingBox newInstance(){
        return new LocationSettingBox();
    }
}
