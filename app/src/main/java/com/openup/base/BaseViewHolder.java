package com.openup.base;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;


/**
 * Created by Niel on 18/07/17.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

	private T item;

	protected BaseViewHolder(ViewGroup parent, @LayoutRes int itemLayoutId) {
		super(LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false));
		ButterKnife.bind(this, itemView);
		itemView.setOnClickListener(this);
		itemView.setOnLongClickListener(this);
	}

	protected final void performBind(T item, int position) {
		this.item = item;
		onBind(item, position);
	}

	protected T getItem() {
		return item;
	}

	protected abstract void onBind(T item, int position);

	protected abstract void onClick(View view, T item);

	protected abstract boolean onLongClick(View view, T item);

	@Override public final void onClick(View view) {
		onClick(view, item);
	}

	@Override public final boolean onLongClick(View view) {
		return onLongClick(view, item);
	}

}