package com.diagnal.mobile

import android.app.Application
import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.test.core.app.ApplicationProvider
import com.diagnal.mobile.local.LocalVideoDetailSource
import com.diagnal.mobile.local.LocalVideoSearchSource
import com.diagnal.mobile.models.VideoDetail
import com.diagnal.mobile.presentation.MainViewModel
import com.diagnal.mobile.utils.JsonSourceNames
import com.diagnal.mobile.utils.readJSONFromAsset
import junit.framework.TestCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    lateinit var videoSource: LocalVideoDetailSource

    lateinit var searchVideoSource: LocalVideoSearchSource

    lateinit var pager : Pager<Int,VideoDetail>

    lateinit var searchPager : Pager<Int,VideoDetail>

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        videoSource = LocalVideoDetailSource(context)
        searchVideoSource = LocalVideoSearchSource(context,"bird")
        pager = Pager(config = PagingConfig(20), pagingSourceFactory = { videoSource })
        searchPager = Pager(config = PagingConfig(20), pagingSourceFactory = { searchVideoSource })
    }

    @Test
    fun testLocalVideoList() {
        runBlocking {
            pager.flow.collectLatest {
                Assert.assertNotNull(it)
            }
        }
    }

    @Test
    fun testLocalSearchVideoList() {
        runBlocking {
            searchPager.flow.collectLatest {
                Assert.assertNotNull(it)
            }
        }
    }

    @Test
    fun testJsonToVideoList() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val list = readJSONFromAsset(JsonSourceNames[0], context)
        Assert.assertNotNull(list)
        Assert.assertEquals(list?.size,20)
    }
}