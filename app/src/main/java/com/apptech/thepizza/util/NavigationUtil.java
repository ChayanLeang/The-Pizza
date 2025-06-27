package com.apptech.thepizza.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class NavigationUtil {

    public static void navigateTo(Context context, Class<?> targetActivity, Bundle extras){
        Intent intent = new Intent(context,targetActivity);
        if(extras != null){
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }
}
