package kr.co.kbinsure.bloodhomeworkparkjaewung.difflist;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.kbinsure.bloodhomeworkparkjaewung.R;

public class DiffViewHolder extends RecyclerView.ViewHolder{

    public View itemRoot;
    public TextView memberTV;
    public CircleImageView memberIV;

    public DiffViewHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot = itemView;
        memberTV = itemView.findViewById(R.id.member_name);
        memberIV = itemView.findViewById(R.id.girls_group_member_image);
    }
}
