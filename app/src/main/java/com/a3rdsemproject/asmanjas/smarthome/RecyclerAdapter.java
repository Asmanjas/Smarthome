package com.a3rdsemproject.asmanjas.smarthome;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.florent37.expansionpanel.ExpansionLayout;
import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {

    private final List<Object> list = new ArrayList<>();
    Context ctx;
    String places[];



    private final ExpansionLayoutCollection expansionsCollection = new ExpansionLayoutCollection();
    public RecyclerAdapter(Context context,String ss[]){

        ctx = context;
        places = ss;
        expansionsCollection.openOnlyOne(true);
    }


    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return RecyclerHolder.buildFor(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.RecyclerHolder holder, int position) {
        holder.bind(list.get(position));
        holder.textView.setText(places[position]);
        expansionsCollection.add(holder.getExpansionLayout());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void setItems(List<Object> items) {
        this.list.addAll(items);
        notifyDataSetChanged();
    }

    public static class RecyclerHolder extends RecyclerView.ViewHolder
    {
        TextView textView;
        private static final int LAYOUT = R.layout.recycler_card;

        @BindView(R.id.expansionLayout)
        ExpansionLayout expansionLayout;

        public static RecyclerHolder buildFor(ViewGroup viewGroup){
            return new RecyclerHolder(LayoutInflater.from(viewGroup.getContext()).inflate(LAYOUT, viewGroup, false));
        }

        public RecyclerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            textView = (TextView) itemView.findViewById(R.id.headerText);
        }

        public void bind(Object object){
            expansionLayout.collapse(false);
        }

        public ExpansionLayout getExpansionLayout() {
            return expansionLayout;
        }
    }
}


