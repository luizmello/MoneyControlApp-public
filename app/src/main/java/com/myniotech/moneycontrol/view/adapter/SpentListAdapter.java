package com.myniotech.moneycontrol.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.myniotech.moneycontrol.R;
import com.myniotech.moneycontrol.app.Utils;
import com.myniotech.moneycontrol.model.spent.Spent;
import com.myniotech.moneycontrol.model.spent.SpentType;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by luiz on 22/04/17.
 */

public class SpentListAdapter extends RecyclerView.Adapter<SpentListAdapter.SpentViewHolder> {


    List<Spent> spentList;

    SpentAdapterListener spentAdapterListener;

    public SpentListAdapter(List<Spent> spentList, SpentAdapterListener spentAdapterListener) {
        this.spentList = spentList;
        this.spentAdapterListener = spentAdapterListener;

    }

    @Override
    public SpentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new SpentViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.spent_list_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(SpentViewHolder holder, int position) {

        Spent spent = spentList.get(position);

        holder.spentValue.setText(Utils.longToCurrency(spent.getValue().longValue()));
        holder.spentDate.setText(spent.getCreated_at().toString());

        if (spent.getSpentType() == SpentType.CREDIT_CARD) {
            holder.spentType.setImageResource(R.drawable.cc_checked);
        } else {
            holder.spentType.setImageResource(R.drawable.money_checked);
        }

        holder.deleteSpent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spentAdapterListener.onDeleteSpent(spent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return spentList.size();
    }

    class SpentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.spentType)
        ImageView spentType;
        @BindView(R.id.spentValue)
        TextView spentValue;
        @BindView(R.id.spentDate)
        TextView spentDate;
        @BindView(R.id.deleteSpent)
        ImageButton deleteSpent;


        public SpentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface SpentAdapterListener {

        void onDeleteSpent(Spent spent);

    }


}
