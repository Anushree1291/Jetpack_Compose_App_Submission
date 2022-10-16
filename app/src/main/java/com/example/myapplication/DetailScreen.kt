package com.example.myapplication

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AndroidViewConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.model.Notes
import com.example.myapplication.ui.theme.ScreenLayout
import com.example.myapplication.ui.theme.WindowInf

@Composable
fun DetailScreen(notes:List<Notes>,
                 toRemoveNote: (Notes) -> Unit,
                 navigateButtonclick: () ->Unit,
                 navigatetoViewNote:( )->Unit,
                 navigatetoFirstScreen:()->Unit
){

    if(notes.isNullOrEmpty()){
        EmptyScreen()
    }
    else{

        val configuration = ScreenLayout()

        Surface(modifier = Modifier
            .width(configuration.ScreenWidth)
            .height(configuration.ScreenHeight)
            .background(Color(179, 255, 218, 100))) {

            Column(modifier = Modifier
                .width(configuration.ScreenWidth)
                .height(configuration.ScreenHeight)
                .background(Color(179, 255, 218, 100))
            ) {

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


                ShowAllNotes(notes,toRemoveNote)
            }

        }



    }
}


@Composable
fun EmptyScreen(){
    val context = LocalContext.current
    val configuration = ScreenLayout()

    Box(modifier = Modifier
        .width(configuration.ScreenWidth)
        .height(configuration.ScreenHeight)
        .fillMaxSize()
        .background(Color(179, 255, 218, 100)),
    contentAlignment = Alignment.Center
    ) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(painter = painterResource(id = R.drawable.ic_baseline_hourglass_empty_24), contentDescription = "Empty List")
            Text(text = "List is empty")
            Toast.makeText(context,"List is empty",Toast.LENGTH_LONG).show()
        }
    }
}