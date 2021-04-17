package com.example.theustimes.news.models

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.theustimes.R
import com.example.theustimes.utils.Util
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(AndroidJUnit4::class)
class LikesAndCommentsApiResponseTest{

    lateinit var util: Util

    private val context: Context = ApplicationProvider.getApplicationContext()


    @Before
    fun setUp() {
        util = Util()
    }

    @Test
    fun `test deserialization like api response`() {
        val jsonString: String = util.getStringFromRawResource(context, R.raw.like_api_reponse)
        assertNotNull(jsonString)
        val likesCommentsApiResponse: LikesAndCommentsApiResponse =
            util.convertJsonToObject(jsonString, LikesAndCommentsApiResponse::class.java)
        assertNotNull(likesCommentsApiResponse)

        assertEquals(10, likesCommentsApiResponse.likes)
        assertEquals(0, likesCommentsApiResponse.comments)
    }

    @Test
    fun `test deserialization comment api response`() {
        val jsonString: String = util.getStringFromRawResource(context, R.raw.commnet_api_reponse)
        assertNotNull(jsonString)
        val likesCommentsApiResponse: LikesAndCommentsApiResponse =
            util.convertJsonToObject(jsonString, LikesAndCommentsApiResponse::class.java)
        assertNotNull(likesCommentsApiResponse)

        assertEquals(0, likesCommentsApiResponse.likes)
        assertEquals(20, likesCommentsApiResponse.comments)
    }
}