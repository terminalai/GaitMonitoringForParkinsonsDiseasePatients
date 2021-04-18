package com.thepyprogrammer.androidlib.listener

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import java.util.concurrent.ArrayBlockingQueue

class SensorChangeListener(val sensorChange: (SensorEvent) -> (Unit)): SensorEventListener {
    override fun onSensorChanged(event: SensorEvent?) {
        if(event != null) sensorChange(event)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}