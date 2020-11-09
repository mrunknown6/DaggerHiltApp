package com.example.daggerhiltapp.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.daggerhiltapp.R
import com.example.daggerhiltapp.models.Blog
import com.example.daggerhiltapp.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.lang.StringBuilder

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private val viewModel: MainViewModel by viewModels()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvents)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, {dataState ->
            when (dataState) {
                is DataState.Success -> {
                    displayLoading(false)
                    displayTitles(dataState.data)
                }
                is DataState.Error -> {
                    displayLoading(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayLoading(true)
                }
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun displayError(message: String?) {
        message?.let {
            tvTitle.text = message
            return
        }
        tvTitle.text = "Unknown error"
    }

    private fun displayLoading(isDisplayed: Boolean) {
        pb.visibility = if (isDisplayed) View.VISIBLE else View.INVISIBLE
    }

    private fun displayTitles(blogs: List<Blog>) {
        val sb = StringBuilder()
        blogs.forEach { blog ->
            sb.append(blog.title)
        }
        tvTitle.text = sb.toString()
    }

}