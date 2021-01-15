package com.example.transferee.helpers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewEmptySupport extends RecyclerView {
    private View EmptyView;

    private AdapterDataObserver emptyObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            Adapter<?> adapter = getAdapter();
            if (adapter != null && EmptyView != null) {
                boolean emptyViewVisible = getAdapter().getItemCount() == 0;
                EmptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE);
                RecyclerViewEmptySupport.this.setVisibility(emptyViewVisible ? GONE : VISIBLE);
            }
        }
    };

    public RecyclerViewEmptySupport(@NonNull Context context) {
        super(context);
    }

    public RecyclerViewEmptySupport(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerViewEmptySupport(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        super.setAdapter(adapter);

        if (adapter != null)
            adapter.registerAdapterDataObserver(emptyObserver);
        emptyObserver.onChanged();
    }

    public void setEmptyView(View emptyView) {
        EmptyView = emptyView;
    }
}
