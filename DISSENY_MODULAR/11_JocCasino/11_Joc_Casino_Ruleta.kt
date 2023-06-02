import kotlin.random.Random

class Player(val nombre: String, var credito: Double)

class Ruleta {
    val lastnums = mutableListOf<Int>()

    fun spin(bets: Array<Pair<Int, Double>>): Double {
        val random = Random(System.currentTimeMillis())
        var randlimit = random.nextInt(50, 201)
        var i = 0
        val n = (0..36).toList().toTypedArray()
        var numeroPremiado = 0

        while (randlimit > 0) {
            Thread.sleep(200)
            clear()
            val animacion = buildString {
                append("${n[0 + i].toString().padStart(2, '0')} | ${n[1 + i].toString().padStart(2, '0')} | ${n[2 + i].toString().padStart(2, '0')} | [[ ${n[3 + i].toString().padStart(2, '0')} ]] | ${n[4 + i].toString().padStart(2, '0')} | ${n[5 + i].toString().padStart(2, '0')} | ${n[6 + i].toString().padStart(2, '0')}\n")
            }
            println(animacion)

            numeroPremiado = n[3 + i].toInt()

            i++
            randlimit -= Random.nextInt(4, 11)
        }

        println("Número premiado: ${numeroPremiado.toString().padStart(2, '0')}")

        // Guardar el número resultante en `lastnums`
        lastnums.add(numeroPremiado)
        if (lastnums.size > 10) {
            lastnums.removeAt(0)
        }

        // Calcular las recompensas y actualizar el crédito del jugador
        val recompensa = calcularRecompensa(numeroPremiado, bets)
        return recompensa
    }

    private fun calcularRecompensa(numero: Int, bets: Array<Pair<Int, Double>>): Double {
        var recompensa = 0.0

        for ((tipoApuesta, cantidad) in bets) {
            when (tipoApuesta) {
                222 -> {
                    // Apostar a Par
                    if (numero % 2 == 0 && numero != 0) {
                        recompensa += cantidad + cantidad
                    }
                }

                333 -> {
                    // Apostar a Impar
                    if (numero % 2 != 0) {
                        recompensa += cantidad + cantidad
                    }
                }

                in 1..36 -> {
                    // Apostar a número
                    if (numero == tipoApuesta) {
                        recompensa += cantidad + (cantidad * 35)
                    }
                }

                444 -> {
                    // Apostar a la primera docena (1 al 12)
                    if (numero in 1..12) {
                        recompensa += cantidad + (cantidad * 2)
                    }
                }

                555 -> {
                    // Apostar a la segunda docena (13 al 24)
                    if (numero in 13..24) {
                        recompensa += cantidad + (cantidad * 2)
                    }
                }

                666 -> {
                    // Apostar a la tercera docena (25 al 36)
                    if (numero in 25..36) {
                        recompensa += cantidad + (cantidad * 2)
                    }
                }

                0 -> {
                    // Apostar al 0
                    if (numero == 0) {
                        recompensa += cantidad + (cantidad * 50)
                    }
                }
            }
        }

        return recompensa
    }
}

fun main() {
    clear()
    val ruleta = Ruleta()
    val jugador = Player("Jugador1", 100.0)
    clear()
    while (jugador.credito > 0) {
        println("--------------------------------------------------Creditos actuales: ${jugador.credito}")
        val bets = obtenerApuestas(jugador)
        
        if (bets.isNotEmpty()) {
            val cantidadGanada = ruleta.spin(bets)
            println("Cantidad ganada: $cantidadGanada")
            jugador.credito += cantidadGanada // Restar la cantidad ganada al crédito del jugador
            println("El crédito del jugador es: ${jugador.credito}")
        } else {
            println("No se realizaron apuestas.")
        }
    }
}

fun obtenerApuestas(jugador: Player): Array<Pair<Int, Double>> {
    val bets = mutableListOf<Pair<Int, Double>>()

    println("Menú de apuestas:")
    println("222. Apostar a Par")
    println("333. Apostar a Impar")
    println("1-36. Apostar a número")
    println("444. Apostar a la primera docena (1 al 12)")
    println("555. Apostar a la segunda docena (13 al 24)")
    println("666. Apostar a la tercera docena (25 al 36)")

    var continuarApostando = true
    while (continuarApostando && jugador.credito > 0) {
        print("Ingrese el número de la opción de apuesta (o 999 para dejar de apostar): ")
        val opcionApuesta = readLine()?.toIntOrNull()

        if (opcionApuesta == null || opcionApuesta !in 0..999) {
            println("Opción de apuesta inválida. Inténtelo nuevamente.")
            continue
        }

        if (opcionApuesta == 999) {
            continuarApostando = false
            break
        }

        val cantidadApostada = obtenerCantidadApostada(jugador)
        if (jugador.credito < cantidadApostada) {
            println("No tienes suficientes créditos disponibles para realizar esa apuesta.")
            println("Créditos disponibles: ${jugador.credito}")
            continue
        }

        bets.add(Pair(opcionApuesta, cantidadApostada))
        jugador.credito -= cantidadApostada
    }

    return bets.toTypedArray()
}

fun obtenerCantidadApostada(jugador: Player): Double {
    var cantidadValida = false
    var cantidad = 0.0

    while (!cantidadValida) {
        print("Ingrese la cantidad a apostar: ")
        val input = readLine()

        if (input != null) {
            try {
                cantidad = input.toDouble()
                if (cantidad > 0 && cantidad <= jugador.credito) {
                    cantidadValida = true
                } else {
                    println("La cantidad debe ser mayor a 0 y no puede superar los créditos disponibles.")
                }
            } catch (e: NumberFormatException) {
                println("Cantidad inválida. Inténtelo nuevamente.")
            }
        }
    }

    return cantidad
}

fun clear() {
    val process = ProcessBuilder("cmd", "/c", "cls").inheritIO().start()
    process.waitFor()
}
