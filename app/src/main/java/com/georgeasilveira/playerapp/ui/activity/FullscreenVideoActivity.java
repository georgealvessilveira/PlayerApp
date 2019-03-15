package com.georgeasilveira.playerapp.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.georgeasilveira.playerapp.R;
import com.georgeasilveira.playerapp.handler.ExoPlayerVideoHandler;
import com.georgeasilveira.playerapp.model.Models;
import com.georgeasilveira.playerapp.model.Video;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

public class FullscreenVideoActivity extends AppCompatActivity {

    private boolean destroyVideo = true;
    private Video video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_video);
        video = (Video) getIntent().getSerializableExtra(Models.VIDEO);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ExoPlayerVideoHandler.getInstance().goToForeground();
        SimpleExoPlayerView exoPlayerView = findViewById(R.id.aula_fullscreen_player);
        hideNavigationBar(exoPlayerView);

        Uri content = Uri.parse(video.getVideoLocation());
        ExoPlayerVideoHandler.getInstance()
                .prepareExoPlayerForUri(getApplicationContext(), content, exoPlayerView);
    }

    @Override
    public void onBackPressed() {
        destroyVideo = false;
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ExoPlayerVideoHandler.getInstance().goToBackground();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (destroyVideo) {
            ExoPlayerVideoHandler.getInstance().releaseVideoPlayer();
        }
    }

    private void hideNavigationBar(SimpleExoPlayerView exoPlayerView) {
        exoPlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}
