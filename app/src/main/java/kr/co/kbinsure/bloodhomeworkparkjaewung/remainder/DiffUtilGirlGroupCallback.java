package kr.co.kbinsure.bloodhomeworkparkjaewung.remainder;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class DiffUtilGirlGroupCallback extends DiffUtil.Callback {

    private List<Integer> oldGirlGroupList;
    private List<Integer> newGirlGroupList;

    public DiffUtilGirlGroupCallback(List<Integer> oldGirlGroupList,
                                     List<Integer> newGirlGroupList) {
        this.oldGirlGroupList = oldGirlGroupList;
        this.newGirlGroupList = newGirlGroupList;
    }
    @Override
    public int getOldListSize() {
        return oldGirlGroupList.size();
    }
    @Override
    public int getNewListSize() {
        return newGirlGroupList.size();
    }
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
    /*@Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }*/
}
