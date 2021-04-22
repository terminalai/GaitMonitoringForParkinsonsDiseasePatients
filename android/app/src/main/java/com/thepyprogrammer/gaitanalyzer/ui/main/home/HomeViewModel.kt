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
        if(task.value != null && !task.value!!.isCancelled) task.value!!.cancel(true)

        for((file, iterable) in hashMapOf(
                "accs" to accs.value,
                "gyros" to gyros.value
        )) {
            if (iterable != null) {
                individualSave(file, iterable)
            }
        }

        freezes.value?.let { individualSave("freezes", it) }

    }

    private fun individualSave(filename: String, map: HashMap<Long, Vector>) {
        val file = File(filesDir.value, "$filename.txt")
        if (!file.exists()) file.createNewFile()
        val fileWriter = KFile.append(file)
        for((time, acc) in map) {
            fileWriter.out?.println("$time\t$acc")
        }

        fileWriter.close()

    }

    private fun individualSave(filename: String, list: MutableList<Long>) {
        val file = File(filesDir.value, "$filename.txt")
        if (!file.exists()) file.createNewFile()
        val fileWriter = KFile.append(file)
        list.forEach { time ->
            fileWriter.out?.println(time)
        }
        fileWriter.close()
    }


    override fun onCleared() {
        super.onCleared()

        if(task.value != null && !task.value!!.isCancelled) task.value!!.cancel(true)

        save()
    }
}