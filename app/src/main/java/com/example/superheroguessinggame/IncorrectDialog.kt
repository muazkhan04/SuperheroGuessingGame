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
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


// This PopUp comes when they guess an incorrect answer
@Composable
fun IncorrectDialog(onDismiss: ()->Unit){

    AlertDialog(onDismissRequest = onDismiss, confirmButton = { /* Empty */}, modifier = Modifier.height(250.dp),

        //Dialog Title
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = "Incorrect")
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Incorrect!")
            }
        },

        //Body Text
        text = {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

                Text("You didn't get the correct answer! Try again or use a hint!")
                Spacer(modifier = Modifier.height(12.dp))

                Button(onClick = onDismiss, modifier = Modifier.width(180.dp).padding(10.dp)) {
                    Text(text = "Try Again")
                }
            }
        }
    )
}
