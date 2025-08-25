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
import com.dcharcha.core.ui.theme.BrandPrimary
import com.dcharcha.core.ui.theme.BrandSecondary
import com.dcharcha.core.ui.theme.BrandTertiary
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

        // Uses MaterialTheme colors so it adapts to your light/dark schemes (and dynamic color if enabled)
        BackdropMode.DynamicColor -> {
            val scheme = MaterialTheme.colorScheme
            val isDark = isSystemInDarkTheme()
            Box(
                modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                scheme.primary.copy(alpha = 0.22f),
                                scheme.secondary.copy(alpha = 0.18f),
                                scheme.surface.copy(alpha = if (isDark) 0.6f else 1f)
                            )
                        )
                    )
            ) { content() }
        }

        // Brand gradient using your teal palette (hero/CTA feel)
        BackdropMode.BrandGradient -> {
            Box(
                modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                BrandPrimary.copy(alpha = 0.95f),
                                BrandSecondary.copy(alpha = 0.60f),
                                BrandTertiary.copy(alpha = 0.50f)
                            )
                        )
                    )
            ) { content() }
        }

        // Blurred current wallpaper behind content with a subtle vignette overlay
        BackdropMode.BlurredWallpaper -> {
            val context = LocalContext.current
            var bmp by remember { mutableStateOf<Bitmap?>(null) }

            LaunchedEffect(Unit) {
                bmp = withContext(Dispatchers.IO) {
                    runCatching {
                        // No runtime permission required to read the current wallpaper drawable.
                        WallpaperManager.getInstance(context)
                            .drawable
                            ?.toBitmap() // let it pick intrinsic size, we’ll blur anyway
                    }.getOrNull()
                }
            }

            Box(modifier.fillMaxSize()) {
                if (bmp != null) {
                    Image(
                        bitmap = bmp!!.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .blur(50.dp)
                    )
                } else {
                    // Fallback if wallpaper is unavailable: brand‑tinted background
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(
                                Brush.linearGradient(
                                    listOf(
                                        BrandPrimary.copy(alpha = 0.9f),
                                        BrandSecondary.copy(alpha = 0.5f)
                                    )
                                )
                            )
                    )
                }

                // Readability overlay (top/bottom fade)
                val top = if (isSystemInDarkTheme()) Color.Black.copy(0.35f) else Color.Black.copy(0.18f)
                val mid = Color.Transparent
                val bottom = if (isSystemInDarkTheme()) Color.Black.copy(0.45f) else Color.Black.copy(0.25f)

                Box(
                    Modifier
                        .fillMaxSize()
                        .background(Brush.verticalGradient(listOf(top, mid, bottom)))
                )

                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    content()
                }
            }
        }
    }
}
