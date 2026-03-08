package es.ies.ejercicios.u6.ej64

/**
 * Representa un elemento que puede generar un resumen en texto.
 * Las clases que implementan esta interfaz pueden ser utilizadas
 * dentro de informes generados por distintas plantillas.
 */
interface Resumible {
    fun resumen(): String
}

/**
 * Plantilla base para generar informes en distintos formatos.
 *
 * Aplica el patrón Template Method: la estructura general del informe
 * está definida en el método [generar], mientras que las subclases
 * pueden personalizar partes concretas como la cabecera, el formato
 * de los elementos o el pie del informe.
 *
 * Las clases como [Persona] o [Alumno] implementan [Resumible],
 * por lo que pueden incluirse como elementos del informe.
 */
abstract class PlantillaInforme : Resumible {
    /**
     * Genera un informe completo utilizando una estructura común.
     *
     * @param titulo título del informe
     * @param items lista de elementos resumibles que aparecerán en el informe
     * @return el informe generado en formato texto
     */
    fun generar(titulo: String, items: List<Resumible>): String {

        val sb = StringBuilder()

        sb.appendLine(cabecera(titulo))

        for (item in items) {
            sb.appendLine(formatearItem(item))
        }

        sb.appendLine(pie())
        return sb.toString() // devolver el string
    }
    /**
     * Genera la cabecera del informe.
     * Puede ser sobrescrita por las subclases.
     */
    protected open fun cabecera(titulo: String): String = titulo
    /**
     * Formatea un elemento del informe.
     * Cada formato de informe implementará su propia forma.
     */
    protected abstract fun formatearItem(item: Resumible): String
    /**
     * Genera el pie del informe.
     */
    protected open fun pie(): String = "-- fin --"

    override fun resumen(): String = "PlantillaInforme"
}
/**
 * Implementación de informe en formato Markdown.
 */
class InformeMarkdown : PlantillaInforme() {
    override fun cabecera(titulo: String): String = "# $titulo"

    override fun formatearItem(item: Resumible): String = "- ${item.resumen()}"
}
/**
 * Implementación de informe en formato CSV.
 */
class InformeCsv : PlantillaInforme() {
    override fun cabecera(titulo: String): String = "titulo,$titulo\nitem"

    override fun formatearItem(item: Resumible): String = item.resumen().replace(",", ";")
}
/**
 * Representa una persona con nombre y edad.
 *
 * Implementa [Resumible] para poder incluirse dentro de informes.
 *
 * @property nombre nombre de la persona
 * @property edad edad de la persona
 */
open class Persona(
    val nombre: String,
    val edad: Int,
) : Resumible {
    init {
        println("[Persona:init] nombre=$nombre edad=$edad")
    }
    /**
     * Constructor secundario que permite crear una persona
     * indicando solo el nombre.
     *
     * La edad se establece por defecto en 0.
     */
    constructor(nombre: String) : this(nombre, edad = 0) {
        println("[Persona:secondary] constructor(nombre)")
    }

    override fun resumen(): String = "$nombre ($edad)"
}
/**
 * Representa un alumno que extiende la clase [Persona]
 * añadiendo información sobre el curso.
 */
class Alumno : Persona {
    val curso: String
    /**
     * Constructor que inicializa todas las propiedades del alumno.
     */
    constructor(nombre: String, edad: Int, curso: String) : super(nombre, edad) {
        // Asignar curso
        this.curso = curso
        println("[Alumno:secondary] nombre=$nombre edad=$edad curso=$curso")
    }
    /**
     * Constructor que permite crear un alumno indicando
     * únicamente el nombre y el curso.
     *
     * La edad se establece por defecto en 0.
     */
    constructor(nombre: String, curso: String) : this(nombre, edad = 0, curso = curso) {
        println("[Alumno:secondary] constructor(nombre, curso)")
    }

    override fun resumen(): String = "Alumno: ${super.resumen()} curso=$curso"
}

/**
 * Registro sencillo de personas indexado por nombre.
 *
 * Los nombres se normalizan antes de almacenarse para evitar
 * duplicados producidos por diferencias de espacios o
 * mayúsculas/minúsculas.
 */
class RegistroPersonas {
    private val personasPorNombre = mutableMapOf<String, Persona>()
    /**
     * Registra una persona en el sistema.
     *
     * Si ya existe una persona con el mismo nombre normalizado,
     * el registro anterior será reemplazado.
     *
     * @param persona persona que se desea registrar
     */
    fun registrar(persona: Persona) {
        val clave = normalizarNombre(persona.nombre)
        personasPorNombre[clave] = persona
    }
    /**
     * Busca una persona por su nombre.
     *
     * @param nombre nombre a buscar
     * @return la persona encontrada o `null` si no existe
     */
    fun buscar(nombre: String): Persona? = personasPorNombre[normalizarNombre(nombre)]
    /**
     * Normaliza el nombre eliminando espacios sobrantes
     * y convirtiéndolo a minúsculas.
     *
     * Esto evita que nombres como "Ana", " ana " o "ANA"
     * se consideren personas diferentes.
     */
    private fun normalizarNombre(nombre: String): String {
        return nombre.trim().lowercase()
    }
}
/**
 * Función principal para demostrar el funcionamiento del programa.
 */
fun main() {

    val p1 = Persona("Ana", 30)
    val p2 = Persona("Luis")

    val a1 = Alumno("Carlos", 20, "DAM")
    val a2 = Alumno("Marta", "DAW")

    val lista = listOf(p1, p2, a1, a2)

    val informeMarkdown = InformeMarkdown()
    val informeCsv = InformeCsv()

    println(informeMarkdown.generar("Informe Personas", lista))
    println()
    println(informeCsv.generar("Informe Personas", lista))

    val registro = RegistroPersonas()
    registro.registrar(p1)
    registro.registrar(a1)

    println()
    println("Busqueda de 'ana': ${registro.buscar("ana")?.resumen()}")
}