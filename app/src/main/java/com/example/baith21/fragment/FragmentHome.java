package com.example.baith21.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baith21.R;
import com.example.baith21.UpdateDeleteActivity;
import com.example.baith21.adapter.RecycleViewAdapter;
import com.example.baith21.dal.SQLiteHeper;
import com.example.baith21.model.Item;

import java.util.List;

public class FragmentHome extends Fragment implements RecycleViewAdapter.ItemListener {

    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;
    private SQLiteHeper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleView);
        adapter = new RecycleViewAdapter();
        db = new SQLiteHeper(this.getContext());
//        Item i = new Item(1,"aaaa","aaaa","abcd","25/04/2023",true);
//        db.addItem(i);
        List<Item> list = db.getAll();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        Item item =adapter.getItem(position);
        Intent intent = new Intent(getActivity(),UpdateDeleteActivity.class);
        intent.putExtra("item",item);
        startActivity(intent);

    }

    @Override
    public void onResume() {
        super.onResume();
        List<Item> list = db.getAll();
        adapter.setList(list);
    }
}
