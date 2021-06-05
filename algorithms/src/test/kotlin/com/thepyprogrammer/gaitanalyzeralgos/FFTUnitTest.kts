package com.thepyprogrammer.gaitanalyzeralgos

import com.thepyprogrammer.gaitanalyzeralgos.signalprocessing.Complex
import com.thepyprogrammer.gaitanalyzeralgos.signalprocessing.FFT
import kotlin.math.sqrt

val arr = arrayOf(Complex(0.0), Complex(0.5), Complex(sqrt(3.0)/2), Complex(1.0), Complex(sqrt(3.0)/2), Complex(0.5), Complex(.0), Complex(-0.5)) // y = sin(pi*t/3)

val fft = FFT.fft(arr)

println(fft.joinToString(separator = ",\n ", prefix="{\n ", postfix="\n}"))

// Answer for the code:
//{
// 3.232,
// -1.927 + 1.927i,
// 0.500i,
// 0.195 + 0.195i,
// 0.232,
// 0.195 - 0.195i,
// -0.000 - 0.500i,
// -1.927 - 1.927i
//}


// Answer according to Python:
// array([ 3.23205081-0.j        , -1.92668558-1.92668558j,
//        0.        -0.5j       ,  0.19463477-0.19463477j,
//        0.23205081-0.j        ,  0.19463477+0.19463477j,
//        0.        +0.5j       , -1.92668558+1.92668558j])