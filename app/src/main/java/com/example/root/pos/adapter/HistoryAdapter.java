package com.example.root.pos.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.pos.R;
import com.example.root.pos.model.SalesHistory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {



    private ArrayList<SalesHistory> salesHistoryList;

    public HistoryAdapter(ArrayList<SalesHistory> salesHistoryList) {
        this.salesHistoryList = salesHistoryList;
    }

    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_sales_history_item, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        ViewHolder vh =  viewHolder;


        vh.txt_email.setText("imran1995bd@gmail.com");
        vh.txt_productName.setText( "N/A");
        vh.paymentID.setText("N/A");
        vh.txt_amount.setText("455"+" TK");
        vh.txt_product_quantity.setText( "N/A");
        vh.txt_phone_number.setText("+8801764310157");
        vh.txt_pyment_charge.setText(".20");
        vh.txt_total_amount.setText("1000"+" TK");
        vh.txt_payment_method.setText("Cash");
        vh.txt_payment_date.setText("22-01-2019");
    }



    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 10 ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.card_content)
        CardView card_content;

        @BindView(R.id.email)
        TextView txt_email;

        @BindView(R.id.productName)
        TextView txt_productName;

        @BindView(R.id.paymet_id)
        TextView paymentID;

        @BindView(R.id.txt_amount) TextView txt_amount;
        @BindView(R.id.txt_product_quantity) TextView txt_product_quantity;
        @BindView(R.id.txt_phone_number) TextView txt_phone_number;
        @BindView(R.id.txt_payment_charge) TextView txt_pyment_charge;
        @BindView(R.id.txt_total_amount) TextView txt_total_amount;
        @BindView(R.id.txt_payment_method) TextView txt_payment_method;
        @BindView(R.id.txt_sales_date) TextView txt_payment_date;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            card_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

        }
    }

}
