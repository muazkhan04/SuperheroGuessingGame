package com.example.superheroguessinggame

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.lang.Exception


// Main Interface: This finalizes the URL to get the response
interface SuperheroAPIService {
    @GET("{character-id}/image")
    suspend fun getCharacterDetails(@Path("character-id") characterId: Int): CharacterResponseData
}


//This build the connection to the BaseURL and gets things ready to get the information
private const val BASE_URL = "https://superheroapi.com/api/e9b5790cdbc316386c3d46490f4fa199/"
private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
val superHeroServiceAPI = retrofit.create(SuperheroAPIService::class.java)









//This is the connection between the Base URL and the Final URL (connecting the two functions above)
suspend fun fetchCharacterDetails(characterId: Int): CharacterResponseData?{
    return try{
        superHeroServiceAPI.getCharacterDetails(characterId)
    } catch(e: Exception){
        e.printStackTrace()
        null
    }


}

