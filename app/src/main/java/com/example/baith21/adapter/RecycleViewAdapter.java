package com.example.baith21.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baith21.R;
import com.example.baith21.model.Item;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder> {

    private List<Item> list;

//    public RecycleViewAdapter(List<Item> list) {
//        this.list = list;
//    }

    private ItemListener itemListener;

    public void setList(List<Item> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public Item getItem(int position){
        return list.get(position);
    }

    @NonNull
    @Override
    public RecycleViewAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.HomeViewHolder holder, int position) {
        Item items = list.get(position);
        holder.title.setText(items.getTitle());
        holder.category.setText(items.getCategory());
        holder.Nd.setText(items.getNd());
        holder.date.setText(items.getDate());
        if(items.isCt()){
            holder.ct.setText("Làm việc nhóm");
        }else {
            holder.ct.setText("Làm một mình");
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title,category,Nd,date,ct;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            category = itemView.findViewById(R.id.tvCategory);
            Nd = itemView.findViewById(R.id.tvNd);
            date = itemView.findViewById(R.id.tvDate);
            ct = itemView.findViewById(R.id.tvCt);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            Log.d("test123", "onClick: ");
            if(itemListener != null){
                itemListener.onItemClick(view,getAdapterPosition());
            }
        }
        public void test(){
        }
    }
    public interface ItemListener{
        void onItemClick(View view,int position);
    }
}
