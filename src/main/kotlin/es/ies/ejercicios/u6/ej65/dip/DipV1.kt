package es.ies.ejercicios.u6.ej65.dip

import es.ies.ejercicios.u6.ej64.InformeCsv
import es.ies.ejercicios.u6.ej64.Persona
import es.ies.ejercicios.u6.ej64.Resumible

/**
 * Abstracción para generadores de informes.
 * Módulos de alto nivel dependen de esta interfaz, no de implementaciones concretas.
 */
interface GeneradorInformes {
    fun generar(titulo: String, items: List<Resumible>): String
}

/**
 * Implementación concreta de [GeneradorInformes] usando CSV.
 */
class GeneradorCsv : GeneradorInformes {
    private val csv = InformeCsv()

    override fun generar(titulo: String, items: List<Resumible>): String {
        return csv.generar(titulo, items)
    }
}

/**
 * Controlador de informes.
 * Solo depende de la abstracción [GeneradorInformes].
 */
class ControladorInformes(private val generador: GeneradorInformes) {

    /**
     * Imprime un listado de elementos resumibles usando el generador inyectado.
     *
     * @param items Lista de objetos que implementan [Resumible].
     */
    fun imprimirListado(items: List<Resumible>) {
        val salida = generador.generar("Listado DIP", items)
        println(salida)
    }
}

fun main() {
    // Inyección de dependencia: el controlador recibe un generador concreto
    val generadorCsv = GeneradorCsv()
    val controller = ControladorInformes(generadorCsv)

    controller.imprimirListado(
        listOf(
            Persona("Ana", 20),
            Persona("Luis", 19)
        )
    )
}