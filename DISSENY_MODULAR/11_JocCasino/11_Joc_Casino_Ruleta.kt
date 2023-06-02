class Player(val nombre: String, var credito: Int)

class Ruleta {
    val lastnums = mutableListOf<Int>()

    fun spin(bets: Array<Int>): Int {
        // Realizar la animación de la ruleta y determinar el número resultante
        var numero = 0 // Número resultante después de la animación

        // Restar cantidades aleatorias a 100 hasta llegar a 0 o menos
        while (numero >= 0) {
            // Realizar un frame de la animación
            printAnimation(numero)

            // Restar una cantidad aleatoria a `numero` (de 0 a 10)
            val resta = (0..10).random()
            numero -= resta
            if (numero < 0) {
                numero = 0
            }

            // Hacer una pausa para simular la animación
            Thread.sleep(1000)
        }

        // Guardar el número resultante en `lastnums`
        lastnums.add(numero)
        if (lastnums.size > 10) {
            lastnums.removeAt(0)
        }

        // Calcular las recompensas y actualizar el crédito del jugador
        val recompensa = calcularRecompensa(numero, bets)
        return recompensa
    }

    private fun printAnimation(numero: Int) {
        // Imprimir la animación en la terminal
        val numeros = listOf("00", "01", "02", "03", "04", "05", "06", "07", "08", "09") + (10..36).map { it.toString() }
        val animacion = buildString {
            for (i in 0 until 9) {
                val index = (numero + i) % numeros.size
                append("${numeros[index]} | ")
            }
            append("[ ${numeros[numero]} ] | ")
            for (i in 1..8) {
                val index = (numero + i) % numeros.size
                append("${numeros[index]} | ")
            }
            append("${numeros[(numero + 9) % numeros.size]}")
        }
        println(animacion)
    }

    private fun calcularRecompensa(numero: Int, bets: Array<Int>): Int {
        var recompensa = 0

        val numerosApostados = bets.sliceArray(8 until bets.size)
        val paresApostados = bets[0]
        val imparesApostados = bets[1]
        val octavosApostados = bets.sliceArray(2..7)

        // Calcular recompensas para números específicos
        for (numeroApostado in numerosApostados) {
            if (numeroApostado == numero) {
                recompensa += 35 * numeroApostado
            }
        }

        // Calcular recompensas para apuestas pares e impares
        if (numero == 0) {
            recompensa += 50 * paresApostados
        } else if (numero % 2 == 0) {
            recompensa += paresApostados
        } else {
            recompensa += imparesApostados
        }

        // Calcular recompensas para apuestas en octavos
        val octavoIndex = (numero - 1) / 4
        recompensa += 2 * octavosApostados[octavoIndex]

        return recompensa
    }
}

fun main() {
    val ruleta = Ruleta()
    val jugador = Player("Jugador1", 100)

    val bets = obtenerApuestas()

    val numeroResultante = ruleta.spin(bets)
    println("El número resultante es: $numeroResultante")
    println("El crédito del jugador es: ${jugador.credito}")
}

fun obtenerApuestas(): Array<Int> {
    val numerosApostados = intArrayOf(1, 5, 9, 12, 18, 22, 29, 32)
    val paresApostados = 5
    val imparesApostados = 2
    val octavosApostados = intArrayOf(1, 0, 0, 0, 1, 0, 1)

    return arrayOf(paresApostados, imparesApostados) + octavosApostados.toList() + numerosApostados.toList()
}
