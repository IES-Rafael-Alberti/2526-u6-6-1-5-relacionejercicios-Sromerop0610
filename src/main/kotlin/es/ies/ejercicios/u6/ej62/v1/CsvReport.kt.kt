package es.ies.ejercicios.u6.ej62.v1

/**
 * Genera informes en formato CSV
 */
class CSVReport : ReportTemplate() {
    override fun body(lines: List<String>): String {
        println("[DEBUG] Construyendo cuerpo CSV")
        return lines.joinToString(separator = ",") { it } + "\n"
    }
}