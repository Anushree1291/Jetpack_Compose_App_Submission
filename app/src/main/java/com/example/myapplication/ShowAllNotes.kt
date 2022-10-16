package com.example.myapplication

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.example.myapplication.model.Notes
import com.example.myapplication.ui.theme.ScreenLayout
import com.example.myapplication.ui.theme.WindowInf
import com.example.myapplication.utility.formatDate
import kotlin.random.Random

@Composable
fun ShowAllNotes(notes:List<Notes>,
                 toRemoveNote: (Notes) -> Unit
){
    val configuration = ScreenLayout()

    if(configuration.ScreenWidthInfo is WindowInf.WindowType.Compact) {

        LazyColumn(
            modifier = Modifier
                .width(configuration.ScreenWidth)
                .background(Color(179, 255, 218, 100))
                .padding(20.dp)
        ) {
            items(notes) { it ->
                OneNote(note = it, toRemoveNote)
            }
        }
    }
    else{
        LazyVerticalGrid(columns = GridCells.Adaptive(300.dp), content = {
            items(notes) { it ->
                OneNote(note = it, toRemoveNote)
            }
        })
        }
}

@Composable
fun OneNote(
    note:Notes,
    toRemoveNote: (Notes) -> Unit,
    cornerRadius : Dp = 10.dp,
    cutCornerSize :Dp = 30.dp
){
    
    Box(modifier = Modifier.padding(10.dp)) {
        Canvas(modifier = Modifier.matchParentSize()){
            val clipPath= Path().apply {
                lineTo(size.width-cutCornerSize.toPx(),0f)
                lineTo(size.width,cutCornerSize.toPx())
                lineTo(size.width,size.height)
                lineTo(0f,size.height)
                close()
            }

            val colo=Color(Random.nextFloat(),Random.nextFloat(),Random.nextFloat(),1f)
            val arr= Random.nextFloat()

            clipPath(clipPath){
                drawRoundRect(
                    color =colo,
                    size=size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
                drawRoundRect(
                    color = Color(ColorUtils.blendARGB(ColorUtils.LABToColor(arr.toDouble(),arr.toDouble(),arr.toDouble()),0x000000,0.2f)),
                    topLeft = Offset(size.width-cutCornerSize.toPx(),-100f),
                    size= Size(cutCornerSize.toPx()+100f,cutCornerSize.toPx()+100f),
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
        }
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .padding(end = 20.dp)) {
                Text(
                    text = note.Title,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(7.dp))
                Text(
                    text = note.Description,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.W500
                )

                Spacer(modifier = Modifier.padding(7.dp))
                Text(
                    text = formatDate(note.DateTime.time),
                    style = MaterialTheme.typography.h6,
                    fontStyle = FontStyle.Italic
                )
                IconButton(
                    onClick = { toRemoveNote(note) }
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Note")

                }
            }
    }

}