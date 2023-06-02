import java.io.*

fun main() {
    // Ej 1
    //      escribirTablasDeMultiplicar()

    // Ej 2
    //      var tablas = leerTablasDeMultiplicar()
    
    // Ej 3
    //      val texto = leerTextoDeArchivo()
    //      val textoInvertido = invertirFrases(texto)
    //      escribirTextoEnArchivo(textoInvertido)
    //      println("El proceso se ha completado. El texto invertido se ha guardado en el archivo 'text.out'.")

    // Ej 4
    //      val subObjeto = SubObjeto("Texto del subobjeto")
    //      val objeto = ObjetoPrincipal(42, listOf("Elemento 1", "Elemento 2"), subObjeto)
   
    //      escribirObjetoEnArchivo(objeto)
    //      println("El objeto se ha escrito correctamente en el archivo 'myFirstDataFile.dat'.")

    // Ej 5
    //      val subObjeto = SubObjeto("Texto del subobjeto")
    //      val objeto = ObjetoPrincipal(42, listOf("Elemento 1", "Elemento 2"), subObjeto)

    //      escribirObjetoEnArchivo(objeto)
    //      println("El objeto se ha escrito correctamente en el archivo 'myFirstDataFile.dat'.")

    //      val objetoLeido = leerObjetoDeArchivo()
    //      if (objetoLeido != null) {
    //          println("Objeto leído del archivo:")
    //          println("Número: ${objetoLeido.numero}")
    //          println("Lista: ${objetoLeido.lista}")
    //          println("Subobjeto: ${objetoLeido.subObjeto}")
    //      }

    // Ej 6
    
    var continuar = true

    while (continuar) {
        println("******************************")
        println("         MENÚ PRINCIPAL        ")
        println("******************************")
        println("1. Escribir DNIS")
        println("2. Añadir DNI al final")
        println("3. Comprobar DNI")
        println("4. Salir")
        println("******************************")

        print("Ingrese el número de opción: ")
        val opcion = readLine()?.toIntOrNull()

        when (opcion) {
            1 -> {
                println("Opción 1 seleccionada: Escribir DNIS")
                // Llamar a la función correspondiente
                escribirDNIsEnArchivo(solicitarDNIs(), "ENTRADAS.txt")
            }
            2 -> {
                println("Opción 2 seleccionada: Añadir DNI al final")
                // Llamar a la función correspondiente
                var dni: String? = null
                print("Ingrese un DNI: ")
                dni = readLine()
                appendDNI(dni?: "", "ENTRADAS.TXT")
            }
            3 -> {
                println("Opción 3 seleccionada: Comprobar DNI")
                // Llamar a la función correspondiente
                
                // Leer los DNIs desde un archivo
                val dnis = leerDNIsDeArchivo("ENTRADAS.txt")

                // Solicitar al usuario qué acción desea realizar
                println("¿Qué acción desea realizar?")
                println("1. Buscar un DNI")
                println("2. Ver la lista completa de DNIs")
                print("Ingrese el número de opción: ")
                val opcion = readLine()?.toIntOrNull()

                when (opcion) {
                    1 -> {
                        // Buscar un DNI
                        print("Ingrese el DNI a buscar: ")
                        val dniBuscado = readLine()
                        if (dniBuscado != null) {
                            if (dnis.contains(dniBuscado)) {
                                println("El DNI '$dniBuscado' se encuentra en la lista.")
                            } else {
                                println("El DNI '$dniBuscado' no se encuentra en la lista.")
                            }
                        } else {
                            println("DNI ingresado inválido.")
                        }
                    }
                    2 -> {
                        // Ver la lista completa de DNIs
                        println("Lista de DNIs:")
                        dnis.forEach { println(it) }
                    }
                    else -> {
                        println("Opción inválida.")
                    }
                }
            }
            4 -> {
                println("Opción 4 seleccionada: Salir")
                continuar = false
            }
            else -> {
                println("Opción inválida. Inténtelo nuevamente.")
            }
        }
    }
}

fun escribirTablasDeMultiplicar() {
    val archivo = File("tablas.txt")

    // Abrir el archivo en modo escritura
    val writer = archivo.bufferedWriter()

    try {
        for (i in 1..10) {
            writer.write("Tabla del $i:\n")
            writer.write("-----------------\n")
            for (j in 1..10) {
                val resultado = i * j
                writer.write("$i x $j = $resultado\n")
            }
            writer.write("\n")
        }

        println("Las tablas de multiplicar se han escrito correctamente en el archivo 'tablas.txt'.")
    } catch (e: Exception) {
        println("Ocurrió un error al escribir en el archivo: ${e.message}")
    } finally {
        // Cerrar el archivo
        writer.close()
    }
}

