package com.openclassrooms.entrevoisins.service;

import android.util.Log;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private ArrayList<Neighbour> mFavoriteNeighbourList;


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * get list of favorite
     * @return ArrayList
     */
    public ArrayList<Neighbour> getFavoriteNeighbourList() {
        if(mFavoriteNeighbourList == null){
            mFavoriteNeighbourList = new ArrayList<>();
        }
        return mFavoriteNeighbourList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    /**
     * change the favorite status according to the previous status
     * @param neighbour
     */
    @Override
    public void addOrRemoveFavoriteNeighbour(Neighbour neighbour) {

        if(!(mFavoriteNeighbourList.contains(neighbour))){
            mFavoriteNeighbourList.add(neighbour);

        }else{
            mFavoriteNeighbourList.remove(neighbour);

        }
    }
}
