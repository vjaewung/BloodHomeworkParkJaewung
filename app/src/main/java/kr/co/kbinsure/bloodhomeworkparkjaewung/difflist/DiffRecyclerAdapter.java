package kr.co.kbinsure.bloodhomeworkparkjaewung.difflist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kr.co.kbinsure.bloodhomeworkparkjaewung.R;
import kr.co.kbinsure.bloodhomeworkparkjaewung.common.RandomInitialzation;


public class DiffRecyclerAdapter extends RecyclerView.Adapter<DiffViewHolder> {

    private List<Integer> currentGroupList;
    private String groupName;

    public DiffRecyclerAdapter(List<Integer> items, String groupName){
        currentGroupList = items;
        this.groupName = groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    @NonNull
    @Override
    public DiffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.recycler_item_group, parent, false);
        return new DiffViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull DiffViewHolder holder, int position) {
        Integer imageKey = currentGroupList.get(position);
        holder.memberTV.setText(RandomInitialzation.getGirlGenerationName(imageKey));
        holder.memberIV.setImageResource(imageKey);
    }
    @Override
    public int getItemCount() {
        return currentGroupList.size();
    }
    public void applyUpdateGirlGroupList(List<Integer> newGroupList){

        final DiffUtilCallback diffCallback =
                new DiffUtilCallback(currentGroupList, newGroupList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        diffResult.convertOldPositionToNew(0);
        currentGroupList = newGroupList;
        diffResult.dispatchUpdatesTo(this);
    }
}
