package org.vangelnum.chatapplication

import kotlinx.browser.window
import org.w3c.dom.events.Event

internal actual fun openUrl(url: String?) {
    url?.let { window.open(it) }
}


@JsModule("C:\\Users\\vangel\\AndroidStudioProjects\\chat_application\\composeApp\\src\\jsMain\\resources\\voiceToText.js")
@JsNonModule
external object VoiceToText {
    fun startListening()
    fun stopListening()
}

actual class VoiceToTextParser actual constructor() {
    actual fun startListening() {
        VoiceToText.startListening()
    }

    actual fun stopListening() {
        VoiceToText.stopListening()
    }
}