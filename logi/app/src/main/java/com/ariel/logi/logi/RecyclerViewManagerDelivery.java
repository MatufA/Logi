package com.ariel.logi.logi;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ariel.DeliverySystem.Delivery;

import java.util.ArrayList;

public class RecyclerViewManagerDelivery extends RecyclerView.Adapter<RecyclerViewManagerDelivery.ViewHolder> {
    private static final int REQUEST_CALL = 1;

    private ArrayList<Delivery> mDelivery;
    private Context context;

    public RecyclerViewManagerDelivery(Context context, ArrayList<Delivery> mDelivery) {
        this.mDelivery = mDelivery;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewManagerDelivery.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_manager_delivery, parent, false);
        return new RecyclerViewManagerDelivery.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewManagerDelivery.ViewHolder holder, final int position) {
        holder.courierName.setText(mDelivery.get(position).getCourier_email());
        holder.customerName.setText(mDelivery.get(position).getCustomer_email());
        holder.status.setText(mDelivery.get(position).getStatus());
        holder.deliveryId.setText(mDelivery.get(position).getDelivery_id());
        holder.deliveryDate.setText(mDelivery.get(position).getDate());
        holder.imgBtnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mDelivery.get(position).getCustomer_phone().isEmpty() && ContextCompat.checkSelfPermission(context,
                        android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions((Activity) context,new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                }else{
                    String dial = "tel:" + mDelivery.get(position).getCustomer_phone().trim();
                    context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDelivery.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView courierName;
        private TextView customerName;
        private TextView status;
        private TextView deliveryId;
        private TextView deliveryDate;
        CardView cardView;
        LinearLayout linearLayout;
        ImageButton imgBtnInfo;
        ImageButton imgBtnDial;

        public ViewHolder(View itemView) {
            super(itemView);
            courierName = (TextView) itemView.findViewById(R.id.recycler_delivery_courier_name);
            customerName = (TextView) itemView.findViewById(R.id.recycler_customer_name);
            status = (TextView) itemView.findViewById(R.id.recycler_staus);
            deliveryId = (TextView) itemView.findViewById(R.id.recycler_delivery_id);
            deliveryDate = (TextView) itemView.findViewById(R.id.recycler_delivery_date);
            cardView = (CardView) itemView.findViewById(R.id.recycler_parent_card_view);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.recycler_child_linear_layout);
            imgBtnInfo = (ImageButton) itemView.findViewById(R.id.recycler_info_imag);
            imgBtnDial = (ImageButton) itemView.findViewById(R.id.recycler_call_imag);
        }
    }
}
