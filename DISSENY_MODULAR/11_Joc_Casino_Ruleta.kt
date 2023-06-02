import kotlin.concurrent.thread
import kotlin.random.Random

fun spinWheel() {
    val minNumber = 0 // El número mínimo en la ruleta
    val maxNumber = 36 // El número máximo en la ruleta
    val totalFrames = 10 // Cantidad de frames en la animación
    val delayMillis = 200 // Retardo entre cada frame en milisegundos

    var currentNumber = Random.nextInt(minNumber, maxNumber + 1)

    for (frame in 1..totalFrames) {
        printFrame(currentNumber)

        // Simula el retardo entre frames
        Thread.sleep(delayMillis)

        // Calcula el siguiente número en el frame siguiente
        currentNumber = (currentNumber + 1) % (maxNumber + 1)
    }

    println("El número ganador es: ${formatNumber(currentNumber)}")
}

fun printFrame(currentNumber: Int) {
    val frameSize = 9 // Tamaño del frame en la consola

    for (number in 0..36) {
        if (number == currentNumber) {
            print("[${formatNumber(number)}] | ")
        } else {
            print("${formatNumber(number)} | ")
        }

        // Añade un salto de línea después de cada frameSize números
        if (number % frameSize == frameSize - 1) {
            println()
        }
    }

    println("\n")
}

fun formatNumber(number: Int): String {
    return String.format("%02d", number)
}

// Ejemplo de uso
fun main() {
    spinWheel()
}
