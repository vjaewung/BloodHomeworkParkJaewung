package kr.co.kbinsure.bloodhomeworkparkjaewung.difflist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.Random;
import kr.co.kbinsure.bloodhomeworkparkjaewung.R;
import kr.co.kbinsure.bloodhomeworkparkjaewung.common.RandomInitialzation;

public class DiffUtilSwipeRefreshActivity extends AppCompatActivity {
    SwipeRefreshLayout swipeRefresh;
    RecyclerView recyclerView;

    DiffRecyclerAdapter adapter;
    private final Random random = new Random(System.currentTimeMillis());
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diff_swipe_refresh);

        swipeRefresh = findViewById(R.id.swipeRefresh);
        recyclerView = findViewById(R.id.diffRecyclerView);

        adapter =  new DiffRecyclerAdapter(RandomInitialzation.shuffleGirlsGeneration(), "twice");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        swipeRefresh.setOnRefreshListener(() -> new Thread(new Runnable(){
            @Override
            public void run() {

                adapter.setGroupName("generations");
                adapter.applyUpdateGirlGroupList(RandomInitialzation.shuffleGirlsGeneration());

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