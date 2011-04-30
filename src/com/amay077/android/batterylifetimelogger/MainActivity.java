package com.amay077.android.batterylifetimelogger;

import com.amay077.android.logging.Log;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;

/**
 * 
 * @author amay077
 * @see http://www.adakoda.com/android/000140.html
 *
 */
public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        IntentFilter filter = new IntentFilter();
        
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mBroadcastReceiver, filter);
}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        
        unregisterReceiver(mBroadcastReceiver);
    }
    
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                int status = intent.getIntExtra("status", 0);
                int health = intent.getIntExtra("health", 0);
                boolean present = intent.getBooleanExtra("present", false);
                int level = intent.getIntExtra("level", 0);
                int scale = intent.getIntExtra("scale", 0);
                int icon_small = intent.getIntExtra("icon-small", 0);
                int plugged = intent.getIntExtra("plugged", 0);
                int voltage = intent.getIntExtra("voltage", 0);
                int temperature = intent.getIntExtra("temperature", 0);
                String technology = intent.getStringExtra("technology");
                
                String statusString = "";
                
                switch (status) {
                case BatteryManager.BATTERY_STATUS_UNKNOWN:
                    statusString = "unknown";
                    break;
                case BatteryManager.BATTERY_STATUS_CHARGING:
                    statusString = "charging";
                    break;
                case BatteryManager.BATTERY_STATUS_DISCHARGING:
                    statusString = "discharging";
                    break;
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    statusString = "not charging";
                    break;
                case BatteryManager.BATTERY_STATUS_FULL:
                    statusString = "full";
                    break;
                }
                
                String healthString = "";
                
                switch (health) {
                case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                    healthString = "unknown";
                    break;
                case BatteryManager.BATTERY_HEALTH_GOOD:
                    healthString = "good";
                    break;
                case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                    healthString = "overheat";
                    break;
                case BatteryManager.BATTERY_HEALTH_DEAD:
                    healthString = "dead";
                    break;
                case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                    healthString = "voltage";
                    break;
                case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                    healthString = "unspecified failure";
                    break;
                }
                
                String acString = "";
                
                switch (plugged) {
                case BatteryManager.BATTERY_PLUGGED_AC:
                    acString = "plugged ac";
                    break;
                case BatteryManager.BATTERY_PLUGGED_USB:
                    acString = "plugged usb";
                    break;
                }
                                
                Log.i("status", statusString);
                Log.i("health", healthString);
                Log.i("present", String.valueOf(present));
                Log.i("level", String.valueOf(level));
                Log.i("scale", String.valueOf(scale));
                Log.i("icon_small", String.valueOf(icon_small));
                Log.i("plugged", acString);
                Log.i("voltage", String.valueOf(voltage));
                Log.i("temperature", String.valueOf(temperature));
                Log.i("technology", technology);
            }
        }
    };
}