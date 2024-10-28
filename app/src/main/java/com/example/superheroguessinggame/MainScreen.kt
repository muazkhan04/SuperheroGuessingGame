package com.example.superheroguessinggame

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.superheroguessinggame.ui.theme.SuperheroGuessingGameTheme


@Composable

fun MainScreen(modifier: Modifier = Modifier.fillMaxSize(), viewModel: SuperheroViewModel){


    val characterResponse by viewModel.characterResponse.collectAsState()


    //Checks if Response was Success or Fail
    characterResponse?.let { response ->
        //Prints Screen if Response was a success
        if (response.response == "success"){

            //Setting up Dialogs
            var showHintDialog by remember { mutableStateOf(false) }
            if (showHintDialog == true){
                HintDialog(onDismiss = {showHintDialog = false}, characterName = response.name)
            }

            var showCorrectDialog by remember { mutableStateOf(false) }
            if (showCorrectDialog == true){
                CorrectDialog(onDismiss = {showCorrectDialog = false; viewModel.newCharacter()})
            }

            var showIncorrectDialog by remember { mutableStateOf(false) }
            if (showIncorrectDialog == true){
                IncorrectDialog(onDismiss = {showIncorrectDialog = false})
            }
            
            var showGameOverDialog by remember { mutableStateOf(false) }
            if (showGameOverDialog == true){
                GameOverDialog(onDismiss = { showGameOverDialog = false; viewModel.newCharacter(); viewModel.currentScore = 0; viewModel.hintsLeft = 3 }, finalScore = viewModel.currentScore)
            }



            //Main Column
            Column(modifier = Modifier.fillMaxSize()) {


                //Row for the Score at the top
                Row(modifier = Modifier.fillMaxWidth().padding(15.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Current Score: " + viewModel.currentScore)
                    Text(text = "High Score: 25")
                }


                //Column for Image (Image should be between Score's at top and where this ends)
                Column(modifier = Modifier.fillMaxWidth().height(400.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom) {

                    //Displays Image from API
                    AsyncImage(model = response.url, contentDescription = response.name, modifier = Modifier.size(500.dp))
                }


                //Column for All other items
                Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

                    Spacer(modifier = Modifier.height(36.dp))
                    Text(text = "What is this character's name?", fontSize = 24.sp)

                    //Guess Text Field
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(label = {Text("Enter Name Here")}, value = viewModel.userGuess, onValueChange = {viewModel.userGuess = it})


                    //Guess Button - Checks if Correct or Incorrect
                    Spacer(modifier = Modifier.height(36.dp))
                    Button(onClick = {
                                     if (viewModel.userGuess.lowercase() == response.name.lowercase()){
                                         showCorrectDialog = true
                                         viewModel.currentScore += 1
                                     }
                                     else {
                                         showIncorrectDialog = true
                                     }},
                        modifier = Modifier.width(180.dp).height(60.dp)){
                        Text(text = "Guess Here", fontSize = 18.sp)
                    }

                    //Hint Button - Checks if Game is over
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(onClick = {
                                     if (viewModel.hintsLeft == 0){
                                         showGameOverDialog = true
                                     }
                                     else {
                                         showHintDialog = true
                                         viewModel.hintsLeft -= 1
                                     }},
                        modifier = Modifier.width(120.dp).height(50.dp)){
                        Text(text = "Hint", fontSize = 15.sp)
                    }

                    //Row for Hint Display - Prints Hints left
                    Spacer(modifier = Modifier.height(36.dp))
                    Row(modifier = Modifier.fillMaxWidth()){

                        Text("Hints Left:", fontSize = 30.sp)

                        if (viewModel.hintsLeft == 3){
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Hint", modifier = Modifier.size(48.dp), tint = Color.Red)
                                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Hint", modifier = Modifier.size(48.dp), tint = Color.Red)
                                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Hint", modifier = Modifier.size(48.dp), tint = Color.Red)
                            }
                        }

                        else if (viewModel.hintsLeft == 2){
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Hint", modifier = Modifier.size(48.dp), tint = Color.Red)
                                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Hint", modifier = Modifier.size(48.dp), tint = Color.Red)
                            }
                        }

                        else if (viewModel.hintsLeft == 1){
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Hint", modifier = Modifier.size(48.dp), tint = Color.Red)
                            }
                        }
                    }
                }
            }
        }

        //If Response is not a success, it will return the Error Screen
        else{
            ErrorScreen()
        }
    }
}
