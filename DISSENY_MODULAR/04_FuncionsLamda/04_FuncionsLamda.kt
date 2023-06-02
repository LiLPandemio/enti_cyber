import kotlin.math.pow

fun main() {
    // Ej 1
        // val sentencia = "Hola, cómo estás?".toList()
        // val resultado = dialectalOf(sentencia, 'U')
        // val sentenciaModificada = resultado.toCharArray().concatToString()
        // println(sentenciaModificada)
    // Ej 2
        // val lista = listOf(1, 2, 3, 4)
        // val resultado = productOfPairs(lista)
    // Ej 3
        // val numbers = listOf(2, 3, 4, 5)
        // val result = powers(numbers)
        // println(result) // Imprime: [1, 3, 16, 625]

    // Ej 4
        // val llista1 = listOf(1, 2, 3)
        // val llista2 = listOf(1, 2, 3)
        // val llista3 = listOf(1, 2, 4)

        // val resultat1 = equals(llista1, llista2)
        // println(resultat1) // Imprimeix: true

        // val resultat2 = equals(llista1, llista3)
        // println(resultat2) // Imprimeix: false

    // Ej 5
        // val llista1 = listOf(1, 2, 3)
        // val llista2 = listOf(3, 2, 1)
        // val llista3 = listOf(1, 2, 2, 3)
        
        // val resultat1 = similar(llista1, llista2)
        // println(resultat1) // Imprimeix: true
        
        // val resultat2 = similar(llista1, llista3)
        // println(resultat2) // Imprimeix: true

    // Ej 6
        // val lista1 = listOf(1, 1, 2, 2)
        // val lista2 = listOf(10, 11, 12, 13)

        // val resultado = sumLists(lista1, lista2)
        // println(resultado) // Imprime: [11, 12, 14, 15]
    
    // Ej 7
        // val listaOrdenada = listOf(1, 2, 3, 6)
        // val nuevoNumero = 4

        // val posicionInsercion = findInsert(listaOrdenada, nuevoNumero)
        // println(posicionInsercion) // Imprime: 3

    // Ej 8

        // val suma: (Int, Int) -> Double = { x, y -> (x + y).toDouble() }
        // val resta: (Int, Int) -> Double = { x, y -> (x - y).toDouble() }
        // val producto: (Int, Int) -> Double = { x, y -> (x * y).toDouble() }

        // val resultado1 = anonymousMathOperation(5, 3, suma)
        // println(resultado1) // Imprime: 8.0

        // val resultado2 = anonymousMathOperation(10, 4, resta)
        // println(resultado2) // Imprime: 6.0

        // val resultado3 = anonymousMathOperation(2, 6, producto)
        // println(resultado3) // Imprime: 12.0

    // Ej 9

        // val powers = powersOf2(65.0)
        // println(powers) // Imprime: [1.0, 2.0, 4.0, 8.0, 16.0, 32.0, 64.0]
    
    
}

fun dialectalEOf(sentencia: List<Char>): List<Char> {
    val resultado = sentencia.map { if (it in "aeiouáéíóúàèòïüAEIOUÁÉÍÓÚÀÈÒÏÜ") 'e' else it }
    return resultado
}

fun dialectalOf(sentencia: List<Char>, vocalReemplazo: Char): List<Char> {
    val resultado = sentencia.map { if (it in "aeiouáéíóúàèòïüAEIOUÁÉÍÓÚÀÈÒÏÜ") vocalReemplazo else it }
    return resultado
}

fun productOfPairs(list: List<Int>): Double {
    return list.filter { it % 2 == 0 }.fold(1.0) { acc, num -> acc * num }
}

fun powers(numbers: List<Int>?): List<Int>? {
    return numbers?.mapIndexed { index, number -> number.toDouble().pow(index.toDouble()).toInt() }
}

fun equals(l1: List<*>, l2: List<*>): Boolean {
    return l1.size == l2.size && l1.zip(l2).all { (a, b) -> a == b }
}

fun similar(l1: List<*>, l2: List<*>): Boolean {
    return l1.size == l2.size && l1.toSet() == l2.toSet()
}

fun sumLists(l1: List<Int>, l2: List<Int>): List<Int> {
    require(l1.size == l2.size) { "Las listas deben tener la misma longitud" }
    return l1.zip(l2).map { (a, b) -> a + b }
}

fun findInsert(orderedList: List<Int>, newNumber: Int): Int {
    return orderedList.indexOfFirst { it > newNumber }
}

fun anonymousMathOperation(a: Int, b: Int, operation: (Int, Int) -> Double): Double {
    return operation(a, b)
}

fun powersOf2(limit: Double): List<Double> {
    return generateSequence(1.0) { it * 2 }.takeWhile { it <= limit }.toList()
}
