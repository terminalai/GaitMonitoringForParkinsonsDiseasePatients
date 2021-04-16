package com.thepyprogrammer.gaitanalyzer

import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONException
import org.json.JSONObject
import org.junit.Assert
import org.junit.Test


class VolleyUnitTest {
    private fun response(freezeY: Double, freezeZ: Double): String? {
        val url = "https://gait-analyzer-1ac2c.web.app/freeze"
        var rep: String? = ""
        val freeze = JSONObject()
        try {
            freeze.put(
                "freezeY", freezeY
            )
            freeze.put(
                "freezeZ", freezeZ
            )
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        object: JsonObjectRequest(
            Method.POST,
            url, freeze,
            Response.Listener { response ->
                Log.d("JSON RESPONSE", "Request Response: $response")
                try {
                    val botReply = response?.getString("freeze")
                    rep = botReply?.replace("\n", "")
                } catch (e: JSONException) {
                    // JSON error
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error ->
                Log.e("url", url)
                Log.e("ON ERROR RESPONSE", "Request Error: " + error.message)
            }

        ) {}

        return rep
    }

    @Test
    fun testVolley() {
        val response = response(10.0, 11.0)
        Assert.assertEquals(response, "1.0")
    }
}