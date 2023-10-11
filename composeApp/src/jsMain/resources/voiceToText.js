export function startListening() {
  let recognition = new webkitSpeechRecognition();
  recognition.onresult = function (event) {
    const result = event.results[0][0].transcript;
    // Отправьте результат на сервер или обработайте его по вашему усмотрению
    console.log('Распознанный текст:', result);
  };

  recognition.onerror = function (event) {
    console.error('Ошибка при распознавании речи:', event.error);
  };

  recognition.start();
}

export function stopListening() {
  if (recognition) {
    recognition.stop();
  }
}
