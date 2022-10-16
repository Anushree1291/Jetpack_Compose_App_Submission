package com.example.myapplication

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.model.Navigation
import com.example.myapplication.screen.FirstScreen
import com.example.myapplication.screen.NoteViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.ScreenLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(179, 255, 218,100)
                ) {

                    val noteViewModel: NoteViewModel by viewModels()
                    AppNavigator(noteViewModel)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Called")
    }


}


@Composable
fun AppNavigator(noteViewModel: NoteViewModel = viewModel()) {

    val navController = rememberNavController()
    var notesList = noteViewModel.noteList.collectAsState().value
    val configuration = ScreenLayout()

    NavHost(navController = navController, startDestination =Navigation.FirstScreen.route) {

        composable(Navigation.FirstScreen.route){
            FirstScreen(notes = notesList,
                toSaveNote = {
                    noteViewModel.addNote(it) },
                toRemoveNote = { noteViewModel.removeNote(it) },
                navigateButtonclick = {navController.navigate(Navigation.DetailScreen.route) },
                navigatetoViewNote = {navController.navigate(Navigation.ViewNote.route) },
                navigatetoFirstScreen={ navController.navigate(Navigation.FirstScreen.route) }
            )
        }


        composable(Navigation.DetailScreen.route) {
            DetailScreen(notesList,
                toRemoveNote = { noteViewModel.removeNote(it) },
                navigateButtonclick = {navController.navigate(Navigation.DetailScreen.route) },
                navigatetoViewNote = {navController.navigate(Navigation.ViewNote.route) },
                navigatetoFirstScreen={ navController.navigate(Navigation.FirstScreen.route) }
            )
        }

        composable(Navigation.ViewNote.route){
            ViewNote(notesList,
                navigateButtonclick = {navController.navigate(Navigation.DetailScreen.route) },
                navigatetoViewNote = {navController.navigate(Navigation.ViewNote.route) },
                navigatetoFirstScreen={ navController.navigate(Navigation.FirstScreen.route) })
        }

    }
}