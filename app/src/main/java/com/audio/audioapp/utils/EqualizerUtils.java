package com.audio.audioapp.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.MediaPlayer;
import android.media.audiofx.AudioEffect;
import android.support.annotation.NonNull;
import android.widget.Toast;

public class EqualizerUtils {
    public static boolean hasEqualizer(@NonNull final Context context) {
        final Intent effects = new Intent(AudioEffect.ACTION_DISPLAY_AUDIO_EFFECT_CONTROL_PANEL);
        final PackageManager pm = context.getPackageManager();
        final ResolveInfo ri = pm.resolveActivity(effects, 0);
        return ri != null;
    }

    static void openAudioEffectSession(@NonNull final Context context, final int sessionId) {
        final Intent intent = new Intent(AudioEffect.ACTION_OPEN_AUDIO_EFFECT_CONTROL_SESSION);
        intent.putExtra(AudioEffect.EXTRA_AUDIO_SESSION, sessionId);
        intent.putExtra(AudioEffect.EXTRA_PACKAGE_NAME, context.getPackageName());
        intent.putExtra(AudioEffect.EXTRA_CONTENT_TYPE, AudioEffect.CONTENT_TYPE_MUSIC);
        context.sendBroadcast(intent);
    }

    static void closeAudioEffectSession(@NonNull final Context context, final int sessionId) {
        final Intent audioEffectsIntent = new Intent(AudioEffect.ACTION_CLOSE_AUDIO_EFFECT_CONTROL_SESSION);
        audioEffectsIntent.putExtra(AudioEffect.EXTRA_AUDIO_SESSION, sessionId);
        audioEffectsIntent.putExtra(AudioEffect.EXTRA_PACKAGE_NAME, context.getPackageName());
        context.sendBroadcast(audioEffectsIntent);
    }

    static void openEqualizer(@NonNull final Activity activity, MediaPlayer mediaPlayer) {
        final int sessionId = mediaPlayer.getAudioSessionId();

        if (sessionId == AudioEffect.ERROR_BAD_VALUE) {
            notifyNoSessionId(activity);
        } else {
            try {
                final Intent effects = new Intent(AudioEffect.ACTION_DISPLAY_AUDIO_EFFECT_CONTROL_PANEL);
                effects.putExtra(AudioEffect.EXTRA_AUDIO_SESSION, sessionId);
                effects.putExtra(AudioEffect.EXTRA_CONTENT_TYPE, AudioEffect.CONTENT_TYPE_MUSIC);
                activity.startActivityForResult(effects, 0);
            } catch (@NonNull final ActivityNotFoundException notFound) {
                notFound.printStackTrace();
            }
        }
    }

    public static void notifyNoSessionId(@NonNull final Context context) {
        Toast.makeText(context, "Play a Song first", Toast.LENGTH_SHORT).show();
    }
}
