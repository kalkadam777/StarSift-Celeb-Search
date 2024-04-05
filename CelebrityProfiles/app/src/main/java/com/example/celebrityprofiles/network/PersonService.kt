package com.example.celebrityprofiles.network

import com.example.celebrityprofiles.model.Person
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonService {
    @GET("celebrity")
    fun fetchPersonList(@Query("name") name: String):Call<List<Person>>
}