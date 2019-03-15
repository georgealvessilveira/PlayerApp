package com.georgeasilveira.playerapp.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Util;

public class ExoPlayerUtil {

    @SuppressLint("StaticFieldLeak")
    private static SimpleExoPlayer player;
    private static DataSource.Factory mediaDataSourceFactory;
    private static DefaultExtractorsFactory extractorsFactory;
    private static BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

    private ExoPlayerUtil() { }

    public static SimpleExoPlayer getPlayer(Context context,
                                            SimpleExoPlayerView exoPlayerView) {
        configFields(context, (TransferListener<? super DataSource>) bandwidthMeter);
        exoPlayerView.requestFocus();

        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);

        DefaultTrackSelector trackSelector = new
                DefaultTrackSelector(videoTrackSelectionFactory);

        if (context != null) {
            player = ExoPlayerFactory.newSimpleInstance(context, trackSelector);
        }

        return player;
    }

    public static MediaSource getMediaSource(Context context, Uri uri) {
        configFields(context, (TransferListener<? super DataSource>) bandwidthMeter);

        return new ExtractorMediaSource(uri, mediaDataSourceFactory,
                extractorsFactory, null, null);
    }

    private static void configFields(Context context, TransferListener<? super DataSource> bandwidthMeter) {
        mediaDataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "Player App"),
                bandwidthMeter);

        extractorsFactory = new DefaultExtractorsFactory();
    }
}
