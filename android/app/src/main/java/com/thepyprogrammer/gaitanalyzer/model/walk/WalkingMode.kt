package com.thepyprogrammer.gaitanalyzer.model.walk

import android.hardware.SensorManager
import android.os.AsyncTask
import com.thepyprogrammer.androidlib.listener.SensorChangeListener
import com.thepyprogrammer.gaitanalyzer.model.walk.fall.FallDetection
import com.thepyprogrammer.gaitanalyzer.ui.MainActivity
import com.thepyprogrammer.ktlib.array.Vector
import toVector

class WalkingMode(val activity: MainActivity, val fall: () -> (Unit)) : AsyncTask<Double, Void, DoubleArray>() {
    private var acc: Vector = Vector()
    private var gyro: Vector = Vector()



    private lateinit var detector: FallDetection

    override fun onPreExecute() {
        activity.setScreenOn()

        activity.accListener = SensorChangeListener { event ->
            acc = event.values.toVector()
        }

        activity.gyroListener = SensorChangeListener { event ->
            gyro = event.values.toVector()
        }

        activity.sensorManager.registerListener(activity.accListener, activity.accelerometer, SensorManager.SENSOR_DELAY_FASTEST)
        activity.sensorManager.registerListener(activity.gyroListener, activity.gyroscope, SensorManager.SENSOR_DELAY_FASTEST)

        detector = FallDetection(acc, gyro)
    }

    override fun doInBackground(vararg params: Double?): DoubleArray {
        while(true) {
            val time = System.currentTimeMillis()
            activity.accs[time] = acc
            activity.gyros[time] = gyro

            val response = detector.feed(acc, gyro)

            if(response == "FALL DETECTED") {
                fall()
                cancel(true)
            } else if(response == "FOG DETECTED") {
                activity.freezes.add(time)
            }

            Thread.sleep(10)
        }
    }

    override fun onProgressUpdate(vararg values: Void?) {
        super.onProgressUpdate(*values)
    }

    override fun onPostExecute(result: DoubleArray) {
        activity.setScreenOff()
        activity.sensorManager.unregisterListener(activity.accListener)
        activity.sensorManager.unregisterListener(activity.gyroListener)
    }
}