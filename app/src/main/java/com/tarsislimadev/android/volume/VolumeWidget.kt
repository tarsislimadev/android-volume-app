package com.tarsislimadev.android.volume

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionParametersOf
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.appwidget.action.actionRunCallback

class VolumeWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceTheme {
                WidgetContent()
            }
        }
    }

    @Composable
    private fun WidgetContent() {
        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .padding(12.dp)
                .background(GlanceTheme.colors.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Volume Quick Controls",
                style = TextStyle(
                    color = GlanceTheme.colors.onSurface,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = GlanceModifier.height(8.dp))

            Row(
                modifier = GlanceModifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WidgetButton(
                    text = "Mute",
                    action = actionRunCallback<SetVolumeAction>(
                        actionParametersOf(
                            SetVolumeAction.MODE_KEY to SetVolumeAction.MODE_MUTE
                        )
                    )
                )

                Spacer(modifier = GlanceModifier.width(8.dp))

                WidgetButton(
                    text = "50%",
                    action = actionRunCallback<SetVolumeAction>(
                        actionParametersOf(
                            SetVolumeAction.MODE_KEY to SetVolumeAction.MODE_HALF
                        )
                    )
                )

                Spacer(modifier = GlanceModifier.width(8.dp))

                WidgetButton(
                    text = "Max",
                    action = actionRunCallback<SetVolumeAction>(
                        actionParametersOf(
                            SetVolumeAction.MODE_KEY to SetVolumeAction.MODE_MAX
                        )
                    )
                )
            }
        }
    }

    @Composable
    private fun WidgetButton(text: String, action: androidx.glance.action.Action) {
        Text(
            text = text,
            style = TextStyle(
                color = GlanceTheme.colors.onPrimary,
                fontWeight = FontWeight.Medium
            ),
            modifier = GlanceModifier
                .background(GlanceTheme.colors.primary)
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .clickable(action)
        )
    }
}
