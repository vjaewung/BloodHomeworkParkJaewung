package kr.co.kbinsure.bloodhomeworkparkjaewung.bicycle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kr.co.kbinsure.bloodhomeworkparkjaewung.R;


public class BicycleViewHolder extends RecyclerView.ViewHolder {

    TextView stationId;
    TextView stationName;
    TextView stationLatitude;
    TextView stationLongitude;
    Button   parkingBikeTotCnt;

    public BicycleViewHolder(@NonNull View itemView) {

        super(itemView);

        stationId           = itemView.findViewById(R.id.stationId);
        stationName         = itemView.findViewById(R.id.stationName);
        stationLatitude     = itemView.findViewById(R.id.stationLatitude);
        stationLongitude    = itemView.findViewById(R.id.stationLongitude);
        parkingBikeTotCnt   = itemView.findViewById(R.id.parkingBikeTotCnt);

    }
}
