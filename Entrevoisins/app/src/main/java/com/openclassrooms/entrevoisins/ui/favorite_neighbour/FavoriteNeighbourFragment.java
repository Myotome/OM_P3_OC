package com.openclassrooms.entrevoisins.ui.favorite_neighbour;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.FavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public class FavoriteNeighbourFragment extends Fragment {

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;
    private Context mContext;


    private static final String TAG = "DEBUGKEY FAV FRAG";

    //TODO : problème lors du clic poubelle, disparition du voisin sur la liste principale uniquement mais definitevement

    /**
     * Create and return a new instance
     * @return @{@link FavoriteNeighbourFragment}
     */
    public static FavoriteNeighbourFragment newInstance() {
        Log.d(TAG, "newInstance: ok");
        FavoriteNeighbourFragment fragment = new FavoriteNeighbourFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ok");
        mApiService = DI.getNeighbourApiService();
        mContext = getContext();
        mNeighbours = mApiService.getFavoriteNeighbourList();
//        FavoriteNeighbourManagement.loadFavoriteData(mContext);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ok");
        View view = inflater.inflate(R.layout.fragment_favorite_neighbour_list, container, false);
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {
        Log.d(TAG, "initList: ok");

//        FavoriteNeighbourManagement.loadFavoriteData(mContext);
//        mNeighbours = FavoriteNeighbourManagement.getFavoriteNeighbourList();

        if (mNeighbours != null) {
            mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours));
        } else{
            Toast.makeText(getContext(), "Pas de favoris enregistré", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void favoriteNeighbourEvent (FavoriteNeighbourEvent event) {
        mApiService.addOrRemoveFavoriteNeighbour(event.neighbour);
        initList();
    }

}
