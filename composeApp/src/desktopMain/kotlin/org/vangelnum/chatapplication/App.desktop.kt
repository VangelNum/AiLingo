package org.vangelnum.chatapplication

import java.awt.Desktop
import java.net.URI

internal actual fun openUrl(url: String?) {
    val uri = url?.let { URI.create(it) } ?: return
    Desktop.getDesktop().browse(uri)
}

actual class VoiceToTextParser {
    actual fun startListening() {
    }

    actual fun stopListening() {
    }
}