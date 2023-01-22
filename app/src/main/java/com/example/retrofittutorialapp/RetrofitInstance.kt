package com.example.retrofittutorialapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
* If we use dependency injection (for example with Dagger Hilt) then we don't need this, instead
* we create our API interface in a specific module
* */

object RetrofitInstance {

    val api: TodoApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create()) // Retrofit needs to know how it
            // should take the JSON data it gets and parse it to the _Todo data class we created
            // GSON is a converter for JSON
            // Additionally we could also add CallAdapterFactory if we need to parse the response
            // for example as an RXJava stream (we don't need it here)
            .build()
            .create(TodoApi::class.java)
    }
}

/*
* Lazy means it is not initialized right away, but when this API is accessed
* */