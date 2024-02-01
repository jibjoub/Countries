package com.example.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.models.DataState
import com.example.presentation.model.CountryUi

@Composable
fun DetailScreen(uiState: DataState<CountryUi>) {
    Surface {
        when (uiState) {
            is DataState.Success -> CountryDetail(countryUi = uiState.data)
            is DataState.Loading ->
                Loading()

            else -> {
                Error()
            }
        }
    }
}

@Composable
fun CountryDetail(countryUi: CountryUi) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImageSvg(
            url = countryUi.flagUrl,
            modifier =
                Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(0.8f),
        )

        // Common Name
        Text(
            text = countryUi.name,
            lineHeight = 30.sp,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 2.dp),
            textAlign = TextAlign.Center,
        )

        // Capitals
        Text(
            text = countryUi.capitals,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
        )

        // Continents
        Text(
            text = countryUi.continents,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(vertical = 8.dp),
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
