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
        actualizarCreditoJugador(recompensa)

        return numero // Devolver el número resultante
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
    
        val numerosApostados = bets[8]
        val paresApostados = bets[0]
        val imparesApostados = bets[1]
        val octavosApostados = bets.sliceArray(2..7)
    
        // Calcular recompensas para números específicos
        for (i in numerosApostados.indices) {
            val numeroApostado = numerosApostados[i]
            if (numeroApostado == numero) {
                recompensa += 35 * numeroApostado
            }
        }
    
        // Calcular recompensas para apuestas pares e impares
        if (numero == 0) {
            recompensa += 50 * bets[0]
        } else if (numero % 2 == 0) {
            recompensa += paresApostados
        } else {
            recompensa += imparesApostados
        }
    
        // Calcular recompensas para apuestas en octavos
        val octavoIndex = (numero - 1) / 4
        if (octavosApostados[octavoIndex] == 1) {
            recompensa += 8
        }
    
        return recompensa
    }
    
    private fun actualizarCreditoJugador(recompensa: Int) {
        jugador.credito += recompensa
    }
}

fun main() {
    println(spinWheel())
}
