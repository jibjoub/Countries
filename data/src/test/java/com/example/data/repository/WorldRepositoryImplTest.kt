package com.example.data.repository

import com.example.common.models.DataState
import com.example.data.remote.api.CountryApi
import com.example.data.remote.entity.CountryEntity
import com.example.data.remote.entity.FlagEntity
import com.example.data.remote.entity.NameEntity
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

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
            coEvery { countryApi.getWorldCountries() } returns Response.success(null)

            worldRepository.getWorldCountries().collect { dataState ->
                assert(dataState is DataState.Error)
            }
        }
    }

    @Test
    fun `Given an empty body of the Retrofit Instance response, when we call getWorldCountries, then it should emit Error data state`() {
        runBlocking {
            coEvery { countryApi.getWorldCountries() } returns Response.success(emptyList())

            worldRepository.getWorldCountries().collect { dataState ->
                assert(dataState is DataState.Error)
            }
        }
    }

    @Test
    fun `Given an error from the Retrofit Instance response, when we call getWorldCountries, then it should emit Error data state`() {
        runBlocking {
            coEvery { countryApi.getWorldCountries() } returns Response.error(400, ResponseBody.create(null, "test"))

            worldRepository.getWorldCountries().collect { dataState ->
                assert(dataState is DataState.Error)
            }
        }
    }

    @Test
    fun `Given a valid flow of data from Retrofit Instance, when we call getCountryById, then it should emit Success data state with correct data`() {
        runBlocking {
            val countryResponse = listOf(countryEntityBuilder())
            coEvery { countryApi.getCountryById("1") } returns Response.success(countryResponse)

            worldRepository.getCountryById("1").collect { dataState ->
                assertTrue(dataState is DataState.Success)
            }
        }
    }

    @Test
    fun `Given a null body of the Retrofit Instance response, when we call getCountryById, then it should emit Error data state`() {
        runBlocking {
            coEvery { countryApi.getCountryById("1") } returns Response.success(null)

            worldRepository.getCountryById("1").collect { dataState ->
                assert(dataState is DataState.Error)
            }
        }
    }

    @Test
    fun `Given an empty body of the Retrofit Instance response, when we call getCountryById, then it should emit Error data state`() {
        runBlocking {
            coEvery { countryApi.getCountryById("1") } returns Response.success(emptyList())

            worldRepository.getCountryById("1").collect { dataState ->
                assert(dataState is DataState.Error)
            }
        }
    }

    @Test
    fun `Given an error from the Retrofit Instance response, when we call getCountryById, then it should emit Error data state`() {
        runBlocking {
            coEvery { countryApi.getCountryById("1") } returns Response.error(400, ResponseBody.create(null, "test"))

            worldRepository.getCountryById("1").collect { dataState ->
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
