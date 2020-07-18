package kr.co.kbinsure.bloodhomeworkparkjaewung.beach;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kr.co.kbinsure.bloodhomeworkparkjaewung.R;


public class BeachViewHolder extends RecyclerView.ViewHolder {

    TextView seqId;
    TextView beachName;
    TextView uniqPop;
    Button   congestion;

    public BeachViewHolder(@NonNull View itemView) {
        super(itemView);
        seqId = itemView.findViewById(R.id.seqId);
        beachName = itemView.findViewById(R.id.beachName);
        uniqPop = itemView.findViewById(R.id.uniqPop);
        congestion = itemView.findViewById(R.id.congestion);
    }
}
