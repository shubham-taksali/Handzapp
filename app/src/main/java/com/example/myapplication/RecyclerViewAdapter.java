package com.example.myapplication;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<DataModel> dataModelArrayList;
    private Context context;
    private ItemListener itemListener;
    private LinearLayout ratingRibbon;
    private int ribbonColor;

    private SparseBooleanArray selectedItems;

    RecyclerViewAdapter(
            Context context, ArrayList<DataModel> values, int ribbonColor,
            ItemListener itemListener) {

        dataModelArrayList = values;
        this.context = context;
        this.itemListener = itemListener;
        this.ribbonColor = ribbonColor;
        this.selectedItems = new SparseBooleanArray();
    }

    @Override
    @NonNull
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(
                R.layout.grid_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.setData(dataModelArrayList.get(position));

        if (MyApplication.isInMultiSelectMode())
            viewHolder.selectorImage.setVisibility(View.VISIBLE);
        else
            viewHolder.selectorImage.setVisibility(View.GONE);

        viewHolder.itemView.setActivated(selectedItems.get(position, false));

        applyClickEvents(viewHolder, position);
    }

    private void applyClickEvents(ViewHolder holder, final int position) {
        holder.cardParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemListener.onItemClick(position);
            }
        });

        holder.cardParentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                itemListener.onItemLongClick(position);
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public void toggleSelection(int pos) {
        //currentSelectedIndex = pos;
        MyApplication.setIsInMultiSelectMode(true);
        if (selectedItems.get(pos, false)) {
            selectedItems.delete(pos);
        } else {
            selectedItems.put(pos, true);
        }
        notifyDataSetChanged();
    }

    public void clearSelections() {
        selectedItems.clear();
        MyApplication.setIsInMultiSelectMode(false);
        MyApplication.setSelectedItemCount(0);
        notifyDataSetChanged();
    }

    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items =
                new ArrayList<>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }

    public void removeData(int position) {
        dataModelArrayList.remove(position);
    }

    public interface ItemListener {
        void onItemClick(int position);

        void onItemLongClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnLongClickListener {

        TextView personName;
        TextView personRating;
        TextView personTasks;
        ImageView personImage;
        ImageView selectorImage;
        RelativeLayout cardParentLayout;
        DataModel dataModel;

        private ViewHolder(View view) {

            super(view);

            view.setOnLongClickListener(this);
            personName = view.findViewById(R.id.tv_person_name);
            personRating = view.findViewById(R.id.tv_rating);
            personTasks = view.findViewById(R.id.tv_tasks);
            personImage = view.findViewById(R.id.im_person_image);
            ratingRibbon = view.findViewById(R.id.rl_rating_ribbon);
            cardParentLayout = view.findViewById(R.id.rl_card_parent);
            selectorImage = view.findViewById(R.id.im_selection_unchecked);
        }

        private void setData(final DataModel item) {
            dataModel = item;

            personName.setText(dataModel.getName());
            personRating.setText(dataModel.getRating());
            personTasks.setText(dataModel.getTasks());
            personImage.setImageResource(
                    context.getResources().getIdentifier(dataModel.getImage(),
                            "drawable", context.getPackageName()));
            ratingRibbon.setBackgroundColor(ribbonColor);
        }

        @Override
        public boolean onLongClick(View view) {
            itemListener.onItemLongClick(getAdapterPosition());
            view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
            return true;
        }
    }
}