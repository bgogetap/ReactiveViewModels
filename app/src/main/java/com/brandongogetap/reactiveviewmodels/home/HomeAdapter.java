package com.brandongogetap.reactiveviewmodels.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brandongogetap.reactiveviewmodels.R;
import com.brandongogetap.reactiveviewmodels.database.ListItem;

import java.util.List;

final class HomeAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private final List<ListItem> data;

    HomeAdapter(@NonNull List<ListItem> data) {
        this.data = data;
    }

    @Override public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_list_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override public int getItemCount() {
        return data.size();
    }

}