fun leerTablasDeMultiplicar(): List<List<String>> {
    val archivo = File("tablas.txt")
    val tablas: MutableList<MutableList<String>> = mutableListOf()

    try {
        // Abrir el archivo en modo lectura
        val reader = archivo.bufferedReader()

        var tabla: MutableList<String> = mutableListOf()
        var linea = reader.readLine()

        while (linea != null) {
            if (linea.startsWith("Tabla del")) {
                if (tabla.isNotEmpty()) {
                    tablas.add(tabla)
                }
                tabla = mutableListOf()
            }

            tabla.add(linea)

            linea = reader.readLine()
        }

        // Agregar la última tabla
        if (tabla.isNotEmpty()) {
            tablas.add(tabla)
        }

        reader.close()
    } catch (e: Exception) {
        println("Ocurrió un error al leer el archivo: ${e.message}")
    }

    return tablas
}


fun leerTextoDeArchivo(): String {
    val archivo = File("text.inp")
    var texto = ""

    try {
        // Abrir el archivo en modo lectura
        val reader = archivo.bufferedReader()

        texto = reader.readText()

        reader.close()
    } catch (e: Exception) {
        println("Ocurrió un error al leer el archivo: ${e.message}")
    }

    return texto
}

fun invertirFrases(texto: String): String {
    val frases = texto.split(". ")

    val frasesInvertidas = frases.map { frase ->
        val palabras = frase.split(" ")
        val fraseInvertida = palabras.joinToString(" ") { palabra -> palabra.reversed() }
        fraseInvertida
    }

    return frasesInvertidas.joinToString(". ")
}

fun escribirTextoEnArchivo(texto: String) {
    val archivo = File("text.out")

    try {
        // Abrir el archivo en modo escritura
        val writer = archivo.bufferedWriter()

        writer.write(texto)

        writer.close()
    } catch (e: Exception) {
        println("Ocurrió un error al escribir en el archivo: ${e.message}")
    }
}

data class SubObjeto(val texto: String)

data class ObjetoPrincipal(val numero: Int, val lista: List<String>, val subObjeto: SubObjeto)

fun escribirObjetoEnArchivo(objeto: ObjetoPrincipal) {
    val archivo = File("myFirstDataFile.dat")

    try {
        // Abrir el archivo en modo escritura
        val outputStream = ObjectOutputStream(FileOutputStream(archivo))

        // Escribir el objeto en el archivo
        outputStream.writeObject(objeto)

        outputStream.close()
    } catch (e: Exception) {
        println("Ocurrió un error al escribir en el archivo: ${e.message}")
    }
}


fun leerObjetoDeArchivo(): ObjetoPrincipal? {
    val archivo = File("myFirstDataFile.dat")

    try {
        // Abrir el archivo en modo lectura
        val inputStream = ObjectInputStream(FileInputStream(archivo))

        // Leer el objeto desde el archivo
        val objeto = inputStream.readObject() as ObjetoPrincipal

        inputStream.close()

        return objeto
    } catch (e: Exception) {
        println("Ocurrió un error al leer el archivo: ${e.message}")
    }

    return null
}

// Función para escribir una lista de DNIs en un archivo
fun escribirDNIsEnArchivo(dnis: List<String>, nombreArchivo: String) {
    try {
        val archivo = File(nombreArchivo)
        archivo.writeText(dnis.joinToString("\n"))
        println("La lista de DNIs se ha escrito correctamente en el archivo '$nombreArchivo'.")
    } catch (e: Exception) {
        println("Ocurrió un error al escribir en el archivo: ${e.message}")
    }
}

// Función para leer una lista de DNIs desde un archivo
fun leerDNIsDeArchivo(nombreArchivo: String): List<String> {
    val dnis = mutableListOf<String>()
    try {
        val archivo = File(nombreArchivo)
        dnis.addAll(archivo.readLines())
    } catch (e: Exception) {
        println("Ocurrió un error al leer el archivo: ${e.message}")
    }
    return dnis
}

// Función para añadir un DNI al archivo
fun appendDNI(dni: String, nombreArchivo: String) {
    try {
        val archivo = File(nombreArchivo)
        val fileWriter = FileWriter(archivo, true)
        fileWriter.write("$dni\n")
        fileWriter.close()
        println("El DNI se ha añadido correctamente al archivo '$nombreArchivo'.")
    } catch (e: Exception) {
        println("Ocurrió un error al añadir el DNI al archivo: ${e.message}")
    }
}

fun solicitarDNIs(): List<String> {
    val dnis = mutableListOf<String>()
    while (true) {
        print("Ingrese un DNI (o '-1' para finalizar): ")
        val dni = readLine()
        if (dni == "-1") {
            break
        }
        dni?.let {
            dnis.add(it)
        }
    }
    return dnis
}