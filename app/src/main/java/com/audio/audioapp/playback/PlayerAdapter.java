package com.audio.audioapp.playback;

import android.media.MediaPlayer;
import android.support.annotation.NonNull;


import com.audio.audioapp.models.Song;

import java.util.List;

public interface PlayerAdapter {
    void initMediaPlayer();

    void release();

    boolean isMediaPlayer();

    boolean isPlaying();

    void resumeOrPause();

    void reset();

    boolean isReset();

    void instantReset();

    void skip(final boolean isNext);

    void forward();

    void rewind();

    void seekTo(final int position);

    void setPlaybackInfoListener(final PlaybackInfoListener playbackInfoListener);

    Song getCurrentSong();

    @PlaybackInfoListener.State
    int getState();

    int getPlayerPosition();

    void registerNotificationActionsReceiver(final boolean isRegister);


    void setCurrentSong(@NonNull final Song song, @NonNull final List<Song> songs);

    MediaPlayer getMediaPlayer();

    void onPauseActivity();

    void onResumeActivity();
}
