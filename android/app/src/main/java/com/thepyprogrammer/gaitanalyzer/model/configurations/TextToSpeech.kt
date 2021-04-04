package com.thepyprogrammer.gaitanalyzer.model.configurations

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*

fun TextToSpeech.getTTS(context: Context?, text: String) =
        TextToSpeech(context) {
            if (it == TextToSpeech.SUCCESS) {
                val result = this.setLanguage(Locale.US)
                if (!(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)) {
                    speak(text)
                }
            }
        }

fun TextToSpeech.speak(text: String) {
    speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
}
