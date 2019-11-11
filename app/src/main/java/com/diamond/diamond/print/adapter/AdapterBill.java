package com.diamond.diamond.print.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.diamond.diamond.print.InforBillActivity;
import com.diamond.diamond.print.R;
import com.diamond.diamond.print.model.Bill;

import java.util.List;

public class AdapterBill extends  RecyclerView.Adapter<AdapterBill.ViewHolder> {
    private List<Bill> lsbill;
    private Context context;

    public AdapterBill(List<Bill> lsbill, Context context) {
        this.lsbill = lsbill;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterBill.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_bill,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBill.ViewHolder holder, int position) {
                holder.bill= lsbill.get(position);
                holder.txtNameBill.setText(holder.bill.getID());
                holder.cardView.setOnClickListener(new View. OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(context, InforBillActivity.class);
                        intent.putExtra("name",holder.bill.getID());
                        context.startActivity(intent);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return lsbill.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameBill;
        CardView cardView;
        Bill bill;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameBill= itemView.findViewById(R.id.txtnamebill);
            cardView= itemView.findViewById(R.id.cardview);
        }
    }
}
