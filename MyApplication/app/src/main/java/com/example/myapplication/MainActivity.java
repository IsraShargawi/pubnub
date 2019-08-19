package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
public class MainActivity extends AppCompatActivity {
    public static PubNub pubnub; // PubNub instance
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPubnub();
        createChannel();
    }
    // Creates PubNub instance with your PubNub credentials. https://admin.pubnub.com/
    // This instance will be used when we need to create connection to PubNub.
    private void initPubnub() {
        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setPublishKey("pub-c-46db3e86-c17d-4608-8b1d-64c037ebcc25");
        pnConfiguration.setSubscribeKey("sub-c-152870a6-c262-11e9-a997-56cbbf38a03a");
        pnConfiguration.setSecure(true);
        pubnub = new PubNub(pnConfiguration);
    }
    // Creates notification channel.
    private void createChannel() {
        // Notification channel should only be created for devices running Android API level 26+.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel chan1 = new NotificationChannel(
                    "default",
                    "default",
                    NotificationManager.IMPORTANCE_NONE);
            chan1.setLightColor(Color.TRANSPARENT);
            chan1.setLockscreenVisibility(Notification.VISIBILITY_SECRET);
            notificationManager.createNotificationChannel(chan1);
        }
    }
}