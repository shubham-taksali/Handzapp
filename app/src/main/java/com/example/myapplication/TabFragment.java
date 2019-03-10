package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TabFragment extends Fragment implements RecyclerViewAdapter.ItemListener {

    private int position;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<DataModel> arrayListFollowers;
    private ArrayList<DataModel> arrayListFavorites;
    private ArrayList<DataModel> arrayListBlocked;
    private Gson gson;
    private Type collectionType;
    private CoordinatorLayout coordinatorLayout;

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
        setHasOptionsMenu(true);
        position = getArguments().getInt("pos");
        collectionType = new TypeToken<Collection<DataModel>>() {
        }.getType();
        gson = new Gson();
        arrayListFollowers = gson.fromJson(loadJSONFromAsset(), collectionType);
        arrayListFavorites = gson.fromJson(loadJSONFromAsset(), collectionType);
        arrayListBlocked = gson.fromJson(loadJSONFromAsset(), collectionType);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int ribbonColor = 0;

        recyclerView = view.findViewById(R.id.recyclerView);
        coordinatorLayout = view.findViewById(R.id.crl_parent_layout);

        if (position == 0) {
            ribbonColor = getResources().getColor(R.color.colorRatingRibbonBlue);
            recyclerViewAdapter = new RecyclerViewAdapter(getActivity(),
                    arrayListFollowers, ribbonColor, this);
        } else if (position == 1) {
            ribbonColor = getResources().getColor(R.color.colorRatingRibbonGreen);
            recyclerViewAdapter = new RecyclerViewAdapter(getActivity(),
                    arrayListFavorites, ribbonColor, this);
        } else if (position == 2) {
            ribbonColor = getResources().getColor(R.color.colorRatingRibbonRed);
            recyclerViewAdapter = new RecyclerViewAdapter(getActivity(),
                    arrayListBlocked, ribbonColor, this);
        }

        recyclerView.setAdapter(recyclerViewAdapter);
        GridLayoutManager manager = new GridLayoutManager(getActivity(),
                3, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onItemClick(int position) {
        if (MyApplication.isInMultiSelectMode())
            enableMultiSelectMode(position);
        else {
            // Can be used for implement normal Click functionality
        }
    }

    @Override
    public void onItemLongClick(int position) {
        enableMultiSelectMode(position);
    }

    private void enableMultiSelectMode(int position) {
        recyclerViewAdapter.toggleSelection(position);
        MyApplication.setSelectedItemCount(recyclerViewAdapter.getSelectedItemCount());
        getActivity().invalidateOptionsMenu();
        if (MyApplication.isInMultiSelectMode() && MyApplication.getSelectedItemCount() > 0)
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(
                    R.string.string_selected_items, MyApplication.getSelectedItemCount()));

    }

    private void deleteItem() {
        List<Integer> selectedItemPositions =
                recyclerViewAdapter.getSelectedItems();
        for (int i = selectedItemPositions.size() - 1; i >= 0; i--) {
            recyclerViewAdapter.removeData(selectedItemPositions.get(i));
        }
        recyclerViewAdapter.notifyDataSetChanged();
        showSnackbar();
        returnToNormalMode();
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream inputStream = getActivity().getAssets().open("data.json");
            int size = inputStream.available();
            byte[] bytes = new byte[size];
            inputStream.read(bytes);
            inputStream.close();
            json = new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    public void returnToNormalMode() {
        recyclerViewAdapter.clearSelections();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.app_name);
        getActivity().invalidateOptionsMenu();
    }

    private void showSnackbar() {
        Snackbar snackbar = null;
        if (position == 0)
            snackbar = Snackbar.make(coordinatorLayout,
                    getString(R.string.sb_removed, MyApplication.getSelectedItemCount()),
                    Snackbar.LENGTH_LONG);
        if (position == 1)
            snackbar = Snackbar.make(coordinatorLayout,
                    getString(R.string.sb_unfavorited, MyApplication.getSelectedItemCount()),
                    Snackbar.LENGTH_LONG);
        if (position == 2)
            snackbar = Snackbar.make(coordinatorLayout,
                    getString(R.string.sb_unblocked, MyApplication.getSelectedItemCount()),
                    Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_help:
                return false;
            case R.id.action_remove:
                deleteItem();
                return true;
            case android.R.id.home:
                returnToNormalMode();
                return true;
            default:
                break;
        }
        return false;
    }
}