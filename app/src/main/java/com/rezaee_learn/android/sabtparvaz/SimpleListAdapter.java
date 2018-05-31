package com.rezaee_learn.android.sabtparvaz;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by K2 on 4/28/2018.
 */
public class SimpleListAdapter extends RecyclerView.Adapter<SimpleListAdapter.SimpleItemViewHolder> {
    private Context context;
    private List<TravelModel> list;
    private Listener listener;

    public SimpleListAdapter(final Context context, List<TravelModel> list  , SimpleListAdapter.Listener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }
    @Override
    public SimpleItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_list, parent, false);
        return new SimpleItemViewHolder(view);
    }


    public int test(int a){
        return a;
    }



    @Override
    public void onBindViewHolder(final SimpleItemViewHolder holder, final int position) {

        Log.e("position" , String.valueOf(position));
        Log.e("size" , String.valueOf(list.size()));
        final TravelModel model = list.get(position);
        holder.bindEmail(list.get(position));
        holder.Holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPresser(list.get(position).cod);

            }
        });

        if (position == list.size() - 2)
        {
            listener.onLoadMore();
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SimpleItemViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout Holder;
        private TextView companyname, cod, begn, stop, date_f, time_f, price;

        public SimpleItemViewHolder(View itemView) {
            super(itemView);
            Holder = (LinearLayout) itemView.findViewById(R.id.TravelHolderItem);
            companyname = (TextView) itemView.findViewById(R.id.etx_item_list_companyname);
            cod = (TextView) itemView.findViewById(R.id.etx_item_list_cod);
            begn = (TextView) itemView.findViewById(R.id.etx_item_list_start);
            stop = (TextView) itemView.findViewById(R.id.etx_item_list_stop);
            date_f = (TextView) itemView.findViewById(R.id.etx_item_list_date);
            time_f = (TextView) itemView.findViewById(R.id.etx_item_list_time);
            price = (TextView) itemView.findViewById(R.id.etx_item_list_price);
        }

        public void bindEmail(TravelModel model) {
            companyname.setText(model.CompanyName);
            cod.setText(model.cod);
            begn.setText(model.begn);
            stop.setText(model.stop);
            date_f.setText(model.date_f);
            time_f.setText(model.time_f);
            price.setText(model.price);
        }

    }

    interface Listener {

        public void onPresser(String cod);

        public void onHold();

        public void onLoadMore();
    }
}
