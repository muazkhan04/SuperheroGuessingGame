package com.example.superheroguessinggame


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// This PopUp comes when they guess a correct answer
@Composable
fun CorrectDialog(onDismiss: ()->Unit, viewModel: SuperheroViewModel = SuperheroViewModel()){

    AlertDialog(onDismissRequest = onDismiss, confirmButton = { /* Empty */}, modifier = Modifier.height(250.dp),
        //Dialog Title
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "Correct")
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Correct!")
            }
        },

        //Body Text
        text = {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

                Text("You got the correct answer! Click Next to get the next question!", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(12.dp))

                Button(onClick = onDismiss, modifier = Modifier.width(180.dp).padding(10.dp)) {
                    Text(text = "Next")
                }
            }
        }
    )
}
