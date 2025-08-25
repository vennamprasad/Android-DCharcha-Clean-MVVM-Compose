package com.dcharcha.core.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.dcharcha.core.ui.R

object Icons {
    const val DefaultAppIcon = "app_icon"
    const val DefaultAppIconRound = "app_icon_round"
    const val GoogleLogo = "google_logo"
    const val FacebookLogo = "facebook_logo"
    const val TwitterLogo = "twitter_logo"
    const val GithubLogo = "github_logo"
    const val DCharchaLogo = "dcharcha_logo"
    const val DCharchaLogoText = "dcharcha_logo_text"
    const val Search = "search"
    const val Clear = "clear"
    const val Close = "close"
    const val Back = "back"
    const val Menu = "menu"
    const val MoreVertical = "more_vertical"
    const val MoreHorizontal = "more_horizontal"
    const val Info = "info"
    const val Edit = "edit"
    const val Delete = "delete"
    const val Add = "add"
    const val Check = "check"
    const val ArrowUp = "arrow_up"
    const val ArrowDown = "arrow_down"
    const val ArrowLeft = "arrow_left"
    const val ArrowRight = "arrow_right"
    const val Upload = "upload"
    const val Download = "download"
    const val Share = "share"
    const val Lock = "lock"
    const val Unlock = "unlock"
    const val Settings = "settings"
    const val Language = "language"
    const val Theme = "theme"
    const val Notification = "notification"
    const val Help = "help"
    const val User = "user"
    const val Users = "users"
    const val Home = "home"
    const val Chat = "chat"
    const val Message = "message"
    const val Attachment = "attachment"
    const val Camera = "camera"
    const val Gallery = "gallery"
    const val Location = "location"
    const val Calendar = "calendar"
    const val Clock = "clock"
    const val Favorite = "favorite"
}

/**
 * Resolve a drawable resource ID by its string key.
 */
fun iconRes(name: String): Int = when (name) {
    Icons.Search -> R.drawable.outline_search_24
    Icons.Clear -> R.drawable.outline_clear_all_24
    Icons.Close -> R.drawable.outline_close_24
    Icons.Language -> R.drawable.outline_language_24
    else -> R.drawable.outline_android_24
}

/**
 * Compose helper for showing icons by key.
 */
@Composable
fun AppIcon(
    key: String, contentDescription: String? = null, modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = iconRes(key)),
        contentDescription = contentDescription,
        modifier = modifier
    )
}