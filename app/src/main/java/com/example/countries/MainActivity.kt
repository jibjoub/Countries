package com.example.countries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.common.models.DataState
import com.example.countries.ui.theme.CountriesTheme
import com.example.presentation.model.CountryUi
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
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val countries: DataState<List<CountryUi>> by viewModel.countriesUiState.observeAsState(DataState.Loading)
                    when (val state = countries) {
                        is DataState.Success -> Greeting(state.data.first().commonName)
                        is DataState.Loading -> Greeting("Loading")
                        is DataState.Error -> Greeting("There has been an error :(((")
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
