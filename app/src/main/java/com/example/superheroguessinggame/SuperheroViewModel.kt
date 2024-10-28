package com.example.superheroguessinggame

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class SuperheroViewModel: ViewModel() {

    var userGuess by mutableStateOf("")
    var currentScore by mutableStateOf(0)
    var hintsLeft by mutableStateOf(3)

    //This is the Response we get from the website after running the API
    private val _characterResponse = MutableStateFlow<CharacterResponseData?>(null)
    val characterResponse: StateFlow<CharacterResponseData?> = _characterResponse

    //On initialization, this is the given CharacterID
    init {
        newCharacter()
    }

    //This is the final part that takes the connection of the URL's, fetches the response, and saves it
    fun getCharacterDetails(characterId: Int){
        viewModelScope.launch {
            val response = fetchCharacterDetails(characterId)
            _characterResponse.value = response
        }
    }



    fun newCharacter(){
        val randomID = Random.nextInt(1, 732)
        userGuess = ""
        getCharacterDetails(randomID)
    }






}


