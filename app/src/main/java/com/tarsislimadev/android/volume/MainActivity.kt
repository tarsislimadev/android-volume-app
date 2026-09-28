package com.tarsislimadev.android.volume

import android.media.AudioManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.tarsislimadev.android.volume.ui.theme.AndroidVolumeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidVolumeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    VolumeControlScreen()
                }
            }
        }
    }
}

@Composable
fun VolumeControlScreen() {
    val context = LocalContext.current
    val volumeManager = remember { VolumeManager(context) }

    val streams = listOf(
        "Media" to AudioManager.STREAM_MUSIC,
        "Ring" to AudioManager.STREAM_RING,
        "Notification" to AudioManager.STREAM_NOTIFICATION,
        "Alarm" to AudioManager.STREAM_ALARM
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Volume Control",
            style = MaterialTheme.typography.headlineMedium
        )

        streams.forEach { (label, streamType) ->
            VolumeSlider(
                label = label,
                streamType = streamType,
                volumeManager = volumeManager
            )
        }
    }
}

@Composable
fun VolumeSlider(
    label: String,
    streamType: Int,
    volumeManager: VolumeManager
) {
    val maxVolume = remember { volumeManager.getMaxVolume(streamType) }
    var currentVolume by remember { 
        mutableStateOf(volumeManager.getCurrentVolume(streamType).toFloat()) 
    }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, style = MaterialTheme.typography.titleMedium)
            Text(text = "${currentVolume.toInt()} / $maxVolume")
        }
        Slider(
            value = currentVolume,
            onValueChange = { newValue ->
                currentVolume = newValue
                volumeManager.setVolume(streamType, newValue.toInt())
            },
            valueRange = 0f..maxVolume.toFloat(),
            steps = if (maxVolume > 1) maxVolume - 1 else 0
        )
    }
}
