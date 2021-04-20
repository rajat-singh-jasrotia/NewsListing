package com.example.theustimes.utils

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.theustimes.news.models.Articles
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(AndroidJUnit4::class)
class ResultTest {

    @Test
    fun `should return loading when status is loading`() {
        val givenViewState = Result.loading<Any>()
        assertNotNull(givenViewState)
        assertEquals(Status.LOADING, givenViewState.status)
    }

    @Test
    fun `should return success when status is success`() {
        val givenViewState = Result.success(emptyList<Articles>())
        assertNotNull(givenViewState)
        assertEquals(Status.SUCCESS, givenViewState.status)
    }

    @Test
    fun `should return error when status is error`() {
        val givenViewState = Result.error<Any>("500 Internal Server Error")
        assertNotNull(givenViewState)
        assertNotNull(givenViewState.message)
        assertEquals(Status.ERROR, givenViewState.status)
    }
}