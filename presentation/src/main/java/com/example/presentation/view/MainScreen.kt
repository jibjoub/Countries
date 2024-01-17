package com.example.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.common.models.DataState
import com.example.presentation.model.CountryUi
import com.example.presentation.navigation.Screen
import com.example.presentation.viewmodel.MainViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(navController: NavController) {
    val mainViewModel: MainViewModel = getViewModel()
    val uiState by mainViewModel.uiState.observeAsState(initial = DataState.Loading)

    val onItemClick = { countryUi: CountryUi ->
        navController.navigate(Screen.DetailScreen.route + "/" + countryUi.id)
    }

    Surface {
        when (val state = uiState) {
            is DataState.Success -> CountryList(countries = state.data, onItemClick)
            is DataState.Loading ->
                Loading()

            else -> {
                Error()
            }
        }
    }
}

@Composable
fun Loading() {
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
}

@Composable
fun Error() {
    Box(
        modifier =
            Modifier
                .fillMaxSize(),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text =
                    "Something went wrong, please check that you are connected to the " +
                        "Internet. If it persists, contact the developer.",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun AsyncImageSvg(
    url: String,
    modifier: Modifier,
) {
    AsyncImage(
        model =
            ImageRequest.Builder(LocalContext.current)
                .data(url)
                .decoderFactory(SvgDecoder.Factory())
                .build(),
        contentDescription = null,
        modifier = modifier,
    )
}
