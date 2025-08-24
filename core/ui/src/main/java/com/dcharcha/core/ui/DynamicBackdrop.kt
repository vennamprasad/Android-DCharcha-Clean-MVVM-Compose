package com.dcharcha.core.ui

import android.app.WallpaperManager
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

enum class BackdropMode { DynamicColor, BlurredWallpaper, BrandGradient }

@Composable
fun DynamicBackdrop(
    mode: BackdropMode = BackdropMode.DynamicColor,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    when (mode) {
        BackdropMode.DynamicColor -> {
            val scheme = MaterialTheme.colorScheme
            Box(
                modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            0f to scheme.primary.copy(alpha = 0.22f),
                            0.5f to scheme.secondary.copy(alpha = 0.18f),
                            1f to if (isSystemInDarkTheme()) Color.Black else Color.White
                        )
                    )
            ) { content() }
        }

        BackdropMode.BrandGradient -> {
            Box(
                modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(
                            listOf(Color(0xFF5B8CFF), Color(0xFF8AE8FF), Color(0xFFA0FFCF))
                        )
                    )
            ) { content() }
        }

        BackdropMode.BlurredWallpaper -> {
            val context = LocalContext.current
            var bmp by remember { mutableStateOf<Bitmap?>(null) }
            LaunchedEffect(Unit) {
                bmp =
                    withContext(Dispatchers.IO) @androidx.annotation.RequiresPermission(anyOf = ["android.permission.READ_WALLPAPER_INTERNAL", android.Manifest.permission.MANAGE_EXTERNAL_STORAGE]) {
                    WallpaperManager.getInstance(context).drawable?.toBitmap(1080, 1920)
                }
            }
            Box(modifier.fillMaxSize()) {
                bmp?.let {
                    Image(
                        bitmap = it.asImageBitmap(), contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .blur(50.dp)
                    )
                }
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color.Black.copy(0.25f),
                                    Color.Transparent,
                                    Color.Black.copy(0.35f)
                                )
                            )
                        )
                )
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { content() }
            }
        }
    }
}
