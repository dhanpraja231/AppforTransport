package com.example.flipr2transport.driver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flipr2transport.R;

import java.util.ArrayList;

public class DriverHomePageRVAdapter extends RecyclerView.Adapter<DriverHomePageRVAdapter.DriverHomePageViewHolder> {

    private ArrayList<DealerModelObject> dataList;
    private LayoutInflater mInflater;

    public DriverHomePageRVAdapter(Context context, ArrayList<DealerModelObject> dataList){
        this.mInflater = LayoutInflater.from(context);
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public DriverHomePageRVAdapter.DriverHomePageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.for_driver_dealer_data_viewholder, parent, false);
        return new DriverHomePageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverHomePageViewHolder holder, int position) {
        System.out.println("holder"+dataList.get(position).mobile);
        holder.mobileText.setText(dataList.get(position).mobile);
        holder.nameText.setText(dataList.get(position).name);
        holder.materialText.setText(dataList.get(position).material);
        holder.weightOfMaterialText.setText(dataList.get(position).weightOfMaterial);
        holder.quantityText.setText(dataList.get(position).quantity);
        holder.cityText.setText(dataList.get(position).city);
        holder.stateText.setText(dataList.get(position).state);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class DriverHomePageViewHolder extends RecyclerView.ViewHolder{
        TextView nameText;
        TextView materialText;
        TextView mobileText;
        TextView weightOfMaterialText;
        TextView quantityText;
        TextView cityText;
        TextView stateText;

        public DriverHomePageViewHolder(View itemView){
            super(itemView);
            nameText = itemView.findViewById(R.id.dealer_holder_name);
            mobileText = itemView.findViewById(R.id.dealer_holder_mobile);
            materialText = itemView.findViewById(R.id.dealer_holder_material);
            weightOfMaterialText = itemView.findViewById(R.id.dealer_holder_weight);
            quantityText = itemView.findViewById(R.id.dealer_holder_quantity);
            cityText = itemView.findViewById(R.id.dealer_holder_city);
            stateText = itemView.findViewById(R.id.dealer_holder_state);
//            itemView.findViewById(R.id.driver_holder_book_button).setOnClickListener(new View.OnClickListener(){
//                public void onClick(View v){
//                    System.out.println("button pressed for user "+ dataList.get(getAdapterPosition()).name);
//                    //TODO:
//                    //set status of booking pair to true
//                }
//            });
        }


    }

}
