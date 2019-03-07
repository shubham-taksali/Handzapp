package com.example.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<DataModel> mValues;
    private Context mContext;
    private ItemListener mListener;
    private RelativeLayout ratingRibbon;

    public RecyclerViewAdapter(Context context, ArrayList<DataModel> values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener=itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView personName;
        private TextView personRating;
        private TextView personTasks;
        private ImageView personImage;
        private DataModel item;

        private ViewHolder(View view) {

            super(view);

            view.setOnClickListener(this);
            personName = view.findViewById(R.id.tv_person_name);
            personRating = view.findViewById(R.id.tv_rating);
            personTasks = view.findViewById(R.id.tv_tasks);
            personImage = view.findViewById(R.id.person_image);
            ratingRibbon = view.findViewById(R.id.rl_rating_ribbon);

        }

        private void setData(DataModel item) {
            this.item = item;

            personName.setText(item.getPersonName());
            personRating.setText(item.getPersonRating());
            personTasks.setText(item.getPersonTask());
            personImage.setImageResource(item.getDrawable());
            ratingRibbon.setBackgroundColor(item.getRibbonColor());
        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item);
            }
        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(
                R.layout.grid_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.setData(mValues.get(position));

    }

    @Override
    public int getItemCount() {

        return mValues.size();
    }

    public interface ItemListener {
        void onItemClick(DataModel item);
    }
}