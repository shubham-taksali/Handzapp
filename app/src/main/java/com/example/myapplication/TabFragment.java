package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class TabFragment extends Fragment implements RecyclerViewAdapter.ItemListener {

    int position;
    RecyclerView recyclerView;
    ArrayList arrayList;

    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        TabFragment tabFragment = new TabFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("pos");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);

        if(position == 0) {
            arrayList = new ArrayList();
            arrayList.add(new DataModel(R.drawable.image_001,
                    "Douglas Roof",
                    "3.8",
                    getActivity().getResources().getString(
                            R.string.string_task_numbers, 298),
                    getActivity().getResources().getColor(R.color.colorRatingRibbonBlue)));
            arrayList.add(new DataModel(R.drawable.image_002,
                    "Douglas Roof",
                    "3.8",
                    getActivity().getResources().getString(
                            R.string.string_task_numbers, 298),
                    getActivity().getResources().getColor(R.color.colorRatingRibbonBlue)));
            arrayList.add(new DataModel(R.drawable.image_003,
                    "Douglas Roof",
                    "3.8",
                    getActivity().getResources().getString(
                            R.string.string_task_numbers, 298),
                    getActivity().getResources().getColor(R.color.colorRatingRibbonBlue)));
        } else if (position == 1){
            arrayList = new ArrayList();
            arrayList.add(new DataModel(R.drawable.image_001,
                    "Douglas Roof",
                    "3.8",
                    getActivity().getResources().getString(
                            R.string.string_task_numbers, 298),
                    getActivity().getResources().getColor(R.color.colorRatingRibbonGreen)));
            arrayList.add(new DataModel(R.drawable.image_002,
                    "Douglas Roof",
                    "3.8",
                    getActivity().getResources().getString(
                            R.string.string_task_numbers, 298),
                    getActivity().getResources().getColor(R.color.colorRatingRibbonGreen)));
            arrayList.add(new DataModel(R.drawable.image_003,
                    "Douglas Roof",
                    "3.8",
                    getActivity().getResources().getString(
                            R.string.string_task_numbers, 298),
                    getActivity().getResources().getColor(R.color.colorRatingRibbonGreen)));
        } else if (position == 2){
            arrayList = new ArrayList();
            arrayList.add(new DataModel(R.drawable.image_001,
                    "Douglas Roof",
                    "3.8",
                    getActivity().getResources().getString(
                            R.string.string_task_numbers, 298),
                    getActivity().getResources().getColor(R.color.colorRatingRibbonRed)));
            arrayList.add(new DataModel(R.drawable.image_002,
                    "Douglas Roof",
                    "3.8",
                    getActivity().getResources().getString(
                            R.string.string_task_numbers, 298),
                    getActivity().getResources().getColor(R.color.colorRatingRibbonRed)));
            arrayList.add(new DataModel(R.drawable.image_003,
                    "Douglas Roof",
                    "3.8",
                    getActivity().getResources().getString(
                            R.string.string_task_numbers, 298),
                    getActivity().getResources().getColor(R.color.colorRatingRibbonRed)));
        }

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(),
                arrayList, this);
        recyclerView.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(getActivity(),
                3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

    }

    @Override
    public void onItemClick(DataModel item) {
        //Toast.makeText(getActivity(), item.getPersonName() + " is clicked", Toast.LENGTH_SHORT).show();
    }
}