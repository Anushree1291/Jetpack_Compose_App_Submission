package com.example.myapplication.componentsInApp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myapplication.model.Notes
import com.example.myapplication.ui.theme.ScreenLayout
import com.example.myapplication.ui.theme.WindowInf
import com.example.myapplication.utility.formatDate
import kotlin.random.Random


@Composable
fun ShowNotes(
    note: Notes,
    OnDeleteButtonClick : (Notes) ->Unit,
    cornerRadius : Dp= 10.dp
    ) {

    var maxlines=1
    val configuration = ScreenLayout()

    if(configuration.ScreenWidthInfo is WindowInf.WindowType.Compact){
        maxlines=1
    }
    else{
        maxlines=10
    }

    Surface(
        Modifier
            .padding(10.dp)
            .clip(
                RoundedCornerShape(
                    topStart = cornerRadius,
                    bottomStart = cornerRadius,
                    bottomEnd = cornerRadius,
                )
            )
            .fillMaxWidth(),
        color = Color(Random.nextFloat(),Random.nextFloat(),Random.nextFloat(),1f),
        elevation = 10.dp
    ) {
        
        Column(modifier = Modifier.padding(horizontal = 5.dp, vertical = 7.dp), horizontalAlignment = Alignment.Start) {

            Text(text = note.Title,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                maxLines = maxlines)

            Text(text = note.Description,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.W500 ,
                maxLines = maxlines)

            Text(text = formatDate(note.DateTime.time),
                style = MaterialTheme.typography.h6,
                fontStyle = FontStyle.Italic)

            Row() {
                IconButton(onClick = { OnDeleteButtonClick(note)}) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Deletes text")
                }
            }


        }
    }
}

