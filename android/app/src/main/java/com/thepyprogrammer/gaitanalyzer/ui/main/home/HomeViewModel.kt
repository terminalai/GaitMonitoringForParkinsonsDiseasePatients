package com.thepyprogrammer.gaitanalyzer.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thepyprogrammer.ktlib.array.Vector
import com.thepyprogrammer.ktlib.io.KFile
import java.io.File

class HomeViewModel : ViewModel() {
    val isWalkMode = MutableLiveData(false)

    val accs = MutableLiveData(hashMapOf<Long, Vector>())
    val gyros = MutableLiveData(hashMapOf<Long, Vector>())
    val freezes = MutableLiveData(mutableListOf<Long>())
    val task = MutableLiveData<WalkingMode>(null)

    val filesDir = MutableLiveData<File>(null)

    private fun save() {
        // save accs
        val accsFile = File(filesDir.value, "accs.txt")
        if (!accsFile.exists()) accsFile.createNewFile()
        val accsWriter = KFile.append(accsFile)
        accs.value?.forEach { (time, acc) ->
            accsWriter.out?.println("$time\t$acc")
        }
        accsWriter.close()

        // save gyros
        val gyrosFile = File(filesDir.value, "gyros.txt")
        if (!gyrosFile.exists()) gyrosFile.createNewFile()
        val gyrosWriter = KFile.append(gyrosFile)
        gyros.value?.forEach { (time, gyro) ->
            gyrosWriter.out?.println("$time\t$gyro")
        }
        gyrosWriter.close()

        // save freezes
        val freezesFile = File(filesDir.value, "freezes.txt")
        if (!freezesFile.exists()) freezesFile.createNewFile()
        val freezesWriter = KFile.append(freezesFile)
        freezes.value?.forEach { time ->
            freezesWriter.out?.println(time)
        }
        freezesWriter.close()


    }


    override fun onCleared() {
        super.onCleared()

        save()


    }
}