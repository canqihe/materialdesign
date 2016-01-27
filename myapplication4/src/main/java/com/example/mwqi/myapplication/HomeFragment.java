package com.example.mwqi.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.kogitune.activity_transition.ActivityTransitionLauncher;
import com.telly.floatingaction.FloatingAction;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * <p/>
 * 版      权 ： 黑马程序员教育集团 版权所有 (c) 2015
 * <p/>
 * 作      者  :  马伟奇
 * <p/>
 * 版      本 ： 1.0
 * <p/>
 * 创建日期 ： 2015/6/21  15:38
 * <p/>
 * 描      述 ：
 * <p/>
 * <p/>
 * 修订历史 ：
 * <p/>
 * ============================================================
 */
public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private View view;
    private List<String> mDatas;
    private SwipeRefreshLayout swipe_refreshLayout;
    private LinearLayoutManager manager;
    private int lastVisibleItemPosition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // view = inflater.inflate(R.layout.frament_home, container, false);
        view = inflater.inflate(R.layout.frament_home,container,false);
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        swipe_refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refreshLayout);

        swipe_refreshLayout.setOnRefreshListener(this);

        swipe_refreshLayout.setColorScheme(R.color.color1, R.color.color2, R.color.color3, R.color.color4);


        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

//                if(lastVisibleItemPosition == 20){
//                  handler.sendEmptyMessageDelayed(1,3000);
//                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = manager.findLastVisibleItemPosition();
            }
        });

        addList();

        HomeAdapter adapter = new HomeAdapter(mDatas);

        recyclerView.setAdapter(adapter);


        manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);


       // StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(manager);

        adapter.setOnItemClickListener(new HomeAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("value", mDatas.get(position));
               // getActivity().startActivity(intent);
                ActivityTransitionLauncher.with(getActivity()).from(view).launch(intent);
            }
        });






    }





    public AppCompatActivity activity;
    public void setActivity(AppCompatActivity activity){
        this.activity = activity;
    }

    private void addList() {
        mDatas = getList();
    }

    private List<String> getList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("我是item--" + i);
        }
        return list;
    }


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    Toast.makeText(getActivity(), "下拉刷新", Toast.LENGTH_SHORT).show();

                    swipe_refreshLayout.setRefreshing(false);
                    break;
            }
        }
    };

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(0,4000);

    }
}
