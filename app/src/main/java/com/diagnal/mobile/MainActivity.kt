package com.diagnal.mobile

import VideoDetailScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.diagnal.mobile.presentation.MainViewModel
import com.diagnal.mobile.ui.theme.DiagnalTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiagnalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
                    VideoDetailScreen(
                        viewModel.videoPagingFlow,
                        viewModel.videoSearchPagingFlow,
                        viewModel.searchStringState,
                        viewModel.searchState,
                        viewModel.searchTextState
                    )
                }
            }
        }
    }
}