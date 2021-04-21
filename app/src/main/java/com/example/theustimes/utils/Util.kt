package com.example.theustimes.utils

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.io.InputStream
import java.util.*
import java.util.Objects.requireNonNull

class Util {
    var TAG = Util::class.java.name

    fun getArticleID(url: String): String {
        return url.replace("/", "-")
    }

    fun getStringFromRawResource(context: Context?, resourceId: Int): String {
        requireNonNull(context)

        var stream: InputStream? = null
        try {
            stream = context!!.resources.openRawResource(resourceId)
            val s = Scanner(stream).useDelimiter("\\A")
            return if (s.hasNext()) s.next() else ""
        } finally {
            if (null != stream) {
                try {
                    stream.close()
                } catch (e: IOException) {
                    Log.e(TAG, e.message.toString())
                }
            }
        }
    }

    fun getJSONObjectFromRawResource(context: Context?, resourceId: Int): JSONObject {
        requireNonNull(context)
        val s: String = getStringFromRawResource(context, resourceId)
        try {
            return JSONObject(s)
        } catch (e: JSONException) {
            Log.e(TAG, e.message.toString())
        }
        return JSONObject()
    }


    fun <T : Any> convertJsonToObject(json: String, classOnject: Class<T>): T {
        val gson = Gson()
        val classData = gson.fromJson(json, classOnject) as T
        return classData
    }

}