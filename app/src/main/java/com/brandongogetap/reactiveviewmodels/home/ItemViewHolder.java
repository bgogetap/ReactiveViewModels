package com.brandongogetap.reactiveviewmodels.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.brandongogetap.reactiveviewmodels.R;
import com.brandongogetap.reactiveviewmodels.database.ListItem;
import com.brandongogetap.scoper.Scoper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class ItemViewHolder extends RecyclerView.ViewHolder {

    @Inject ItemClickedListener listener;

    @BindView(R.id.tv_title) TextView titleTextView;
    @BindView(R.id.tv_message) TextView messageTextView;
    @BindView(R.id.view_color) View colorView;

    private ListItem item;

    ItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        Scoper.<HomeComponent>getComponent(itemView.getContext()).inject(this);
        itemView.setOnClickListener(v -> listener.itemClicked(item));
    }

    void bind(ListItem item) {
        this.item = item;
        colorView.setBackgroundColor(item.color());
        titleTextView.setText(item.title());
        messageTextView.setText(item.message());
    }
}
