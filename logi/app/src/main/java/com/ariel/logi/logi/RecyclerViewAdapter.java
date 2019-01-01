package com.ariel.logi.logi;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ariel.DeliverySystem.Delivery;
import com.ariel.User.Courier;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<Delivery> mDelivery;
    protected static Context iContext;

    public RecyclerViewAdapter(Context iContext, ArrayList<Delivery> mDelivery) {
        this.mDelivery = mDelivery;
        this.iContext = iContext;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.date.setText(mDelivery.get(position).getDate());
        holder.name.setText(mDelivery.get(position).getProduct_id());
        holder.stat.setText(mDelivery.get(position).getStatus());
        holder.id.setText(mDelivery.get(position).getDelivery_id());
        holder.cour.setText(mDelivery.get(position).getCourier_email());
        holder.address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final Dialog d = new Dialog(RecyclerViewAdapter.this.iContext);
                d.setTitle("Set Address");
                d.setContentView(R.layout.textdialog);
                Button okBtn = (Button) d.findViewById(R.id.button1);
                Button cancelBtn = (Button) d.findViewById(R.id.button2);
                final EditText editText = (EditText)  d.findViewById(R.id.textPicker);
                okBtn.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        mDelivery.get(position).setAddress(editText.toString());
                        d.dismiss();
                    }
                });
                cancelBtn.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        d.dismiss();
                    }
                });
                d.show();
            }
        });

        holder.gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final Dialog f = new Dialog(RecyclerViewAdapter.this.iContext);
                f.setTitle("Set Address");
                f.setContentView(R.layout.finishdialog);
                Button okBtn = (Button) f.findViewById(R.id.button1);
                Button cancelBtn = (Button) f.findViewById(R.id.button2);
                okBtn.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        mDelivery.get(position).setStatus("delivered");
                        f.dismiss();
                    }
                });
                cancelBtn.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        f.dismiss();
                    }
                });
                f.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDelivery.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView parentLayout;
        LinearLayout linl;
        ImageButton call,address,gotit;
        private TextView name, date, id, cour, stat;
        public ViewHolder(View itemView){
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.recycler_delivery_product_name);
            date = (TextView) itemView.findViewById(R.id.recycler_delivery_date);
            id = (TextView) itemView.findViewById(R.id.recycler_delivery_id);
            cour = (TextView) itemView.findViewById(R.id.recycler_courier_name);
            stat = (TextView) itemView.findViewById(R.id.recycler_staus);
            parentLayout = (CardView)itemView.findViewById(R.id.recycler_cust_item);
            linl = (LinearLayout)itemView.findViewById(R.id.recycler_child_linear_layout);
            call = (ImageButton)itemView.findViewById(R.id.recycler_callCur_img);
            address = (ImageButton)itemView.findViewById(R.id.recycler_setA_img);
            gotit = (ImageButton)itemView.findViewById(R.id.recycler_fin_btn);
        }

        @Override
        public void onClick(View v) {

        }
    }
}