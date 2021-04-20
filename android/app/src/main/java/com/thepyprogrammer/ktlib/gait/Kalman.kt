package com.thepyprogrammer.ktlib.gait

import zeros

// Source: https://github.com/TKJElectronics/KalmanFilter
class Kalman(var angle: Double = 0.0) {
    /* We will set the variables like so, these can also be tuned by the user */
    var Q_angle = 0.001
    var Q_bias = 0.003
    var R_measure = 0.03

    var bias = 0.0 // Reset bias

    // Since we assume that the bias is 0 and we know the starting angle (use setAngle), the error covariance matrix is set like so - see: http://en.wikipedia.org/wiki/Kalman_filter#Example_application.2C_technical
    val P = zeros(2, 2)

    var rate = 0.0

    fun getAngle(newAngle: Double, newRate: Double, dt: Double) = run {
        // KasBot V2  -  Kalman filter module - http://www.x-firm.com/?page_id=145
        // Modified by Kristian Lauszus
        // See my blog post for more information: http://blog.tkjelectronics.dk/2012/09/a-practical-approach-to-kalman-filter-and-how-to-implement-it

        // Discrete Kalman filter time update equations - Time Update ("Predict")
        // Update xhat - Project the state ahead
        /* Step 1 */
        rate = newRate - bias
        angle += dt * rate

        // Update estimation error covariance - Project the error covariance ahead
        /* Step 2 */
        P[0][0] += dt * (dt * P[1][1] - P[0][1] - P[1][0] + Q_angle)
        P[0][1] -= dt * P[1][1]
        P[1][0] -= dt * P[1][1]
        P[1][1] += Q_bias * dt

        // Discrete Kalman filter measurement update equations - Measurement Update ("Correct")
        // Calculate Kalman gain - Compute the Kalman gain
        /* Step 4 */
        val S = P[0][0] + R_measure // Estimate error
        /* Step 5 */
        val K = DoubleArray(2) // Kalman gain - This is a 2x1 vector
        K[0] = P[0][0] / S
        K[1] = P[1][0] / S

        // Calculate angle and bias - Update estimate with measurement zk (newAngle)
        /* Step 3 */
        val y = newAngle - angle // Angle difference
        /* Step 6 */
        angle += K[0] * y
        bias += K[1] * y

        // Calculate estimation error covariance - Update the error covariance
        /* Step 7 */
        val P00_temp = P[0][0]
        val P01_temp = P[0][1]

        P[0][0] -= K[0] * P00_temp
        P[0][1] -= K[0] * P01_temp
        P[1][0] -= K[1] * P00_temp
        P[1][1] -= K[1] * P01_temp

        angle
    }


}