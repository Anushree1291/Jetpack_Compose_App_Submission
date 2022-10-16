package com.example.myapplication.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.componentsInApp.ShowNotes
import com.example.myapplication.componentsInApp.saveButton
import com.example.myapplication.componentsInApp.titleDescritptionButton
import com.example.myapplication.model.Notes
import com.example.myapplication.ui.theme.ScreenLayout


@Composable
fun FirstScreen(
    notes : List<Notes>,
    toSaveNote: (Notes) -> Unit,
    toRemoveNote: (Notes) -> Unit,
    navigateButtonclick: () ->Unit,
    navigatetoViewNote:( )->Unit,
    navigatetoFirstScreen:()->Unit,
    modifier: Modifier= Modifier
        .fillMaxSize()
        .background(Color(179, 255, 218, 100))
   ){

    var text1 by remember{ mutableStateOf(" ") }
    var description1 by remember{ mutableStateOf(" ") }
    val context= LocalContext.current
    val configuration= ScreenLayout()

    Surface(modifier = Modifier
        .width(configuration.ScreenWidth)
        .height(configuration.ScreenHeight)
        .background(Color(179, 255, 218, 100))
        .fillMaxSize()) {

        Column(modifier= Modifier
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


            Column(modifier = Modifier
                .width(configuration.ScreenWidth)
                .fillMaxWidth()
                .padding(10.dp)
                .background(Color(179, 255, 218, 1)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                titleDescritptionButton(
                    modifier = Modifier
                        .width(configuration.ScreenWidth)
                        .fillMaxWidth()
                        .padding(top = 5.dp, bottom = 7.dp),
                    text = text1,
                    label = "Add a Title",
                    OnTextChange = {
                        if(it.all{char -> char.isLetter() || char.isWhitespace() }) text1=it
                    }
                )
                Spacer(modifier = Modifier.padding(5.dp))

                titleDescritptionButton(
                    modifier = Modifier
                        .width(configuration.ScreenWidth)
                        .fillMaxWidth()
                        .padding(top = 5.dp, bottom = 7.dp),
                    text = description1,
                    label="Add a Description",
                    OnTextChange = {
                        if(it.all{char -> char.isLetter() || char.isWhitespace() }) description1=it
                    }
                )

                saveButton(  OnClick = {
                    if(text1.isNotEmpty() && description1.isNotEmpty()){
                        toSaveNote(Notes(Title = text1, Description = description1))
                        Toast.makeText(context,"Note is Added",Toast.LENGTH_SHORT).show()
                        text1=""
                        description1=""
                    }
                },
                    text = "Save")

            }

            Divider(modifier = Modifier.padding(10.dp))

            LazyColumn(){
                items(notes){ note->
                    ShowNotes(note = note,
                        OnDeleteButtonClick = { toRemoveNote(note) }
                    )
                }
            }

        }

    }

}

