package com.thepyprogrammer.gaitanalyzer

import com.thepyprogrammer.gaitanalyzer.model.signalProcessing.Complex
import com.thepyprogrammer.gaitanalyzer.model.signalProcessing.FFT
import org.junit.Assert
import org.junit.Test
import kotlin.math.sqrt

class FFTUnitTest {
    @Test
    fun test_FFT() {
        Assert.assertEquals(4, 2 + 2)

        val arr = arrayOf(Complex(0.0), Complex(0.5), Complex(sqrt(3.0)/2), Complex(1.0), Complex(sqrt(3.0)/2), Complex(0.5), Complex(.0), Complex(-0.5)) // y = sin(pi*t/3)

        val fft = FFT.fft(arr)

        // 3.232,
        // -1.927 + 1.927i,
        // 0.500i,
        // 0.195 + 0.195i,
        // 0.232,
        // 0.195 - 0.195i,
        // -0.000 - 0.500i,
        // -1.927 - 1.927i
        
        Assert.assertEquals(
                """{
                           | 3.232,
                           | -1.927 + 1.927i,
                           | 0.500i,
                           | 0.195 + 0.195i,
                           | 0.232,
                           | 0.195 - 0.195i,
                           | -0.000 - 0.500i,
                           | -1.927 - 1.927i
                           |}""",
                fft.joinToString(separator = ",\n ", prefix="{\n ", postfix="\n}")
        )

    }
}