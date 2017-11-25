package com.example.eddie.usingservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.InterruptedIOException;

public class MyService extends Service {
    public MyService() {
    }

    public void onCreate(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(5000);
                    Intent intent = new Intent(MyService.this, Main2Activity.class);
                    MyService.this.startActivity(intent);
                }catch(InterruptedException e){
                    e.printStackTrace();;
                }
            }
        }).start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startID){
        super.onStartCommand(intent, flags, startID);
        return START_STICKY;
    }

}
