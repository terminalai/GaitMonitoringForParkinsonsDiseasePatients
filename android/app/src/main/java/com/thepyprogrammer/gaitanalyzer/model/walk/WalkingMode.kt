package com.thepyprogrammer.gaitanalyzer.model.walk

import android.os.AsyncTask

class WalkingMode() : AsyncTask<Double, Void, DoubleArray>() {
    override fun onPreExecute() {

    }

    override fun doInBackground(vararg params: Double?): DoubleArray {
        TODO("Not yet implemented")
    }

    override fun onProgressUpdate(vararg values: Void?) {
        super.onProgressUpdate(*values)
    }

    override fun onPostExecute(result: DoubleArray) {

    }