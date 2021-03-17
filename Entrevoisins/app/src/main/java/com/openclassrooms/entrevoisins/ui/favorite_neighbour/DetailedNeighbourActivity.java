package com.openclassrooms.entrevoisins.ui.favorite_neighbour;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.model.Neighbour;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailedNeighbourActivity extends AppCompatActivity {
    @BindView(R.id.iv_detailed_back)
    public ImageView mBtBack;
    @BindView(R.id.iv_detaile_avatar)
    public ImageView mIVAvatar;
    @BindView(R.id.tv_detailed_bigname)
    public TextView mTVBigName;
    @BindView(R.id.fab_detailed_favorite)
    public FloatingActionButton mFABFavorite;

    @BindView(R.id.tv_detailed_name)
    public TextView mTVName;
    @BindView(R.id.tv_detailed_location)
    public TextView mTVLocation;
    @BindView(R.id.tv_detailed_phone)
    public TextView mTVPhone;
    @BindView(R.id.tv_detailed_website)
    public TextView mTVWebsite;

    @BindView(R.id.tv_detailed_about)
    public TextView mTVAbout;

    private static final String TAG = "DEBUGKEY DNA";
    private Neighbour mNeighbour;
    private NeighbourApiService mApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_neighbour);
        mApiService = DI.getNeighbourApiService();
        Log.d(TAG, "onCreate: ok");
        ButterKnife.bind(this);
        getIncomingIntent();


        mBtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        mFABFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                mApiService.addOrRemoveFavoriteNeighbour(mNeighbour);
//                FavoriteNeighbourManagement.addOrRemoveToFavoriteList(mNeighbour,context);
                starStatus(mNeighbour);
                Log.d(TAG, "onClick: fait");

            }
        });

    }

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: ok");
        if(getIntent().hasExtra("extraNeighbour") ){
             mNeighbour = getIntent().getParcelableExtra("extraNeighbour");

            Log.d(TAG, "getIncomingIntent: intent : " + mNeighbour.toString());
            displayDataFromIntent(mNeighbour);
        }
    }

    private void displayDataFromIntent(Neighbour neighbour) {
        Log.d(TAG, "setData: ok");


        mTVBigName.setText(neighbour.getName());
        mTVName.setText(neighbour.getName());
        mTVLocation.setText(neighbour.getAddress());
        mTVPhone.setText(neighbour.getPhoneNumber());
        mTVAbout.setText(neighbour.getAboutMe());
        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .into(mIVAvatar);
        mTVWebsite.setText(neighbour.getName() + " " + getString(R.string.did_not));
        starStatus(neighbour);

    }

    private void starStatus(Neighbour neighbour) {
        if(mApiService.getFavoriteNeighbourList().contains(neighbour)){
            mFABFavorite.setImageResource(R.drawable.ic_baseline_star_24);
        }else{
            mFABFavorite.setImageResource(R.drawable.ic_baseline_star_border_24);
        }
    }
}