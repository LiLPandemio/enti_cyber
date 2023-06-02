class Player(val nombre: String, var credito: Int)

class Ruleta {
    val lastnums = mutableListOf<Int>()

    fun spin(bets: Array<Int>): Int {
        // Realizar la animación de la ruleta y determinar el número resultante
        var numero = 0 // Número resultante después de la animación

        // Restar cantidades aleatorias a 100 hasta llegar a 0 o menos
        while (numero >= 0) {
            // Realizar un frame de la animación
            // Restar una cantidad aleatoria a `numero` (de 0 a 10)
        }

        // Guardar el número resultante en `lastnums`

        // Calcular las recompensas y actualizar el crédito del jugador

        return numero // Devolver el número resultante
    }
}


fun main() {
    println(spinWheel())
}
