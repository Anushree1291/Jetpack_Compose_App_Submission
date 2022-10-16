package com.example.myapplication.componentsInApp


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun titleDescritptionButton(
    modifier: Modifier=Modifier,
    text: String,
    OnTextChange:(String) -> Unit,
    maxlines : Int =1,
    label:String
){

    val keyboardController= LocalSoftwareKeyboardController.current

    TextField(
        value =text ,
        onValueChange = OnTextChange,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = colorResource(id = R.color.lightBlue),
            cursorColor = Color.Black,
            disabledLabelColor = colorResource(id = R.color.lightBlue),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        maxLines = maxlines,
        label = { Text(text = label)},
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()

        })
    )
}


@Composable
fun saveButton(
    enabled : Boolean=true,
    OnClick : () ->Unit,
    text: String
){
    Button(onClick =OnClick,
        shape= CircleShape,
        enabled = enabled
        ) {
        Text(text = text)
    }
}