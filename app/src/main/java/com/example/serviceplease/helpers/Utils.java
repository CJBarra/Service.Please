package com.example.serviceplease.helpers;

import android.app.AlertDialog;
import android.content.Context;

import com.example.serviceplease.api.ApiClientInstance;
import com.example.serviceplease.api.DrinkServiceApi;
import com.example.serviceplease.api.FoodServiceApi;

public class Utils {
//    public static DrinkServiceApi getDrinkApi() {
//        return ApiClientInstance.getDrinkClient().create(DrinkServiceApi.class);
//    }
    public static FoodServiceApi getFoodApi() {
        return ApiClientInstance.getFoodClient().create(FoodServiceApi.class);
    }

    public static AlertDialog showDialogMessage(Context context, String title, String message) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .show();

        if (dialog.isShowing()) {
            dialog.cancel();
        }
        return dialog;
    }

}
