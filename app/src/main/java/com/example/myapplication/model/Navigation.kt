package com.example.myapplication.model

sealed class Navigation(val route :String){
    object  FirstScreen:Navigation("FirstScreen")
    object  DetailScreen:Navigation("DetailScreen")
    object ViewNote:Navigation("ViewNote")
}
