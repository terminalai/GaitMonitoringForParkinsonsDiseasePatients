package com.thepyprogrammer.gaitanalyzer.ui.main.home

import android.hardware.SensorManager
import android.os.AsyncTask
import androidx.lifecycle.ViewModelProvider
import com.thepyprogrammer.androidlib.listener.SensorChangeListener
import com.thepyprogrammer.ktlib.gait.FallDetection
import com.thepyprogrammer.gaitanalyzer.ui.MainActivity
import com.thepyprogrammer.ktlib.array.Vector
import toVector

class WalkingMode(val activity: MainActivity, val fall: () -> (Unit)) : AsyncTask<Double, Void, DoubleArray>() {
    var acc: Vector = Vector()
    private var gyro: Vector = Vector()
    lateinit var homeViewModel: HomeViewModel



    private lateinit var detector: FallDetection

    override fun onPreExecute() {
        homeViewModel =
            ViewModelProvider(activity).get(HomeViewModel::class.java)
        activity.setScreenOn()

        activity.accListener = SensorChangeListener { event ->
            acc = event.values.toVector()
        }

        activity.gyroListener = SensorChangeListener { event ->
            gyro = event.values.toVector()
        }

        activity.sensorManager.registerListener(activity.accListener, activity.accelerometer, SensorManager.SENSOR_DELAY_FASTEST)
        activity.sensorManager.registerListener(activity.gyroListener,activity.gyroscope, SensorManager.SENSOR_DELAY_FASTEST)

        detector = FallDetection(acc, gyro)

    }

    override fun doInBackground(vararg params: Double?): DoubleArray {
        while(true) {
            try {
                val time = System.currentTimeMillis()
                homeViewModel.accs.value?.set(time, acc)
                homeViewModel.gyros.value?.set(time, gyro)

                val response = detector.feed(acc, gyro)

                if (response == "FALL DETECTED") {
                    fall()
                    cancel(true)
                } else if (response == "FOG DETECTED") {
                    homeViewModel.freezes.value?.add(time)
                }

                Thread.sleep(10)
            } catch (e: InterruptedException) {}
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