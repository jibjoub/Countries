package com.example.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.common.models.DataState
import com.example.presentation.model.CountryUi
import com.example.presentation.viewmodel.DetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(id: String) {
    // Can I give the id to an invoke function of it (knowing that it is injected with Koin)?
    // To avoid having a method in DetailViewModel
    val detailViewModel: DetailViewModel = koinViewModel()
    val uiState: DataState<CountryUi> by detailViewModel.detailUiState.collectAsState()
    detailViewModel.getDetailById(id)
    Surface(color = MaterialTheme.colorScheme.background) {
        when (val state = uiState) {
            is DataState.Success -> CountryDetails(countryUi = state.data)
            is DataState.Loading ->
                Loading()

            else -> {
                Error()
            }
        }
    }
}

@Composable
fun CountryDetails(countryUi: CountryUi) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FlagImage(flagUrl = countryUi.flagUrl)

        // Common Name
        Text(
            text = countryUi.commonName,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp),
            textAlign = TextAlign.Center,
        )

        // Official Name
        Text(
            text = countryUi.officialName,
            fontSize = 18.sp,
            color = Color.Gray,
            modifier = Modifier.padding(vertical = 4.dp),
        )

        // Continents
        Text(
            text = "Continents: ${countryUi.continents}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 4.dp),
        )

        // Capitals
        Text(
            text = "Capitals: ${countryUi.capitals}",
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 4.dp),
        )

        // Population
        Text(
            text = "Population: ${countryUi.population}",
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 4.dp),
        )

        // Flag Description
        Text(
            text = countryUi.flagDescription,
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 8.dp),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun FlagImage(flagUrl: String) {
    AsyncImage(
        model = flagUrl,
        contentDescription = "Flag",
        modifier =
            Modifier
                .size(256.dp),
    )
}
