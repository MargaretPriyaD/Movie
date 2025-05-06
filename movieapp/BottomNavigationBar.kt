package com.example.movieapp

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.content.Intent
import android.widget.Toast
import com.example.movieapp.activity.MainActivity
import com.example.movieapp.activity.ProfileActivity
import com.example.movieapp.activity.WatchlistActivity
import com.example.movieapp.activity.SupportActivity
import com.example.movieapp.activity.SettingsActivity
import android.content.Context

@Preview
@Composable
fun BottomNavigationBar() {
    val bottomMenuItemsList = prepareBottomMenu()
    val contextForToast = LocalContext.current
    var selectedItem by remember {
        mutableStateOf("Home")
    }

    BottomAppBar(
        cutoutShape = CircleShape,
        contentColor = colorResource(id = R.color.white),
        backgroundColor = colorResource(id = R.color.black3),
        elevation = 3.dp
    ) {
        bottomMenuItemsList.forEachIndexed { index, bottomMenuItem ->
            BottomNavigationItem(
                selected = (selectedItem == bottomMenuItem.label),
                onClick = {
                    selectedItem = bottomMenuItem.label
                    navigateToScreen(contextForToast, bottomMenuItem.label)
                },
                icon = {
                    Icon(
                        painter = bottomMenuItem.icon,
                        contentDescription = bottomMenuItem.label,
                        modifier = Modifier
                            .height(20.dp)
                            .width(20.dp)
                    )
                },
                label = {
                    Text(
                        text = bottomMenuItem.label,
                        maxLines = 1, // Keep it on one line
                        fontSize = 10.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                },
                alwaysShowLabel = true,
                enabled = true
            )
        }
    }
}

fun navigateToScreen(context: Context, label: String) {
    when (label) {
        "Home" -> context.startActivity(Intent(context, MainActivity::class.java))
        "Profile" -> context.startActivity(Intent(context, ProfileActivity::class.java))
        "Watchlist" -> context.startActivity(Intent(context, WatchlistActivity::class.java))
        "Support" -> context.startActivity(Intent(context, SupportActivity::class.java))
        "Settings" -> context.startActivity(Intent(context, SettingsActivity::class.java))
        else -> Toast.makeText(context, "Coming soon!", Toast.LENGTH_SHORT).show()
    }
}

data class BottomMenuItem(
    val label: String,
    val icon: Painter
)

@Composable
fun prepareBottomMenu(): List<BottomMenuItem> {
    return listOf(
        BottomMenuItem(
            label = "Home",
            icon = painterResource(id = R.drawable.btn_1)
        ),
        BottomMenuItem(
            label = "Profile",
            icon = painterResource(id = R.drawable.profile)
        ),
        BottomMenuItem(
            label = "Watchlist",
            icon = painterResource(id = R.drawable.watchlist)
        ),
        BottomMenuItem(
            label = "Support",
            icon = painterResource(id = R.drawable.support)
        ),
        BottomMenuItem(
            label = "Settings",
            icon = painterResource(id = R.drawable.settings)
        )
    )
}
