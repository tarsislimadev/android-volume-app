package com.tarsislimadev.android.volume

import android.content.Context
import android.media.AudioManager
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback

class SetVolumeAction : ActionCallback {

    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        val mode = parameters[MODE_KEY] ?: return
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)

        val targetVolume = when (mode) {
            MODE_MUTE -> 0
            MODE_HALF -> maxVolume / 2
            MODE_MAX -> maxVolume
            else -> return
        }

        audioManager.setStreamVolume(
            AudioManager.STREAM_MUSIC,
            targetVolume,
            AudioManager.FLAG_SHOW_UI
        )
    }

    companion object {
        val MODE_KEY = ActionParameters.Key<String>("volume_mode")
        const val MODE_MUTE = "mute"
        const val MODE_HALF = "half"
        const val MODE_MAX = "max"

        fun actionParametersOf(vararg params: Pair<ActionParameters.Key<*>, Any>) = 
            ActionParameters.Builder().apply {
                params.forEach { (key, value) -> set(key, value) }
            }.build()
    }
}
