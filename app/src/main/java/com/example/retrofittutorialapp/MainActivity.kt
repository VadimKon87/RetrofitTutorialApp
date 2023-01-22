package com.example.retrofittutorialapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittutorialapp.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

/*
* Retrofit tutorial by Phiipp Lackner - https://youtu.be/t6Sql3WMAnk
* The data for our app is taken from https://jsonplaceholder.typicode.com/todos ( a list of todos)
*
* first we add build dependencies, create RecyclerView in activity_main.xls
* and item_todo.xml layout
* Add internet permission to manifest
* ----------------------------------------------------------------------------------
* STEP 1 - create TodoApi interface
* STEP 2 - automatically generate _Todo class from JSON using JsonToKotlinClass Plugin
* STEP 3 - create API by creating RetrofitInstance object (singleton class) that implements our _TodoApi interface
* STEP 4 - create RecyclerView Adapter - TodoAdapter.kt class file
* STEP 5.0 - in MainActivity create fun setupRecyclerView() and call it
* STEP 5.1 - make retrofit call (API request) in MainActivity in background thread
*           (if we were using MVVM we would make it in repository and then call it from the ViewModel)
*
* */

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        // making API request (step 5.1)
        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true // initially set to false, but when we make call then it should become visible
            val response = try {
                RetrofitInstance.api.getTodos() // our function from API interface
            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }
            delay(6000) // I added this to see the progress bar
            // if we get to the end of this code, that means that we got a response, but it doesn't
            // mean that the response was successful, so we add following if check
            if (response.isSuccessful && response.body() != null) {
                todoAdapter.todos = response.body()!! //response.body() returns a list of todos, that we set to our adapter
            } else {
                Log.e(TAG, "Response not successful")
            }
            binding.progressBar.isVisible = false // whether the call was successful or not, we hide progress bar

            /*
            * This is the setup for making API calls, if we need to make more calls, then we simply add the function to our API interface
            * */
        }
    }

    private fun setupRecyclerView() = binding.rvTodos.apply {
        todoAdapter = TodoAdapter()
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}

