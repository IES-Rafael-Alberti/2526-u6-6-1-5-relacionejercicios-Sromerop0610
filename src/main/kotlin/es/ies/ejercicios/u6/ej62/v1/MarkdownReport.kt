package es.ies.ejercicios.u6.ej62.v1

/**
 * Genera informes en formato Markdown
 */
class MarkdownReport : ReportTemplate() {
    override fun body(lines: List<String>): String {
        println("[DEBUG] Construyendo cuerpo Markdown")
        return lines.joinToString(separator = "\n") { "- $it" } + "\n"
    }
}