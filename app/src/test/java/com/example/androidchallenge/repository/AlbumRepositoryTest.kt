package com.example.androidchallenge.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.androidchallenge.networking.Resource
import com.example.androidchallenge.networking.ResponseHandler
import com.example.androidchallenge.repository.api.PhotosApi
import com.example.androidchallenge.utils.MockWebServerBaseTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class AlbumRepositoryTest: MockWebServerBaseTest() {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var albumRepository: AlbumRepository
    private lateinit var photosApi: PhotosApi
    private val responseHandler = ResponseHandler()

    override fun isMockServerEnabled() = true

    @Before
    fun start() {
        photosApi = provideTestApiService()
        albumRepository = AlbumRepository(photosApi,responseHandler)
    }

    @Test
    fun `given response ok when fetching results then return a list with elements`() {
        runBlocking {
            mockHttpResponse("json/albums_response_one_item.json", HttpURLConnection.HTTP_OK)
            val apiResponse = albumRepository.getAlbums()
            Assert.assertNotNull(apiResponse)
            Assert.assertEquals(apiResponse.data?.size, 1)
        }
    }

    @Test
    fun `given response ok when fetching empty results then return an empty list`() {
        runBlocking {
            mockHttpResponse("json/albums_response_empty_list.json", HttpURLConnection.HTTP_OK)
            val apiResponse = albumRepository.getAlbums()
            Assert.assertNotNull(apiResponse)
            Assert.assertEquals(apiResponse.data?.size, 0)
        }
    }

    @Test
    fun `given response failure when fetching results then return exception`() {
        runBlocking {
            mockHttpResponse(404)
            val apiResponse = albumRepository.getAlbums()
            Assert.assertNotNull(apiResponse)
            val expectedValue = Resource.error("Not found", null)
            junit.framework.Assert.assertEquals(expectedValue, apiResponse)
        }
    }
}