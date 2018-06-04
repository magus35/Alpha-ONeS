package edu.csueastbay.horizon.lucifer.ones.EventHolder;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
/* Class for obtaining the Name, location, age, and Pictures. Getter and setters that will be called
from inside the MainActivity class
        */
public class EveUtils {

    private static final String TAG = "EveUtils";

    public static List<Eve> loadEvents(Context context){
        try{
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            JSONArray array = new JSONArray(loadJSONFromAsset(context, "events.json"));
            List<Eve> eventList = new ArrayList<>();
            for(int i=0;i<array.length();i++){
                Eve eve = gson.fromJson(array.getString(i), Eve.class);
                eventList.add(eve);
            }
            return eventList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private static String loadJSONFromAsset(Context context, String jsonFileName) {
        String json = null;
        InputStream is=null;
        try {
            AssetManager manager = context.getAssets();
            Log.d(TAG,"path "+jsonFileName);
            is = manager.open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}

