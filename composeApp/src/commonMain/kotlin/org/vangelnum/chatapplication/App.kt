package org.vangelnum.chatapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import compose.icons.FeatherIcons
import compose.icons.feathericons.Mic
import compose.icons.feathericons.MicOff
import compose.icons.feathericons.Send
import org.vangelnum.chatapplication.theme.AppTheme

@Composable
internal fun App(voiceToTextParser: VoiceToTextParser) = AppTheme {
    ChatScreen(voiceToTextParser)
}


@Composable
fun ChatScreen(voiceToTextParser: VoiceToTextParser) {

    Scaffold(floatingActionButton = {

    }) { padding ->
        Row(
            modifier = Modifier.fillMaxSize().padding(8.dp).padding(bottom = 32.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = voiceToTextParser.lastText,
                onValueChange = {

                },
                trailingIcon = {
                    Row {
                        IconButton(onClick = {
                            if (voiceToTextParser.isSpeaking) {
                                voiceToTextParser.stopListening()
                            } else {
                                voiceToTextParser.startListening()
                            }
                        }) {
                            Icon(
                                imageVector = if (voiceToTextParser.isSpeaking) FeatherIcons.Mic else FeatherIcons.MicOff,
                                contentDescription = null
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(onClick = {

                        }) {
                            Icon(imageVector = FeatherIcons.Send, contentDescription = null)
                        }
                    }
                }
            )
        }

    }
}

internal expect fun openUrl(url: String?)




