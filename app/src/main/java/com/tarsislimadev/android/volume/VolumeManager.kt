package com.tarsislimadev.android.volume

import android.content.Context
import android.media.AudioManager

class VolumeManager(context: Context) {
    private val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    fun getMaxVolume(streamType: Int): Int {
        return audioManager.getStreamMaxVolume(streamType)
    }

    fun getCurrentVolume(streamType: Int): Int {
        return audioManager.getStreamVolume(streamType)
    }

    fun setVolume(streamType: Int, index: Int) {
        // FLAG_SHOW_UI displays the system volume UI overlay if desired
        audioManager.setStreamVolume(streamType, index, AudioManager.FLAG_SHOW_UI)
    }
}
