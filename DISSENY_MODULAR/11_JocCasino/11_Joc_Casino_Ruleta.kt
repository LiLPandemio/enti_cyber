import kotlin.random.Random

class Player(val nombre: String, var credito: Double)

class Ruleta {
    val lastnums = mutableListOf<Int>()

    fun spin(bets: Array<Pair<Int, Double>>): Double {
        var randlimit = 100
        var i = 0
        val n = (0..36).toList().toTypedArray()
        var numeroPremiado = 0

        while (randlimit > 0) {
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
                888 -> {
                    // Apostar a Octetos
                    // No es necesario realizar ninguna acción aquí
                }

                222 -> {
                    // Apostar a Par
                    if (numero % 2 == 0 && numero != 0) {
                        recompensa += cantidad
                    }
                }

                333 -> {
                    // Apostar a Impar
                    if (numero % 2 != 0) {
                        recompensa += cantidad
                    }
                }

                in 1..36 -> {
                    // Apostar a número
                    if (numero == tipoApuesta) {
                        recompensa += cantidad * 35
                    }
                }
            }
        }

        return recompensa
    }


    private fun obtenerOcteto(): IntRange {
        println("Seleccione a qué octeto desea apostar:")
        println("1. Del 1 al 8")
        println("2. Del 9 al 16")
        println("3. Del 17 al 24")
        println("4. Del 25 al 32")
        println("5. Del 33 al 36")

        var seleccionValida = false
        var seleccion = 0

        while (!seleccionValida) {
            val input = readLine()
            if (input != null) {
                try {
                    seleccion = input.toInt()
                    if (seleccion in 1..5) {
                        seleccionValida = true
                    } else {
                        println("Por favor, seleccione una opción válida (1-5).")
                    }
                } catch (e: NumberFormatException) {
                    println("Por favor, ingrese un número válido.")
                }
            }
        }

        return when (seleccion) {
            1 -> 1..8
            2 -> 9..16
            3 -> 17..24
            4 -> 25..32
            else -> 33..36
        }
    }
}

fun main() {
    val ruleta = Ruleta()
    val jugador = Player("Jugador1", 100.0)

    val bets = obtenerApuestas()

    val cantidadGanada = ruleta.spin(bets)
    println("Cantidad ganada: $cantidadGanada")
    jugador.credito += cantidadGanada
    println("El crédito del jugador es: ${jugador.credito}")
}

fun obtenerApuestas(): Array<Pair<Int, Double>> {
    val bets = mutableListOf<Pair<Int, Double>>()

    println("Menú de apuestas:")
    println("888. Apostar a Octetos")
    println("222. Apostar a Par")
    println("333. Apostar a Impar")
    println("1-36. Apostar a número")

    var continuarApostando = true
    while (continuarApostando) {
        print("Ingrese el número de la opción de apuesta (o 0 para dejar de apostar): ")
        val opcionApuesta = readLine()?.toIntOrNull()

        if (opcionApuesta == null || opcionApuesta !in 0..888) {
            println("Opción de apuesta inválida. Inténtelo nuevamente.")
            continue
        }

        if (opcionApuesta == 0) {
            continuarApostando = false
            break
        }

        if (opcionApuesta == 888) {
            val octetoApostado = obtenerOctetoApostado()
            val cantidadApostada = obtenerCantidadApostada()
            bets.add(Pair(opcionApuesta, cantidadApostada))
            bets.add(Pair(octetoApostado, cantidadApostada))
        } else {
            val cantidadApostada = obtenerCantidadApostada()
            bets.add(Pair(opcionApuesta, cantidadApostada))
        }
    }

    return bets.toTypedArray()
}

fun obtenerOctetoApostado(): Int {
    println("Seleccione a qué octeto desea apostar:")
    println("1. Del 1 al 8")
    println("2. Del 9 al 16")
    println("3. Del 17 al 24")
    println("4. Del 25 al 32")
    println("5. Del 33 al 36")

    var seleccionValida = false
    var seleccion = 0

    while (!seleccionValida) {
        val input = readLine()
        if (input != null) {
            try {
                seleccion = input.toInt()
                if (seleccion in 1..5) {
                    seleccionValida = true
                } else {
                    println("Por favor, seleccione una opción válida (1-5).")
                }
            } catch (e: NumberFormatException) {
                println("Por favor, ingrese un número válido.")
            }
        }
    }

    return seleccion
}


fun obtenerCantidadApostada(): Double {
    var cantidadValida = false
    var cantidad = 0.0

    while (!cantidadValida) {
        print("Ingrese la cantidad a apostar: ")
        val input = readLine()

        if (input != null) {
            try {
                cantidad = input.toDouble()
                if (cantidad > 0) {
                    cantidadValida = true
                } else {
                    println("La cantidad debe ser mayor a 0. Inténtelo nuevamente.")
                }
            } catch (e: NumberFormatException) {
                println("Cantidad inválida. Inténtelo nuevamente.")
            }
        }
    }

    return cantidad
}
