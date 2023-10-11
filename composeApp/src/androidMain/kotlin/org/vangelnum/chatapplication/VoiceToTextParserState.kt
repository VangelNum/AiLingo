package org.vangelnum.chatapplication

data class VoiceToTextParserState(
    var spokenText: String = "",
    val isSpeaking: Boolean = false,
    val error: String? = null
)