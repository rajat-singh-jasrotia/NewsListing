package com.example.theustimes

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runners.model.Statement
import kotlin.jvm.Throws

class TestCaseUtils {

    @ExperimentalCoroutinesApi
    class TestCoroutineRule : TestWatcher() {

        internal val testCoroutineDispatcher = TestCoroutineDispatcher()

        private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

        override fun starting(description: Description?) {
            Dispatchers.setMain(testCoroutineDispatcher)
        }

        override fun finished(description: Description?) {
            Dispatchers.resetMain()
            testCoroutineDispatcher.cleanupTestCoroutines()
        }

        override fun apply(base: Statement, description: Description?) = object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                Dispatchers.setMain(testCoroutineDispatcher)

                base.evaluate()

                Dispatchers.resetMain()
                testCoroutineScope.cleanupTestCoroutines()
            }
        }

        fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) =
            testCoroutineScope.runBlockingTest { block() }
    }
}