package com.example.retrofittutorialapp

import retrofit2.Response
import retrofit2.http.GET


// we create simple function to retrieve data from our API, real app would have more functions
// to write, delete, edit etc.
// NETWORK CALL SHOULD NEVER BE DONE IN MAIN THREAD - THUS WE USE COROUTINE (suspend keyword)

interface TodoApi {

    @GET("/todos")
    suspend fun getTodos(): Response<List<Todo>>

}

/*
* 4 main types of HTTP requests -
* 1) get requests - retrieving data from remote server (@GET annotation + plus what comes after base url)
*  https://jsonplaceholder.typicode.com/todos - full URL
*  https://jsonplaceholder.typicode.com - base URL, /todos - after base url, and what we use in annotation
* the base URL will be specified when we create the API instance (RetrofitInstance.kt)
* 2) post requests - posting data from our device to server,
* 3) put requests - updating data on server
* 4) and delete requests - deleting data on server
* */

/*
* Attaching Query parameters to our request, for example if we need an API key to access API
* suspend fun getTodos(@Query("key") key: String)
* In this project we don't need a key or any request parameters
*
* */

/*
* Example of what post request could look like:
*
*    @POST("/createTodo")
*    suspend fun createTodo(@Body _todo: _Todo): Response<CreateTodoResponse>
* */




