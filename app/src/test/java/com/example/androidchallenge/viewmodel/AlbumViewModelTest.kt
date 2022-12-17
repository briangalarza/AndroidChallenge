package com.example.androidchallenge.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.androidchallenge.model.Album
import com.example.androidchallenge.networking.Resource
import com.example.androidchallenge.repository.AlbumRepository
import com.example.androidchallenge.utils.TestCoroutineRule
import com.nhaarman.mockitokotlin2.mock
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AlbumViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: AlbumViewModel
    private lateinit var albumRepository: AlbumRepository

    @Mock
    private lateinit var albumResponseObserver: Observer<Resource<List<Album>>>

    @Before
    fun setUp() {
        albumRepository = mock()
        viewModel = AlbumViewModel(albumRepository)
    }

    @Test
    fun `when fetching results ok then return a list successfully`() {
        val emptyList = arrayListOf<Album>()
        testCoroutineRule.runBlockingTest {
            viewModel.getAlbums().observeForever(albumResponseObserver)
            Mockito.`when`(
                albumRepository.getAlbums()
            ).thenAnswer {
                Resource.success(emptyList)
            }
            viewModel.loadAlbums()
            Assert.assertNotNull(viewModel.getAlbums().value)
            assertEquals(Resource.success(emptyList), viewModel.getAlbums().value)
        }
    }

    @Test
    fun `when calling for results then return loading`() {
        testCoroutineRule.runBlockingTest {
            viewModel.getAlbums().observeForever(albumResponseObserver)
            viewModel.loadAlbums()
            Mockito.verify(albumResponseObserver).onChanged(Resource.loading(null))
        }
    }

    @Test
    fun `when fetching results fails then return an error`() {
        val exception = Mockito.mock(HttpException::class.java)
        testCoroutineRule.runBlockingTest {
            viewModel.getAlbums().observeForever(albumResponseObserver)
            Mockito.`when`(
                albumRepository.getAlbums()
            ).thenAnswer {
                Resource.error(exception.toString(),null)
            }
            viewModel.loadAlbums()
            Assert.assertNotNull(viewModel.getAlbums().value)
            assertEquals(Resource.error(exception.toString(),null), viewModel.getAlbums().value)
        }
    }

    @After
    fun tearDown() {
        viewModel.getAlbums().removeObserver(albumResponseObserver)
    }
}