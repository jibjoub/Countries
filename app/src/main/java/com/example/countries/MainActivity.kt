package com.example.countries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.models.DataState
import com.example.countries.ui.theme.CountriesTheme
import com.example.presentation.model.CountryUi
import com.example.presentation.view.CountryList
import com.example.presentation.viewmodel.CountriesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: CountriesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadCountriesData()
        setContent {
            CountriesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(android.graphics.Color.parseColor("#f2ede4")),
                ) {
                    val countries: DataState<List<CountryUi>> by viewModel.countriesUiState.observeAsState(DataState.Loading)
                    when (val state = countries) {
                        is DataState.Success -> CountryList(countries = state.data)
                        is DataState.Loading ->
                            Box(
                                modifier =
                                    Modifier
                                        .fillMaxSize(),
                            ) {
                                CircularProgressIndicator(
                                    modifier =
                                        Modifier
                                            .width(64.dp)
                                            .align(Alignment.Center),
                                    color = MaterialTheme.colorScheme.primary,
                                )
                            }
                        else -> {
                            Box(
                                modifier =
                                    Modifier
                                        .fillMaxSize(),
                            ) {
                                Text(
                                    "Something went wrong, please check that you are connected to the " +
                                        "Internet. If it persists, contact the developer.",
                                    modifier = Modifier.align(Alignment.Center),
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
) {
    if (name.isNullOrBlank()) {
        Text(
            text = "Hello Loading!",
            modifier = modifier,
        )
    } else {
        Text(
            text = "Hello $name!",
            modifier = modifier,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CountriesTheme {
        Greeting("Android")
    }
}
