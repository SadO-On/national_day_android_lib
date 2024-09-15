package studio.s98.game_tab_demo.home

import android.content.ContentResolver
import android.graphics.Color
import android.net.Uri
import android.view.Surface
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.lifecycle.Lifecycle
import androidx.media3.common.MediaItem
import androidx.media3.common.Player.STATE_ENDED
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import studio.s98.game_tab_demo.R
import studio.s98.game_tab_demo.util.OnLifecycleEvent


@OptIn(UnstableApi::class)
@Composable
fun StarterVideoPlayer() {

    val context = LocalContext.current

    val exoPlayer = ExoPlayer.Builder(context).build()
    val uri = Uri.Builder()
        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
        .path(R.raw.starter.toString())
        .build()
    val mediaSource = remember(uri) {
        MediaItem.fromUri(uri)
    }

    LaunchedEffect(key1 = mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
    }

    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                if (exoPlayer.playbackState == STATE_ENDED) {
                    exoPlayer.setMediaItem(mediaSource)
                    exoPlayer.prepare()
                    exoPlayer.playWhenReady = true
                }
            }

            else -> {}
        }
    }
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }


    AndroidView(
        factory = { ctx ->
            PlayerView(ctx).apply {
                player = exoPlayer
                setShutterBackgroundColor(Color.parseColor("#37918A"))
                controllerAutoShow = false
                useController = false
                hideController()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
    )


}
