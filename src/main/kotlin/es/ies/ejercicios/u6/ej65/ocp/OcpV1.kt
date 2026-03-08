package es.ies.ejercicios.u6.ej65.ocp

import es.ies.ejercicios.u6.ej64.Resumible

/**
 * Abstracción para cualquier formato de informe.
 * Permite extender el sistema sin modificar el generador.
 */
interface FormatoInforme {
    fun generar(titulo: String, items: List<Resumible>): String
}

/**
 * Implementación del formato CSV.
 */
class FormatoCsv : FormatoInforme {
    override fun generar(titulo: String, items: List<Resumible>): String =
        buildString {
            appendLine("titulo,$titulo")
            appendLine("item")
            for (item in items) {
                appendLine(item.resumen().replace(",", ";"))
            }
        }
}

/**
 * Implementación del formato Markdown.
 */
class FormatoMarkdown : FormatoInforme {
    override fun generar(titulo: String, items: List<Resumible>): String =
        buildString {
            appendLine("# $titulo")
            for (item in items) {
                appendLine("- ${item.resumen()}")
            }
        }
}

/**
 * Generador de informes que depende de la abstracción FormatoInforme.
 * Cumple el principio OCP: se pueden añadir nuevos formatos sin modificar esta clase.
 */
class GeneradorInforme {
    fun generar(formato: FormatoInforme, titulo: String, items: List<Resumible>): String {
        return formato.generar(titulo, items)
    }
}

fun main() {

    val items = listOf<Resumible>(
        object : Resumible {
            override fun resumen(): String = "Elemento A"
        },
        object : Resumible {
            override fun resumen(): String = "Elemento B"
        }
    )

    val generador = GeneradorInforme()

    val formatoMarkdown = FormatoMarkdown()
    val formatoCsv = FormatoCsv()

    println("---- Markdown ----")
    println(generador.generar(formatoMarkdown, "Demo OCP", items))

    println("---- CSV ----")
    println(generador.generar(formatoCsv, "Demo OCP", items))
}