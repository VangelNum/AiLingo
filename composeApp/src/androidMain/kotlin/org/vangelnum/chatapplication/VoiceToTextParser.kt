package org.vangelnum.chatapplication

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

actual class VoiceToTextParser : RecognitionListener {
    private val application: Application
        get() {
            return AndroidApp.INSTANCE
        }
    private val _state = MutableStateFlow(VoiceToTextParserState())
    val state = _state.asStateFlow()
    private val recognizer: SpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(application)

    actual fun startListening() {
        _state.update {
            VoiceToTextParserState()
        }
        if (SpeechRecognizer.isRecognitionAvailable(application)) {
            _state.update {
                it.copy(error = "Recognition is not avialiable")
            }
        }
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ru")
        }
        recognizer.setRecognitionListener(this)
        recognizer.startListening(intent)
        _state.update {
            it.copy(isSpeaking = true)
        }
    }

    actual fun stopListening() {
        _state.update {
            it.copy(isSpeaking = false)
        }
        recognizer.stopListening()
    }

    override fun onReadyForSpeech(params: Bundle?) {
        _state.update {
            it.copy(error = null)
        }
    }

    override fun onBeginningOfSpeech() {
        _state.update {
            isSpeaking = true
            it.copy(isSpeaking = true)
        }
    }

    override fun onRmsChanged(rmsdB: Float) = Unit

    override fun onBufferReceived(buffer: ByteArray?) = Unit

    override fun onEndOfSpeech() {
        _state.update {
            isSpeaking = false
            it.copy(isSpeaking = false)
        }
    }

    override fun onError(error: Int) {
        if (error == SpeechRecognizer.ERROR_CLIENT) {
            return
        }
        _state.update {
            it.copy(error = "Error: $error")
        }
    }

    override fun onResults(results: Bundle?) {
        results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.getOrNull(0)?.let { result ->
            _state.update {
                lastText = result
                it.copy(spokenText = result)
            }
        }
    }

    override fun onPartialResults(partialResults: Bundle?) = Unit

    override fun onEvent(eventType: Int, params: Bundle?) = Unit
    actual var lastText: String
        get() {
            return state.value.spokenText
        }
        set(value) {

        }
    actual var isSpeaking: Boolean
        get()  {
            return state.value.isSpeaking
        }
        set(value) {}
}