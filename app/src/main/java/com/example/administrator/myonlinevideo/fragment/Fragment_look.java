package com.example.administrator.myonlinevideo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myonlinevideo.R;
import com.example.administrator.myonlinevideo.adapter.Fragment_look_adapter;
import com.example.administrator.myonlinevideo.util.NewsDBUtil;

/**
 * Created by Administrator on 2016/8/17.
 */
public class Fragment_look extends Fragment {
    private RecyclerView recyclerView;
    private Fragment_look_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_look, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_look_recyclerview);
        NewsDBUtil dbUtil = NewsDBUtil.getsInstance(getContext());
        adapter = new Fragment_look_adapter(dbUtil);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new Fragment_look_adapter.ItemClick() {
            @Override
            public void setOnItemClick(int position) {
                adapter.notifyItemInserted(position);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onItemLongClick(int position) {
                adapter.notifyItemRemoved(position);
                adapter.notifyDataSetChanged();
            }
        });

    }
}
