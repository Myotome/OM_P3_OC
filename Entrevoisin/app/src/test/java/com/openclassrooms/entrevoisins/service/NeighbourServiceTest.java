package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;
    private Neighbour addNeighbour;


    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
        addNeighbour = new Neighbour(99, "Harry Potter", "https://i.pravatar.cc/150?u=a042581f4e29026704d", "4 Privet Drive, Little Whinging",
                "Hedwig Owl Service",  "Bonjour !Je souhaiterais faire de la magie. Pas initiée, je recherche une école et plusieurs personnes susceptibles de devenir mes amis !Je suis persécuté par un méchant qui m'a laissé une cicatrice mais moi je suis gentil");
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void getFavoriteNeighbourWithSuccess(){
        ArrayList<Neighbour> favoriteNeighbour = service.getFavoriteNeighbourList();
        ArrayList<Neighbour> expectedFavoriteNeighbour = new ArrayList<>();
        assertThat(favoriteNeighbour, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedFavoriteNeighbour.toArray()));
    }

    @Test
    public void createNeighbourWithSuccess(){
        List<Neighbour> neighbours = service.getNeighbours();
        service.createNeighbour(addNeighbour);
        assertTrue(neighbours.contains(addNeighbour));
    }

    @Test
    public void addOrRemoveFavoriteNeighbourWithSuccess(){
        ArrayList<Neighbour> favoriteNeighbour = service.getFavoriteNeighbourList();
        service.addOrRemoveFavoriteNeighbour(addNeighbour);
        assertTrue(favoriteNeighbour.contains(addNeighbour));

        service.addOrRemoveFavoriteNeighbour(addNeighbour);
        assertFalse(favoriteNeighbour.contains(addNeighbour));


    }
}
