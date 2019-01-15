package com.example.neelabh.bakingapp;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.Unbinder;

public class RecipeStepFragment extends Fragment {
    @BindView(R.id.recipe_step_detail)
    TextView mRecipeDetailView;
    @BindView(R.id.receipe_step_video)
    PlayerView playerView;
    private SimpleExoPlayer player;
    private Unbinder unbinder;

    private long playbackPosition = 0;
    private int currentWindow = 0;
    private boolean playWhenReady = true;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.recipe_step_layout,container,false);
        mContext = getActivity();
        ButterKnife.bind(this,rootView);
        mRecipeDetailView.setText(MyData.description);
        return rootView;
    }

    private void initializePlayer(){
        player = ExoPlayerFactory.newSimpleInstance(
                mContext);
        playerView.setPlayer(player);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow,playbackPosition);
        Uri uri = Uri.parse(MyData.videoUrl);
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource,true,false);
    }

    private MediaSource buildMediaSource(Uri uri){
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(mContext,
                Util.getUserAgent(mContext,mContext.getString(R.string.app_name)));
        return new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri);

    }

    @Override
    public void onStart(){
        super.onStart();
        if(Util.SDK_INT>23){
            initializePlayer();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        hideSystemUi();
        if((Util.SDK_INT<=23 || player==null )){
            initializePlayer();
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        if(Util.SDK_INT<=23){
            releasePlayer();
        }
    }

    @Override
    public void onStop(){
        super.onStop();
        if(Util.SDK_INT>23){
            releasePlayer();
        }
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }

    private void releasePlayer(){
        if(player!=null){
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
}
