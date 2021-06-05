package com.thepyprogrammer.gaitanalyzeralgos

import com.thepyprogrammer.ktlib.array.Vector
import com.thepyprogrammer.ktlib.gait.Kalman
import kotlin.math.abs
import kotlin.math.atan
import kotlin.math.atan2
import kotlin.math.sqrt

/**
 * Class for Detecting Falls
 * Uses IMU Data to compute a prediction for falls
 */
class FallDetection(private var acc: Vector, private var gyro: Vector = Vector()) {
    companion object {
        var fallCount = 0
        var fogCount = 0
    }

    private var time: Long = System.currentTimeMillis()

    // Source: http://www.freescale.com/files/sensors/doc/app_note/AN3461.pdf eq. 25 and eq. 26
    // atan2 outputs the value of -π to π (radians) - see http://en.wikipedia.org/wiki/Atan2
    // It is then converted from radians to degrees

    private var roll: Double = Math.toDegrees(
        atan(
            acc.y / sqrt(acc.x * acc.x + acc.z * acc.z)
        )
    )
        get() = run {
            val newRoll = Math.toDegrees(
                atan(
                    acc.y / sqrt(acc.x * acc.x + acc.z * acc.z)
                )
            )
            roll = newRoll
            newRoll
        }


    private var pitch = Math.toDegrees(atan2(-acc.x, acc.z))
        get() = run {
            val newPitch = Math.toDegrees(atan2(-acc.x, acc.z))
            pitch = newPitch
            newPitch
        }


    // Angle calculate using the gyro only
    private var gyroAngle: Vector = Vector(roll, pitch)


    // Calculated angle using a complementary filter
    private var compAngle: Vector = Vector(roll, pitch)


    // Calculated angle using a Kalman filter
    private var kalAngle: Vector = Vector(0.0, 0.0)


    private var gyroRate = Vector(gyro.x / 131.0, gyro.y / 131.0)
        get() = run {
            val newGyroRate = Vector(gyro.x / 131.0, gyro.y / 131.0)  // Convert to deg/s
            gyroRate = newGyroRate
            newGyroRate
        }


    // Create the Kalman instances
    private val kalmanX = Kalman(roll)
    private val kalmanY = Kalman(pitch)


    fun feed(acc: Vector, gyro: Vector, newTime: Long = System.currentTimeMillis()): String {
        this.acc = acc
        this.gyro = gyro

        val dt = (newTime - time).toDouble() / 1000000 // Calculate delta time
        time = newTime


        // This fixes the transition problem when the accelerometer angle jumps between -180 and 180 degrees
        if ((pitch < -90 && kalAngle.y > 90) || (pitch > 90 && kalAngle.y < -90)) {
            kalmanY.angle = pitch
            compAngle.y = pitch
            kalAngle.y = pitch
            gyroAngle.y = pitch
        } else
            kalAngle.y = kalmanY.getAngle(
                pitch,
                gyroRate.y,
                dt
            )  // Calculate the angle using a Kalman filter


        // Invert rate, so it fits the restriced accelerometer reading
        if (abs(kalAngle.y) > 90) gyroRate.x = -gyroRate.x


        // Calculate the angle using a Kalman filter
        kalAngle.x = kalmanX.getAngle(roll, gyroRate.x, dt)


        // Calculate gyro angle without any filter
        gyroAngle.x += gyroRate.x * dt
        gyroAngle.y += gyroRate.y * dt


        // Calculate the angle using a Complimentary filter
        compAngle.x = 0.93 * (compAngle.x + gyroRate.x * dt) + 0.07 * roll
        compAngle.y = 0.93 * (compAngle.y + gyroRate.y * dt) + 0.07 * pitch


        // Reset the gyro angle when it has drifted too much
        if (gyroAngle.x < -180 || gyroAngle.x > 180) gyroAngle.x = kalAngle.x
        if (gyroAngle.y < -180 || gyroAngle.y > 180) gyroAngle.y = kalAngle.y

        // Predict Fall
        if (kalAngle.x > 135 || kalAngle.x < 35 || kalAngle.y < -50 || kalAngle.y > 50)
            fallCount++
        else fallCount = 0

        // Predict FoG
        if (kalAngle.y > -5 && kalAngle.y < 5 && kalAngle.x > 115)
            fogCount++
        else fogCount = 0

        if (fallCount == 50) return "FALL"
        if (fogCount == 50) return "FOG"
        return ""
    }


}