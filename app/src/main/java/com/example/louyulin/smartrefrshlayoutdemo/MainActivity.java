package com.example.louyulin.smartrefrshlayoutdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private SmartRefreshLayout refreshlayout;
    private MaterialHeader mMaterialHeader;
    private List<String> datas = new ArrayList<>();
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rv);
        refreshlayout = (SmartRefreshLayout) findViewById(R.id.refreshlayout);
        refreshlayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshlayout.setEnableAutoLoadMore(false);
        refreshlayout.setRefreshFooter(new ClassicsFooter(this));
        refreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
            }
        });
        refreshlayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);

            }
        });

        mMaterialHeader = (MaterialHeader) refreshlayout.getRefreshHeader();

        mMaterialHeader.setColorSchemeColors(Color.parseColor("#FFFF8888"));
        for (int i = 0; i < 40; i++) {
            datas.add(i + "");
        }
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(manager);
        rv.setAdapter(new RvAdapter());
    }

    class RvAdapter extends RecyclerView.Adapter<RvAdapter.Holder> {


        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            holder.tv.setText(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        class Holder extends RecyclerView.ViewHolder {
            TextView tv;

            public Holder(View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.tv);
            }
        }
    }
}
