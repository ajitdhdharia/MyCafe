package com.example.mycafe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ShowItemsAdapter extends RecyclerView.Adapter<ShowItemsAdapter.RVViewHolderClass> {
    private ArrayList<Model> modelArrayList;

    public ShowItemsAdapter(ArrayList<Model> modelArrayList) {
        this.modelArrayList = modelArrayList;

    }
    public RVViewHolderClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new RVViewHolderClass(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_list,viewGroup,false));
    }
    @Override
    public void onBindViewHolder(@NonNull RVViewHolderClass holder, int position) {
        Model objectModel =modelArrayList.get(position);

        holder.img.setImageBitmap(objectModel.getITEMIMAGE());
        holder.cost.setText(objectModel.getCOST());
        holder.itemname.setText(objectModel.getITEMNAME());
        holder.faltu.setText(String.valueOf(objectModel.getID()));

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public static class RVViewHolderClass extends RecyclerView.ViewHolder {
        TextView itemname,count,cost,faltu;
        Button remove,add;
        ImageView img ;

        public RVViewHolderClass(@NonNull View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.img);
            faltu=itemView.findViewById(R.id.faltu);
            cost=itemView.findViewById(R.id.cost);
            itemname=itemView.findViewById(R.id.itemname);




        }



    }

}
