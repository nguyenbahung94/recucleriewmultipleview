package com.example.nbhung.demorecyclerviewmultipleviewtypes.Adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nbhung.demorecyclerviewmultipleviewtypes.R;
import com.example.nbhung.demorecyclerviewmultipleviewtypes.model.item;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;

import java.util.List;

/**
 * Created by nbhung on 10/11/2017.
 */
class myViewHolderWithChild extends RecyclerView.ViewHolder {
    public TextView textView, textViewChild;
    public RelativeLayout button;
    public ExpandableLinearLayout expandableLinearLayout;

    public myViewHolderWithChild(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.textview);
        textViewChild = (TextView) itemView.findViewById(R.id.textviewChild);
        button = (RelativeLayout) itemView.findViewById(R.id.button);
        expandableLinearLayout = (ExpandableLinearLayout) itemView.findViewById(R.id.expandableLayout);
    }
}

class myViewHolderWithOutChild extends RecyclerView.ViewHolder {
    public TextView textView;

    public myViewHolderWithOutChild(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textview);
    }
}

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<item> items;
    private Context context;
    private SparseBooleanArray expandState = new SparseBooleanArray();

    public MyAdapter(List<item> items) {
        this.items = items;
        for (int i = 0; i < items.size(); i++) {
            expandState.append(i, false);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position).isExpandable()) {
            return 1;
        } else return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        if (viewType == 0) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.layout_without_child, parent, false);
            return new myViewHolderWithOutChild(view);
        } else {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.layout_with_child, parent, false);
            return new myViewHolderWithChild(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case 0:
                myViewHolderWithOutChild viewHolderWithOutChild = (myViewHolderWithOutChild) holder;
                item iitem = items.get(position);
                viewHolderWithOutChild.setIsRecyclable(false);
                viewHolderWithOutChild.textView.setText(iitem.getText());
                break;
            case 1:
                final myViewHolderWithChild viewHolderWithChild = (myViewHolderWithChild) holder;
                item iitemss = items.get(position);
                viewHolderWithChild.setIsRecyclable(false);
                viewHolderWithChild.textView.setText(iitemss.getText());
                viewHolderWithChild.expandableLinearLayout.setInRecyclerView(true);
                viewHolderWithChild.expandableLinearLayout.setExpanded(expandState.get(position));
                viewHolderWithChild.expandableLinearLayout.setListener(new ExpandableLayoutListenerAdapter() {
                    @Override
                    public void onPreOpen() {
                        changeRotate(viewHolderWithChild.button, 0f, 100f).start();
                        expandState.put(position, true);
                    }

                    @Override
                    public void onPreClose() {
                        changeRotate(viewHolderWithChild.button, 100f, 0f).start();
                        expandState.put(position, false);
                    }

                });
                viewHolderWithChild.button.setRotation(expandState.get(position) ? 100f : 0f);
                viewHolderWithChild.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewHolderWithChild.expandableLinearLayout.toggle();
                    }
                });
                viewHolderWithChild.textViewChild.setText(items.get(position).getSubText());
                break;
        }
    }

    private ObjectAnimator changeRotate(RelativeLayout button, float to, float from) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(button, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
