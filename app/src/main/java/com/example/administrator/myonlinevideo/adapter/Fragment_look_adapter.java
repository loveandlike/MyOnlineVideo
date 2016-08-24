package com.example.administrator.myonlinevideo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myonlinevideo.R;
import com.example.administrator.myonlinevideo.user.Student;
import com.example.administrator.myonlinevideo.util.NewsDBUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/17.
 */
public class Fragment_look_adapter extends RecyclerView.Adapter<Fragment_look_adapter.ItemView> {
    private Context context;
    private ArrayList<Student> mDatas;
    NewsDBUtil dbUtil = NewsDBUtil.getsInstance(context);

    public Fragment_look_adapter(NewsDBUtil dbUtil) {
        this.context = context;
        this.dbUtil = dbUtil;
    }

    //回调接口
    public interface ItemClick {
//        void setOnItemClick(View view,int position);
        void setOnItemClick(int position);
        void onItemLongClick(int position);
    }

    private ItemClick click;
    public void setOnItemClickListener(ItemClick listener) {
        this.click = listener;
    }
    @Override
    public ItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student_look, parent, false);
        return new ItemView(view);
    }

    public void addData(int position) {
        mDatas.add(position,new Student("",12,""));
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onBindViewHolder(ItemView holder, final int position) {




        holder.tv_name.setText(dbUtil.getNewsList().get(position).getName());
        holder.tv_age.setText(dbUtil.getNewsList().get(position).getAge() + "");
        holder.tv_xing.setText(dbUtil.getNewsList().get(position).getXing());
    }

    @Override
    public int getItemCount() {
        return dbUtil.getNewsList().size();
    }

    public class ItemView extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_age;
        TextView tv_xing;


        public ItemView(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.list_name);
            tv_age = (TextView) itemView.findViewById(R.id.list_age);
            tv_xing = (TextView) itemView.findViewById(R.id.list_xing);
        }
    }


}
