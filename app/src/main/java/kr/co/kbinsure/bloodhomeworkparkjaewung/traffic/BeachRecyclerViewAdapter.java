package kr.co.kbinsure.bloodhomeworkparkjaewung.traffic;

import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kr.co.kbinsure.bloodhomeworkparkjaewung.R;
import kr.co.kbinsure.bloodhomeworkparkjaewung.pojo.Beach;

import java.util.List;
import java.util.Random;

public class BeachRecyclerViewAdapter extends RecyclerView.Adapter<BeachViewHolder> {

    private List<Beach> lists;
    private Activity owner;

    public BeachRecyclerViewAdapter(List<Beach> lists, Activity owner) {
        this.lists = lists;
        this.owner = owner;
    }


    @NonNull
    @Override
    public BeachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_beach, parent, false);
        return new BeachViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull BeachViewHolder holder, int position) {
        Beach beach = lists.get(position);
        holder.seqId.setText(String.valueOf(beach.seqId));
        holder.beachName.setText(beach.poiNm);
        holder.uniqPop.setText(String.valueOf(beach.uniqPop));
        beach.congestion = String.valueOf(new Random(
                   System.currentTimeMillis()).nextInt(3) + 1);
//        Resources res = owner.getResources();
//        if(beach.congestion.equals("2")){
//            holder.congestion.setBackgroundColor(
//                    res.getColor(android.R.color.holo_orange_light, null));
//            holder.congestion.setText("중간혼잡도");
//        }else if(beach.congestion.equals("3")){
//            holder.congestion.setText("아주혼잡함");
//            holder.congestion.setBackgroundColor(
//                    res.getColor(android.R.color.holo_red_dark, null));
//        }
        //holder.congestion.setText(String.valueOf(beach.congestion));
    }
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
