package kr.co.kbinsure.bloodhomeworkparkjaewung.remainder;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.Random;
import kr.co.kbinsure.bloodhomeworkparkjaewung.R;
import kr.co.kbinsure.bloodhomeworkparkjaewung.common.GirlGroupRandomInit;

public class DiffUtilAndSwipeRefreshActivity extends AppCompatActivity {
    SwipeRefreshLayout swipeRefresh;
    RecyclerView recyclerView;

    GirlGroupRecyclerAdapter adapter;
    private final Random random = new Random(System.currentTimeMillis());
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diff_util_and_swipe_refresh);

        swipeRefresh = findViewById(R.id.swipeRefresh);
        recyclerView = findViewById(R.id.diffRecyclerView);

        adapter =  new GirlGroupRecyclerAdapter(GirlGroupRandomInit.shuffleGirlsGeneration(), "twice");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        swipeRefresh.setOnRefreshListener(() -> new Thread(new Runnable(){
            @Override
            public void run() {

                adapter.setGroupName("generations");
                adapter.applyUpdateGirlGroupList(GirlGroupRandomInit.shuffleGirlsGeneration());

                runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start());
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
    }
}