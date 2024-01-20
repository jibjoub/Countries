package com.example.data.repository

import com.example.common.models.DataState
import com.example.data.entity.CountryEntity
import com.example.data.entity.FlagEntity
import com.example.data.entity.NameEntity
import com.example.data.remote.api.CountryApi
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class WorldRepositoryImplTest {
    private lateinit var countryApi: CountryApi
    private lateinit var worldRepository: WorldRepositoryImpl

    @Before
    fun setup() {
        countryApi = mockk()
        worldRepository = WorldRepositoryImpl(countryApi)
    }

    @Test
    fun `Given a valid flow of data from Retrofit Instance, when we call getWorldCountries, then it should emit Success data state with correct data`() {
        runBlocking {
            val countriesResponse = listOf(countryEntityBuilder())
            coEvery { countryApi.getWorldCountries() } returns Response.success(countriesResponse)

            worldRepository.getWorldCountries().collect { dataState ->
                assertTrue(dataState is DataState.Success)
            }
        }
    }

    @Test
    fun `Given a null body of the Retrofit Instance response, when we call getWorldCountries, then it should emit Error data state`() {
        runBlocking {
            val countriesResponse = listOf(countryEntityBuilder())
            coEvery { countryApi.getWorldCountries() } returns Response.success(null)

            worldRepository.getWorldCountries().collect { dataState ->
                assert(dataState is DataState.Error)
            }
        }
    }
}

private fun countryEntityBuilder(
    id: String? = null,
    names: NameEntity? = nameEntityBuilder(),
    capitals: List<String>? = null,
    continents: List<String>? = null,
    flag: FlagEntity? = flagEntityBuilder(),
    population: Int? = null,
) = CountryEntity(
    id,
    names,
    capitals,
    continents,
    flag,
    population,
)

private fun nameEntityBuilder(
    name: String? = null,
    officialName: String? = null,
): NameEntity = NameEntity(name, officialName)

private fun flagEntityBuilder(
    url: String? = null,
    description: String? = null,
): FlagEntity = FlagEntity(url, description)
