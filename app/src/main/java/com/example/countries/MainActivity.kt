package com.example.countries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.common.models.DataState
import com.example.countries.ui.theme.CountriesTheme
import com.example.presentation.model.CountryUi
import com.example.presentation.view.CountryList
import com.example.presentation.viewmodel.CountriesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: CountriesViewModel by viewModel()

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadWorldCountriesData()
        setContent {
            CountriesTheme {
                val isRefreshing = viewModel.isRefreshing.collectAsStateWithLifecycle()
                val pullRefreshState =
                    rememberPullRefreshState(
                        refreshing = isRefreshing.value,
                        onRefresh = {
                            viewModel.refresh()
                        },
                    )
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .pullRefresh(pullRefreshState)
                            .background(Color(android.graphics.Color.parseColor("#f2ede4"))),
                ) {
                    val countries: DataState<List<CountryUi>> by viewModel.countriesUiState.observeAsState(DataState.Loading)
                    when (val state = countries) {
                        is DataState.Success -> CountryList(countries = state.data)
                        is DataState.Loading ->
                            Loading()
                        else -> {
                            Error { viewModel.loadWorldCountriesData() }
                        }
                    }
                    PullRefreshIndicator(
                        refreshing = isRefreshing.value,
                        state = pullRefreshState,
                        modifier = Modifier.align(Alignment.TopCenter),
                    )
                }
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
fun Error(onRetry: () -> Unit) {
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

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onRetry,
                modifier =
                    Modifier
                        .wrapContentWidth()
                        .padding(8.dp),
                colors =
                    ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colorScheme.primary,
                    ),
            ) {
                Text("Retry", Modifier)
            }
        }
    }
}
