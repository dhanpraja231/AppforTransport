package com.example.flipr2transport.dealer;

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

public class DealerHomePageRVAdapter extends RecyclerView.Adapter<DealerHomePageRVAdapter.DealerHomePageViewHolder> {

    private ArrayList<DriverModelObject> dataList;
    private LayoutInflater mInflater;

    public DealerHomePageRVAdapter(Context context, ArrayList<DriverModelObject> dataList){
        this.mInflater = LayoutInflater.from(context);
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public DealerHomePageRVAdapter.DealerHomePageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.for_dealer_driver_data_viewholder_, parent, false);
        return new DealerHomePageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DealerHomePageViewHolder holder, int position) {
        holder.mobileText.setText(dataList.get(position).mobile);
        holder.nameText.setText(dataList.get(position).name);
        holder.transporterText.setText(dataList.get(position).transporterName);
        holder.truckNoText.setText(dataList.get(position).truckNo);
        holder.experienceText.setText(dataList.get(position).drivingExperience);
        holder.capacityText.setText(dataList.get(position).truckCapacity);
        holder.ageText.setText(dataList.get(position).age);

    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class DealerHomePageViewHolder extends RecyclerView.ViewHolder{
        TextView nameText;
        TextView transporterText;
        TextView ageText;
        TextView experienceText;
        TextView truckNoText;
        TextView capacityText;
        TextView mobileText;
        View itemview;
        AppCompatButton bookingButton;
        public DealerHomePageViewHolder(View itemView){
            super(itemView);
            this.itemview = itemView;
            nameText = itemView.findViewById(R.id.driver_holder_name);
            transporterText = itemView.findViewById(R.id.driver_holder_transporter);
            ageText =itemView.findViewById(R.id.driver_holder_age);;
            experienceText = itemView.findViewById(R.id.driver_holder_experience);;
            truckNoText =itemView.findViewById(R.id.driver_holder_truckno);
            capacityText =itemView.findViewById(R.id.driver_holder_capacity);
            mobileText = itemView.findViewById(R.id.driver_holder_mobile);

            itemView.findViewById(R.id.driver_holder_book_button).setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    System.out.println("button pressed for user "+ dataList.get(getAdapterPosition()).name);
                    //TODO:
                    //add driver-dealer pair to booking table
                    //ADD A ASYNC CONNECTOR MYSQL TO SEND DATA TO DB
                    //TABLE: booking- drivername and dealer name;
                }
            });

        }


    }

}
