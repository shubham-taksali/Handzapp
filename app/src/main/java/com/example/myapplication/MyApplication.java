package com.example.myapplication;

import android.app.Application;

public class MyApplication extends Application {

    private static int selectedItemCount;
    private static boolean isInMultiSelectMode;

    public MyApplication() {
        selectedItemCount = 0;
        isInMultiSelectMode = false;
    }

    public static int getSelectedItemCount() {
        return selectedItemCount;
    }

    public static void setSelectedItemCount(int selectedItemCount) {
        MyApplication.selectedItemCount = selectedItemCount;
    }

    public static boolean isInMultiSelectMode() {
        return isInMultiSelectMode;
    }

    public static void setIsInMultiSelectMode(boolean isInMultiSelectMode) {
        MyApplication.isInMultiSelectMode = isInMultiSelectMode;
    }
}
