package com.example.a24442.air_conditioner;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

import android.widget.RemoteViews;


import com.example.a24442.air_conditioner.Activity.VoiceDialogActivity;

/**
 * Implementation of App Widget functionality.
 */
public class VoiceAppWidget extends AppWidgetProvider {


    //接收广播事件。
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

    }


    //到达指定的更新时间或用户向桌面添加widget时候调用。
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context,appWidgetManager,appWidgetIds);
        for(int x = 0; x < appWidgetIds.length;x++){

            Intent intent = new Intent(context,VoiceDialogActivity.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,

                    intent, PendingIntent.FLAG_UPDATE_CURRENT);

            RemoteViews remote = new RemoteViews(context.getPackageName(),

                    R.layout.voice_app_widget);

            remote.setOnClickPendingIntent(R.id.voice, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetIds[x], remote);

        }
    }

    //当AppWidget实例第一次被创建时调用。
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);

        // Enter relevant functionality for when the first widget is created
    }

    //当AppWidget被删除时调用。
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);

    }

    //当最后一个AppWidget被删除时调用。
    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);

        // Enter relevant functionality for when the last widget is disabled
    }

}

