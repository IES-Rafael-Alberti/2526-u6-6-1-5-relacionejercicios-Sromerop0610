package es.ies.ejercicios.u6.ej62.v1
/**
 * Plantilla base para generar informes.
 * Bloquea la generación completa (final) y fuerza la implementación
 * de detalles específicos en subclases.
 */
abstract class ReportTemplate {

    // Método público que ejecuta el algoritmo completo (bloqueado)
    fun generate(title: String, lines: List<String>): String {
        println("[DEBUG] Generando informe usando ${this::class.simpleName}")
        val headerText = header(title)
        val bodyText = body(lines)
        val footerText = footer()
        return "$headerText$bodyText$footerText"
    }

    // Forzar implementación: cada formato debe implementar body
    protected abstract fun body(lines: List<String>): String

    // Opcional: hooks para header/footer, se pueden sobrescribir si se quiere
    protected open fun header(title: String): String = "=== $title ===\n"
    protected open fun footer(): String = "\n=== Fin del informe ===\n"
}