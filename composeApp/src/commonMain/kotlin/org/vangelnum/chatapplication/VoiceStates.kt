package org.vangelnum.chatapplication

data class VoiceStates(
    var spokenText: String = "",
    val isSpeaking: Boolean = false,
    val error: String? = null
)