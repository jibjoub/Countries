package com.example.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.model.CountryUi

@Composable
fun CountryList(
    countries: List<CountryUi>,
    onItemClick: (String) -> Unit,
) {
    LazyColumn {
        items(countries) { country ->
            CountryItem(country = country, onItemClick)
        }
    }
}

@Composable
fun CountryItem(
    country: CountryUi,
    onItemClick: (String) -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable { onItemClick(country.id) }
                .padding(16.dp),
    ) {
        // Flag
        AsyncImageSvg(
            country.flagUrl,
            Modifier
                .size(64.dp)
                .align(Alignment.CenterVertically),
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            // Name
            Text(text = country.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            // Capitals
            Text(text = country.capitals, fontWeight = FontWeight.Medium, fontSize = 14.sp)
            // Continents
            Text(text = country.continents, fontSize = 14.sp)
            // Population
            Text(text = "${country.population} inhabitants", fontSize = 14.sp)
        }
    }
}
