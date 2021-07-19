package com.example.Curricular_Practice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private ArrayList<Item> arrayList;
    private Context context;

    public CustomAdapter(ArrayList<Item> arrayList,Context context){
        this.arrayList = arrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder holder, int position) {
        holder.tv_curricular_name.setText(arrayList.get(position).getName());
        holder.tv_curricular_content.setText(String.valueOf(arrayList.get(position).getContent()));
    }

    @Override
    public int getItemCount() {
        return arrayList!=null?arrayList.size():0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView tv_curricular_name;
        TextView tv_curricular_content;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_curricular_name = itemView.findViewById(R.id.tv_curricular_name);
            this.tv_curricular_content = itemView.findViewById(R.id.tv_curricular_content);
        }
    }
}
