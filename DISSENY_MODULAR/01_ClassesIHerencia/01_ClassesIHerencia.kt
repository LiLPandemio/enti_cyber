import kotlin.math.pow

open class CuerpoCeleste(var distanciaAlSol: Double, var posicionEnElCielo: String, var esVisibleALaVistaDesnuda: Boolean) {
    open fun imprimirNombre() {
        println("Nombre del cuerpo celeste")
    }
}

class AgujeroNegro(
    distanciaAlSol: Double,
    posicionEnElCielo: String,
    esVisibleALaVistaDesnuda: Boolean,
    var velocidadDeRotacion: Double,
    var masa: Double,
    var esPrimitivo: Boolean
) : CuerpoCeleste(distanciaAlSol, posicionEnElCielo, esVisibleALaVistaDesnuda) {
    override fun imprimirNombre() {
        println("Agujero negro")
    }

    // Soy aficionado a las teorías de agujeros negros y de gusanos. ¡Este ejercicio me ha gustado! :)
    fun calcularRadioDeSchwarzschild(): Double? {
        if (velocidadDeRotacion == 0.0) {
            val G = 6.67e-11
            val c = 3e8
            return 2 * G * masa * c.pow(2)
        }
        return null
    }
}

class Galaxia(
    distanciaAlSol: Double,
    posicionEnElCielo: String,
    esVisibleALaVistaDesnuda: Boolean,
    var luminosidad: Double,
    var cuerposCelestes: Int,
    var tipo: String
) : CuerpoCeleste(distanciaAlSol, posicionEnElCielo, esVisibleALaVistaDesnuda) {

    init {
        require(tipo in listOf("Elíptica", "Espiral", "Enana", "Irregular")) {
            "Tipo de galaxia no válido: $tipo"
        }
    }

    override fun imprimirNombre() {
        println("Tipo de galaxia: $tipo")
        super.imprimirNombre()
    }
}

class Estrella(
    distanciaAlSol: Double,
    posicionEnElCielo: String,
    esVisibleALaVistaDesnuda: Boolean,
    var luminosidad: Double,
    var temperatura: Double,
    var claseEspectralEstrella: String
) : CuerpoCeleste(distanciaAlSol, posicionEnElCielo, esVisibleALaVistaDesnuda) {
    var perteneceA: Galaxia? = null

    val claseEspectral: String
        get() = calcularClaseEspectral()

    private fun calcularClaseEspectral(): String {
        return when {
            temperatura >= 30000 -> "O"
            temperatura >= 10000 -> "B"
            temperatura >= 7500 -> "A"
            temperatura >= 6000 -> "F"
            temperatura >= 5200 -> "G"
            temperatura >= 3700 -> "K"
            else -> "M"
        }
    }

    override fun imprimirNombre() {
        println("Clase espectral de la estrella: $claseEspectral")
        super.imprimirNombre()
    }
}

class Planeta(val masa: Double, val esHabitable: Boolean, val tieneAtmosfera: Boolean, val lunas: List<Luna>, val estrella: Estrella?)

class Luna(val masa: Double, val esHabitable: Boolean, val tieneAtmosfera: Boolean, val planeta: Planeta)

fun encontrarElMasLejano(cuerposCelestes: List<CuerpoCeleste>): CuerpoCeleste {
    return cuerposCelestes.maxByOrNull { it.distanciaAlSol }
        ?: throw NoSuchElementException("Lista de cuerpos celestes vacía")
}

fun filtrarPorGalaxia(estrellas: List<Estrella?>, galaxia: Galaxia): List<Estrella> {
    return estrellas.filter { it?.perteneceA == galaxia }.mapNotNull { it }
}

object MapaCeleste {
    private val objetosCelestes: MutableList<ObjetoCeleste> = mutableListOf()

    fun agregarObjetoCeleste(objetoCeleste: ObjetoCeleste) {
        objetosCelestes.add(objetoCeleste)
    }

    fun imprimirMapa() {
        objetosCelestes.forEach { objetoCeleste ->
            imprimirObjetoCeleste(objetoCeleste, 0)
        }
    }

    private fun imprimirObjetoCeleste(objetoCeleste: ObjetoCeleste, nivelDeIndentacion: Int) {
        val indentacion = "\t".repeat(nivelDeIndentacion)
        println("$indentacion${objetoCeleste.nombre} ${objetoCeleste.informacionAdicional}")
        if (objetoCeleste is ObjetoContenedor) {
            val objetoContenedor = objetoCeleste as ObjetoContenedor
            objetoContenedor.contenido.forEach { contenido ->
                imprimirObjetoCeleste(contenido, nivelDeIndentacion + 1)
            }
        }
    }
}

abstract class ObjetoCeleste(val nombre: String, val informacionAdicional: String)

interface ObjetoContenedor {
    val contenido: List<ObjetoCeleste>
}

class PlanetaCeleste(nombre: String, informacionAdicional: String) : ObjetoCeleste(nombre, informacionAdicional)

class LunaCeleste(nombre: String) : ObjetoCeleste(nombre, "")

class EstrellaCeleste(nombre: String, informacionAdicional: String) : ObjetoCeleste(nombre, informacionAdicional)

class GalaxiaCeleste(nombre: String, informacionAdicional: String) :
    ObjetoCeleste(nombre, informacionAdicional), ObjetoContenedor {
    override val contenido: MutableList<ObjetoCeleste> = mutableListOf()
}

class AgujeroNegroCeleste(nombre: String) : ObjetoCeleste(nombre, "(agujero negro)")

class EjemploMapaCeleste {
    fun crearMapaCeleste() {
        val mapaCeleste = MapaCeleste

        val viaLactea = GalaxiaCeleste("Vía Láctea", "")
        val andromeda = GalaxiaCeleste("Andrómeda", "")

        val sagitarioAStar = AgujeroNegroCeleste("Sagitario A*")
        val alphaCentauri = EstrellaCeleste("Alpha Centauri", "")
        val sirio = EstrellaCeleste("Sirio", "")

        val sol = ObjetoContenedorImpl("Sol")
        val tierra = ObjetoContenedorImpl("Tierra")
        val jupiter = ObjetoContenedorImpl("Júpiter")

        val venus = PlanetaCeleste("Venus", "")
        val marte = PlanetaCeleste("Marte", "")
        val luna = LunaCeleste("Luna")
        val io = LunaCeleste("IO")
        val europa = LunaCeleste("Europa")
        val galilea = LunaCeleste("Galilea")
        val calisto = LunaCeleste("Calisto")

        mapaCeleste.agregarObjetoCeleste(viaLactea)
        mapaCeleste.agregarObjetoCeleste(andromeda)

        mapaCeleste.agregarObjetoCeleste(sagitarioAStar)
        mapaCeleste.agregarObjetoCeleste(sol)
        mapaCeleste.agregarObjetoCeleste(alphaCentauri)
        mapaCeleste.agregarObjetoCeleste(sirio)

        sol.contenido.addAll(listOf(venus, marte, tierra, jupiter))
        tierra.contenido.add(luna)
        jupiter.contenido.addAll(listOf(io, europa, galilea, calisto))

        mapaCeleste.imprimirMapa()
    }
}

class ObjetoContenedorImpl(nombre: String) : ObjetoCeleste(nombre, ""), ObjetoContenedor {
    override val contenido: MutableList<ObjetoCeleste> = mutableListOf()
}


fun main() {
    val ejemploMapaCeleste = EjemploMapaCeleste()
    ejemploMapaCeleste.crearMapaCeleste()
}
