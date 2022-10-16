package com.example.myapplication.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ScreenLayout() : WindowInf{

    val configuration = LocalConfiguration.current

    return WindowInf(
        ScreenWidthInfo = when{
            configuration.screenWidthDp<=600 ->WindowInf.WindowType.Compact
            configuration.screenWidthDp<=840 ->WindowInf.WindowType.Medium
            else -> WindowInf.WindowType.Expanded
        },

        ScreenHeightInfo = when{
            configuration.screenHeightDp<=480 ->WindowInf.WindowType.Compact
            configuration.screenHeightDp<=840 ->WindowInf.WindowType.Medium
            else -> WindowInf.WindowType.Expanded
        },

        ScreenWidth = configuration.screenWidthDp.dp,

        ScreenHeight = configuration.screenHeightDp.dp
    )
}


data class WindowInf(
    val ScreenWidthInfo : WindowType,
    val ScreenHeightInfo : WindowType,
    val ScreenWidth : Dp,
    val ScreenHeight : Dp
){

    sealed class WindowType(){
        object Compact : WindowType()
        object Medium : WindowType()
        object Expanded : WindowType()
    }
}