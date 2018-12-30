package com.ariel.logi.logi;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<String> iNames;
    private ArrayList<String> iDates;
    private ArrayList<String> iDelID;
    private ArrayList<String> iCour;
    private ArrayList<String> iStat;
    protected static Context iContext;

    public RecyclerViewAdapter(Context iContext, ArrayList<String> dNames, ArrayList<String> dDates,ArrayList<String> dDelID, ArrayList<String> dCour, ArrayList<String> dStat) {
        this.iNames = dNames;
        this.iDates = dDates;
        this.iStat = dStat;
        this.iDelID = dDelID;
        this.iCour = dCour;
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
        holder.date.setText(iDates.get(position));
        holder.name.setText(iNames.get(position));
        holder.stat.setText(iStat.get(position));
        holder.id.setText(iDelID.get(position));
        holder.cour.setText(iCour.get(position));
    }

    @Override
    public int getItemCount() {
        return iNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView parentLayout;
        LinearLayout linl;
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
        }

        @Override
        public void onClick(View v) {

        }
    }
}