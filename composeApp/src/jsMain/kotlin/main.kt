import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import org.jetbrains.skiko.wasm.onWasmReady
import org.vangelnum.chatapplication.App
import org.vangelnum.chatapplication.VoiceToTextParser

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        CanvasBasedWindow("chat_application") {
            val voiceToTextParser by lazy {
                VoiceToTextParser()
            }
            App(
                voiceToTextParser
            )
        }
    }
}
