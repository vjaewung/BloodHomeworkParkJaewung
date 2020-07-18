package kr.co.kbinsure.bloodhomeworkparkjaewung.remainder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kr.co.kbinsure.bloodhomeworkparkjaewung.R;
import kr.co.kbinsure.bloodhomeworkparkjaewung.common.GirlGroupRandomInit;


public class GirlGroupRecyclerAdapter extends RecyclerView.Adapter<GirlGroupViewHolder> {

    private List<Integer> currentGroupList;
    private String groupName;

    public GirlGroupRecyclerAdapter(List<Integer> items, String groupName){
        currentGroupList = items;
        this.groupName = groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    @NonNull
    @Override
    public GirlGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.girls_group_recycler_item, parent, false);
        return new GirlGroupViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull GirlGroupViewHolder holder, int position) {
        Integer imageKey = currentGroupList.get(position);
        holder.memberTV.setText(GirlGroupRandomInit.getGirlGenerationName(imageKey));
        holder.memberIV.setImageResource(imageKey);
    }
    @Override
    public int getItemCount() {
        return currentGroupList.size();
    }
    public void applyUpdateGirlGroupList(List<Integer> newGroupList){

        final DiffUtilGirlGroupCallback diffCallback =
                new DiffUtilGirlGroupCallback(currentGroupList, newGroupList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        diffResult.convertOldPositionToNew(0);
        currentGroupList = newGroupList;
        diffResult.dispatchUpdatesTo(this);
    }
}
