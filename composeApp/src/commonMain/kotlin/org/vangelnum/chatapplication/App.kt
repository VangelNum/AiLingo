package org.vangelnum.chatapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.vangelnum.chatapplication.theme.AppTheme

@Composable
internal fun App() = AppTheme {
    ChatScreen()
}


@Composable
fun ChatScreen() {
    Column {
        Button(
            onClick = {
                VoiceToTextParser().startListening()
            }
        ) {
            Text("click to record")
        }
        Button(
            onClick = {
                VoiceToTextParser().stopListening()
            }
        ) {
            Text("click to stop")
        }

    }
}

internal expect fun openUrl(url: String?)


expect class VoiceToTextParser() {
    fun startListening()
    fun stopListening()
}