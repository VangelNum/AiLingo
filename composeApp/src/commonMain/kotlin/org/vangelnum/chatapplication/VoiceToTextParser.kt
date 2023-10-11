package org.vangelnum.chatapplication

expect class VoiceToTextParser {
    var lastText: String
    var isSpeaking: Boolean
    fun startListening()
    fun stopListening()
}
