package kr.co.kbinsure.bloodhomeworkparkjaewung.bicycle;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kr.co.kbinsure.bloodhomeworkparkjaewung.R;
import kr.co.kbinsure.bloodhomeworkparkjaewung.bicycle.BicycleDataObject;
import kr.co.kbinsure.bloodhomeworkparkjaewung.bicycle.BicycleViewHolder;

import java.util.List;

public class BicycleRecyclerViewAdapter extends RecyclerView.Adapter<BicycleViewHolder> {


    private List<BicycleDataObject> lists;
    private Activity owner;

    public BicycleRecyclerViewAdapter(List<BicycleDataObject> Lists, Activity owner) {
        this.lists = lists;
        this.owner = owner;
    }






    @NonNull
    @Override
    public BicycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_bicycle, parent, false);
        return new BicycleViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull BicycleViewHolder holder, int position) {
        BicycleDataObject bicycle = lists.get(position);

//        holder.stationId.setText(String.format("[ %s ]", bicycle.stationId));
//        holder.stationName.setPaintFlags(holder.bicycle.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    };

    @Override
    public int getItemCount() {
        try {
            return lists.size();
        }
        catch (Exception ex) {
            return 0;
        }
    }
}
