
package com.openclassrooms.entrevoisins.favorite_neighbour;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class FavoriteNeighboursTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private final String NEIGHBOUR = "My neighbours";
    private final String FAVORITE = "Favorites";
    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure open detailed screen when click on a neighbour
     */
    @Test
    public void detailedNeighboursActivity_is_launched() {

        // Given : We check if all elements exists
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // Click on item in random position in the list
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(new Random().nextInt(ITEMS_COUNT), click()));
        // Verifying if new activity is on view
        onView(withId(R.id.sv_detailed)).check(matches(withId(R.id.sv_detailed)));

    }

    /**
     * When we click on item in neighbour list, the item is correctly shown in favorite detailed activity
     */
    @Test
    public void selectedNeighbourName_is_display_in_favoriteActivity() {
        // Given : We check if all elements exists
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // Click on item in position 2
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        // Verifying if TextView is correct
        onView(withId(R.id.tv_detailed_bigname)).check(matches(withText("Chloé")));
        onView(withId(R.id.tv_detailed_name)).check(matches(withText("Chloé")));

    }

    /**
     * perform click on Favorite Tab
     */
    @Test
    public void clickOnTabFavorite(){
        Matcher<View> matcher = allOf(withText(FAVORITE), isDescendantOfA(withId(R.id.main_content)));
        onView(matcher).perform(click());
    }

    /**
     * Perform click on My Neighbour tab
     */
    @Test
    public void clickOnTabNeighbour(){
        Matcher<View> matcher = allOf(withText(NEIGHBOUR), isDescendantOfA(withId(R.id.main_content)));
        onView(matcher).perform(click());
    }

    /**
     * Only favorite neighbour shown on favorite list
     */
    @Test
    public void onlyFavorite_in_list_of_favoriteActivity(){
        //Before click, check if list of favorite is empty
        clickOnTabFavorite();
        onView(withId(R.id.favorite_list_neighbours)).check(withItemCount(0));
        clickOnTabNeighbour();

        // Click on item in position 2
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(withId(R.id.fab_detailed_favorite)).perform(click());
        //Back to previous view
        onView(withId(R.id.iv_detailed_back)).perform(click());


        // Check number of neighbour in favorite list
        clickOnTabFavorite();
        onView(withId(R.id.favorite_list_neighbours)).check(withItemCount(1));

    }
}