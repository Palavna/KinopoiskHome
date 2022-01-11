package com.example.yana.kinopoiskhome.utils

import android.content.Context
import android.net.Uri
import com.example.yana.kinopoiskhome.R
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util

interface MediaPlayer{
    fun init(context: Context): ExoPlayer?
    fun play(url: String, context: Context)
    fun stop()
}

class MediaPlayerImpl: MediaPlayer {

    private var exoPlayer: ExoPlayer? = null

    override fun init(context: Context): ExoPlayer? {
        val trackSelector = DefaultTrackSelector()
        val loadControl = DefaultLoadControl()
        val renderersFactory = DefaultRenderersFactory(context)
        exoPlayer = ExoPlayerFactory.newSimpleInstance(renderersFactory,trackSelector,loadControl)
        return exoPlayer
    }

    override fun play(url: String, context: Context) {
        val userAgent = Util.getUserAgent(context, context.getString(R.string.app_name))
        val httpDataSourceFactory = DefaultHttpDataSourceFactory(
            userAgent,
            null,
            DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS,
            DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS,
            true
        )
        val mediaSource = ExtractorMediaSource.Factory(DefaultDataSourceFactory(context, null, httpDataSourceFactory))
            .setExtractorsFactory(DefaultExtractorsFactory())
            .createMediaSource(Uri.parse(url))
        exoPlayer?.prepare(mediaSource)
        exoPlayer?.playWhenReady = true
    }

    override fun stop() {
        exoPlayer?.stop()
        exoPlayer?.release()
    }

}