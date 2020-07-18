package kr.co.kbinsure.bloodhomeworkparkjaewung.map;

import android.Manifest;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class RuntimePermissionCheckUtil {

    public static final int REQUEST_CODE = 500;

    public static void requestPermission(AppCompatActivity activity) {

        ActivityCompat.requestPermissions(activity, new String[]{ Manifest.permission.ACCESS_FINE_LOCATION }, REQUEST_CODE);
    }
    public static Boolean isPermissionGranted(String [] grantPermissions, int [] grantResults){

        int permissionSize = grantPermissions.length;
        for (int i = 0 ; i < permissionSize ; i++) {
            if(Manifest.permission.ACCESS_FINE_LOCATION == grantPermissions[i]){
                return grantResults[i] == PackageManager.PERMISSION_GRANTED;
            }
        }
        return false;
    }
}
