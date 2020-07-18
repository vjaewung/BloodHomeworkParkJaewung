package kr.co.kbinsure.bloodhomeworkparkjaewung.beach;

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

import java.util.List;
import java.util.Random;

public class BeachRecyclerViewAdapter extends RecyclerView.Adapter<BeachViewHolder> {

    private List<BeachDataObject> lists;
    private Activity owner;

    public BeachRecyclerViewAdapter(List<BeachDataObject> lists, Activity owner) {
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
        BeachDataObject beach = lists.get(position);
        holder.beachName.setText(String.format("[ %s ]", beach.poiNm));
        holder.beachName.setPaintFlags(holder.beachName.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        holder.beachName.setOnClickListener( bName ->{
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri targetURL = Uri.parse(String.format("geo://37,127?q=%s", Uri.encode(beach.poiNm)));
            intent.setData(targetURL);
            intent.setPackage("com.google.android.apps.maps");
            owner.startActivity(intent);
        });

        /**
         * 현재(7월 18일 기준 우리나라 해수욕장 혼잡도가 좋아 테스트 코드를 삽입합니다
         * 시즌때는 이코드는 주석처리 하세요
         * beach.congestion = String.valueOf(new Random(System.currentTimeMillis()).nextInt(3) + 1);
         */
        beach.congestion = String.valueOf(new Random(System.currentTimeMillis()).nextInt(3) + 1);
        Resources res = owner.getResources();
        switch(beach.congestion) {
            case "2": {
                holder.congestion.setBackgroundColor(owner.getResources().getColor(android.R.color.holo_orange_light));
                holder.congestion.setText(res.getString(R.string.crowded_side));
                holder.congestion.setTextColor(owner.getResources().getColor(android.R.color.holo_purple));
                break;
            }
            case "3": {
                holder.congestion.setText(res.getString(R.string.very_complex));
                holder.congestion.setBackgroundColor(owner.getResources().getColor(android.R.color.holo_red_dark));
                holder.congestion.setTextColor(owner.getResources().getColor(android.R.color.white));
                break;
            }
            default:
                break;
        }
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
