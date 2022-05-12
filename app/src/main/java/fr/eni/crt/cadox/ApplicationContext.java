package fr.eni.crt.cadox;

import android.app.Application;
import android.content.Context;

public class ApplicationContext extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        context = getApplicationContext();
    }

    private static Context context;
    public static Context getContext(){
        return context;
    }

}
