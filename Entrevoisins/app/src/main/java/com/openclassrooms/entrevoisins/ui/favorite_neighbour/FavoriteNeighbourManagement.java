package com.openclassrooms.entrevoisins.ui.favorite_neighbour;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.lang.reflect.Type;
import java.util.ArrayList;

public abstract class FavoriteNeighbourManagement {
//TODO : gérer le bouton poubelle depuis la partie favoris

    private static ArrayList<Neighbour> mFavoriteNeighbourList;
    private static NeighbourApiService mApiService = DI.getNeighbourApiService();

    private static final String SHARED = "sharedPreferences";
    public static final String FAVORITE_LIST = "favoriteList";

    private static final String TAG = "DEBUGKEY FNM";


//    public static ArrayList<Neighbour> getFavoriteNeighbourList() {
//        Log.d(TAG, "getFavoriteNeighbourList: ok");
//        return mFavoriteNeighbourList = mApiService.getFavoriteNeighbourList();
//    }

//    public static void addOrRemoveToFavoriteList(Neighbour neighbour, Context context){
//        Log.d(TAG, "addOrRemoveToFavoriteList: called");
//        mApiService.addOrRemoveFavoriteNeighbour(neighbour);
//        if(mFavoriteNeighbourList.contains(neighbour)==false){
//            mFavoriteNeighbourList.add(neighbour);
//            Log.d(TAG, "addOrRemoveToFavoriteList: add");
//
//        }else{
//            mFavoriteNeighbourList.remove(neighbour);
//            Log.d(TAG, "addOrRemoveToFavoriteList: remove");
//        }
//        saveFavoriteData(context);
//        Log.d(TAG, "addOrRemoveToFavoriteList: saved");
//    }

//    public static void saveFavoriteData(Context context) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(mFavoriteNeighbourList);
//        editor.putString(FAVORITE_LIST, json);
//        editor.apply();
//        Log.d(TAG, "saveFavoriteData: called");
//    }
//
//    public static void loadFavoriteData(Context context){
//        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED,Context.MODE_PRIVATE);
//        Gson gson = new Gson();
//        String json = sharedPreferences.getString(FAVORITE_LIST,null);
//        Type type = new TypeToken<ArrayList<Neighbour>>(){}.getType();
//        mFavoriteNeighbourList = gson.fromJson(json,type);
//
//        if(mFavoriteNeighbourList == null){
//            mFavoriteNeighbourList = new ArrayList<>();
//        }
//        Log.d(TAG, "loadFavoriteData: called");
//
//    }

//    public static boolean starStatue(Neighbour neighbour){
//        getFavoriteNeighbourList();
//        if (mFavoriteNeighbourList.contains(neighbour)){
//            return true;
//        }else{
//            return false;
//        }
//    }
}
