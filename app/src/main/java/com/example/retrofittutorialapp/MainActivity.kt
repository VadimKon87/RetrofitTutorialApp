package com.example.retrofittutorialapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

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
* STEP 3 - create RetrofitInstance object (singleton class) that implements our _TodoApi interface
* */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

