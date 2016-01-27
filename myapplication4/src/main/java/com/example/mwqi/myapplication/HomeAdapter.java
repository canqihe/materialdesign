package com.example.mwqi.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
 * 创建日期 ： 2015/6/21  15:54
 * <p/>
 * 描      述 ：
 * <p/>
 * <p/>
 * 修订历史 ：
 * <p/>
 * ============================================================
 */
public class HomeAdapter extends RecyclerView.Adapter<MyViewHolder> {


    private List<String> mDatas;


    public HomeAdapter(List<String> mDatas) {
        this.mDatas = mDatas;

    }

    public interface onItemClickListener{
        void onItemClick(View view ,int position);
    }

    public onItemClickListener onItemClickListener;


    public void setOnItemClickListener(onItemClickListener listener){
        this.onItemClickListener = listener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home,parent,false);

        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.text.setText(mDatas.get(position));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(null != onItemClickListener){
                    onItemClickListener.onItemClick(holder.view,position);
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return mDatas.size();
    }


}
class MyViewHolder extends RecyclerView.ViewHolder{

    TextView text;
    View view;

    public MyViewHolder(View itemView) {
        super(itemView);
        this.view = itemView;
        text = (TextView) itemView.findViewById(R.id.text);
    }
}
