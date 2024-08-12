package com.example.atlysassignment

import  android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.atlysassignment.ui.navigation.Nav
import com.example.atlysassignment.ui.theme.AtlysAssignmentTheme
import com.example.atlysassignment.ui.theme.HeadingStyle
import com.example.atlysassignment.viewmodel.MovieDetailViewModel
import com.example.atlysassignment.viewmodel.MovieListViewModel
import com.example.atlysassignment.viewmodel.SearchMovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val movieListViewModel by viewModels<MovieListViewModel>()
    private val searchMovieViewModel by viewModels<SearchMovieViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AtlysAssignmentTheme {
                Nav(
                    movieListViewModel,
                    searchMovieViewModel
                )
            }
        }
    }
}

@Composable
fun TextComponent() {
    Text(text = "ANIKET")
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AtlysAssignmentTheme {
        TextComponent()
    }

}

