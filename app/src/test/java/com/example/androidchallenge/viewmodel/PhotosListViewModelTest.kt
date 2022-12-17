package com.example.androidchallenge.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.androidchallenge.model.Album
import com.example.androidchallenge.model.Photo
import com.example.androidchallenge.networking.Resource
import com.example.androidchallenge.repository.PhotoListRepository
import com.example.androidchallenge.utils.TestCoroutineRule
import com.nhaarman.mockitokotlin2.mock
import junit.framework.Assert
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
class PhotosListViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: PhotoListViewModel
    private lateinit var photoListRepository: PhotoListRepository

    @Mock
    private lateinit var photosResponseObserver: Observer<Resource<List<Photo>>>

    @Before
    fun setUp() {
        photoListRepository = mock()
        viewModel = PhotoListViewModel(photoListRepository)
    }

    @Test
    fun `when fetching results ok then return a list successfully`() {
        val emptyList = arrayListOf<Album>()
        testCoroutineRule.runBlockingTest {
            viewModel.getPhotos().observeForever(photosResponseObserver)
            Mockito.`when`(
                photoListRepository.getPhotoList("")
            ).thenAnswer {
                Resource.success(emptyList)
            }
            viewModel.loadPhotos("")
            Assert.assertNotNull(viewModel.getPhotos().value)
            Assert.assertEquals(Resource.success(emptyList), viewModel.getPhotos().value)
        }
    }

    @Test
    fun `when calling for results then return loading`() {
        testCoroutineRule.runBlockingTest {
            viewModel.getPhotos().observeForever(photosResponseObserver)
            viewModel.loadPhotos("")
            Mockito.verify(photosResponseObserver).onChanged(Resource.loading(null))
        }
    }

    @Test
    fun `when fetching results fails then return an error`() {
        val exception = Mockito.mock(HttpException::class.java)
        testCoroutineRule.runBlockingTest {
            viewModel.getPhotos().observeForever(photosResponseObserver)
            Mockito.`when`(
                photoListRepository.getPhotoList("")
            ).thenAnswer {
                Resource.error(exception.toString(), null)
            }
            viewModel.loadPhotos("")
            Assert.assertNotNull(viewModel.getPhotos().value)
            Assert.assertEquals(
                Resource.error(exception.toString(), null),
                viewModel.getPhotos().value
            )
        }
    }

    @After
    fun tearDown() {
        viewModel.getPhotos().removeObserver(photosResponseObserver)
    }
}