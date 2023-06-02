import kotlin.math.pow

open class CelestialBody(var distanceToSun: Double, var positionInSky: String, var isVisibleToNakedEye: Boolean) {
    open fun printName() {
        println("Nombre del cuerpo celeste")
    }
}

class BlackHole(distanceToSun: Double, positionInSky: String, isVisibleToNakedEye: Boolean, var rotationVelocity: Double, var mass: Double, var isPrimitive: Boolean) : CelestialBody(distanceToSun, positionInSky, isVisibleToNakedEye) {
    override fun printName() {
        println("Agujero negro")
    }

    //Soy aficionado a las teorias de agujeros negros y de gusano. Este ejer me ha molado :)
    fun calculateSchwarzschildRadius(): Double? {
        if (rotationVelocity == 0.0) {
            val G = 6.67e-11
            val c = 3e8
            return 2 * G * mass * c.pow(2)
        }
        return null
    }
}

class Galaxy(distanceToSun: Double, positionInSky: String, isVisibleToNakedEye: Boolean, var luminosity: Double, var celestialBodies: Int, var type: String) : CelestialBody(distanceToSun, positionInSky, isVisibleToNakedEye) {

    init {
        require(type in listOf("Elliptical", "Spiral", "Dwarf", "Irregular")) {
            "Invalid galaxy type: $type"
        }
    }

    override fun printName() {
        println("Galaxy type: $type")
        super.printName()
    }
}


class Star(distanceToSun: Double, positionInSky: String, isVisibleToNakedEye: Boolean, var luminosity: Double, var temperature: Double, var spectralClass: String) : CelestialBody(distanceToSun, positionInSky, isVisibleToNakedEye) {
    var belongsTo: Galaxy? = null

    val spectralClass: String
        get() = calculateSpectralClass()

    private fun calculateSpectralClass(): String {
        return when {
            temperature >= 30000 -> "O"
            temperature >= 10000 -> "B"
            temperature >= 7500 -> "A"
            temperature >= 6000 -> "F"
            temperature >= 5200 -> "G"
            temperature >= 3700 -> "K"
            else -> "M"
        }
    }

    override fun printName() {
        println("Star spectral class: $spectralClass")
        super.printName()
    }
}

class Planet(val mass: Double, val isHabitable: Boolean, val hasAtmosphere: Boolean, val moons: List<Moon>, val star: Star?)

class Moon(val mass: Double, val isHabitable: Boolean, val hasAtmosphere: Boolean, val planet: Planet)

fun findTheFarthest(celestialBodies: List<CelestialBody>): CelestialBody {
    return celestialBodies.maxByOrNull { it.distanceToSun } ?: throw NoSuchElementException("Empty list of celestial bodies")
}

fun filterByGalaxy(stars: List<Star?>, galaxy: Galaxy): List<Star> {
    return stars.filter { it?.belongsTo == galaxy }.mapNotNull { it }
}

object SkyMap {
    private val celestialObjects: MutableList<CelestialObject> = mutableListOf()

    fun addCelestialObject(celestialObject: CelestialObject) {
        celestialObjects.add(celestialObject)
    }

    fun printMap() {
        celestialObjects.forEach { celestialObject ->
            printCelestialObject(celestialObject, 0)
        }
    }

    private fun printCelestialObject(celestialObject: CelestialObject, indentationLevel: Int) {
        val indentation = "\t".repeat(indentationLevel)
        println("$indentation${celestialObject.name} ${celestialObject.additionalInfo}")
        if (celestialObject is ContainerObject) {
            val containerObject = celestialObject as ContainerObject
            containerObject.contents.forEach { content ->
                printCelestialObject(content, indentationLevel + 1)
            }
        }
    }
}

abstract class CelestialObject(val name: String, val additionalInfo: String)

interface ContainerObject {
    val contents: List<CelestialObject>
}

class Planet(name: String, additionalInfo: String) : CelestialObject(name, additionalInfo)

class Moon(name: String) : CelestialObject(name, "")

class Star(name: String, additionalInfo: String) : CelestialObject(name, additionalInfo)

class Galaxy(name: String, additionalInfo: String) : CelestialObject(name, additionalInfo), ContainerObject {
    override val contents: MutableList<CelestialObject> = mutableListOf()
}

class BlackHole(name: String) : CelestialObject(name, "(blackhole)")

class SkyMapExample {
    fun createSkyMap() {
        val skyMap = SkyMap

        val milkyWay = Galaxy("MilkyWay", "")
        val andromeda = Galaxy("Andromeda", "")

        val sagitariusAStar = BlackHole("Sagitarius A*")
        val alphaCentauri = Star("Alpha Centaury", "")
        val sirius = Star("Sirius", "")

        val sun = ContainerObjectImpl("Sun")
        val earth = ContainerObjectImpl("Earth")
        val jupiter = ContainerObjectImpl("Jupiter")

        val venus = Planet("Venus", "")
        val mars = Planet("Mars", "")
        val moon = Moon("Moon")
        val io = Moon("IO")
        val europa = Moon("Europa")
        val ganymede = Moon("Ganymede")
        val callisto = Moon("Callisto")

        skyMap.addCelestialObject(milkyWay)
        skyMap.addCelestialObject(andromeda)

        skyMap.addCelestialObject(sagitariusAStar)
        skyMap.addCelestialObject(sun)
        skyMap.addCelestialObject(alphaCentauri)
        skyMap.addCelestialObject(sirius)

        sun.contents.addAll(listOf(venus, mars, earth, jupiter))
        earth.contents.add(moon)
        jupiter.contents.addAll(listOf(io, europa, ganymede, callisto))

        skyMap.printMap()
    }
}

class ContainerObjectImpl(override val name: String) : CelestialObject(name, ""), ContainerObject {
    override val contents: MutableList<CelestialObject> = mutableListOf()
}

fun main() {
    val skyMapExample = SkyMapExample()
    skyMapExample.createSkyMap()
}
