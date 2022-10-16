package com.example.myapplication

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
fun ViewNote(
    notes: List<Notes>,
    navigateButtonclick: () ->Unit,
    navigatetoViewNote:( )->Unit,
    navigatetoFirstScreen:()->Unit
){

    val configuration = ScreenLayout()
    
    Surface(
        modifier = Modifier
            .width(configuration.ScreenWidth)
            .height(configuration.ScreenHeight)
            .background(Color(179, 255, 218, 100))
            .fillMaxSize()
    ) {

        Column(modifier = Modifier
            .width(configuration.ScreenWidth)
            .height(configuration.ScreenHeight)
            .background(Color(179, 255, 218, 100))
            .fillMaxSize()) {

            TopAppBar(
                title = { Text(text = stringResource(id = R.string.TopAppBarString))},
                actions = {
                    Row() {
                        IconButton(onClick = { navigatetoFirstScreen()}) {
                            Icon(painter = painterResource(id = R.drawable.ic_baseline_playlist_add_24), contentDescription = "List Title")
                        }
                        IconButton(onClick = { navigateButtonclick() }) {
                            Icon(imageVector = Icons.Default.List, contentDescription = "List")
                        }

                        IconButton(onClick = { navigatetoViewNote()}) {
                            Icon(painter = painterResource(id = R.drawable.ic_baseline_view_list_24), contentDescription = "List Title")
                        }
                    }
                },
                backgroundColor = Color(0xFF7BF36E),elevation = 15.dp
            )


            if(configuration.ScreenWidthInfo is WindowInf.WindowType.Compact){
                LazyColumn(modifier = Modifier
                    .width(configuration.ScreenWidth)
                    .height(configuration.ScreenHeight)
                    .background(Color(179, 255, 218, 100))
                    .fillMaxSize()
                ){
                    items(notes){
                        eachNoteCard(it)
                    }
                }
            }
            else{
                LazyHorizontalGrid(rows = GridCells.Adaptive(100.dp), content = {
                    items(notes){
                        eachNoteCard(note = it)
                    }
                },modifier = Modifier
                    .width(configuration.ScreenWidth)
                    .height(configuration.ScreenHeight)
                    .background(Color(179, 255, 218, 100))
                    .fillMaxSize() )
            }


        }




    }
}


@Composable
fun eachNoteCard(note : Notes,
                 cornerRadius : Dp = 10.dp,
                 cutCornerSize : Dp = 30.dp){

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
                text = formatDate(note.DateTime.time),
                style = MaterialTheme.typography.h6,
                fontStyle = FontStyle.Italic
            )
        }
    }
}