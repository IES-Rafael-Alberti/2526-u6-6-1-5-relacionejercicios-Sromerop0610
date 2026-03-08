package es.ies.ejercicios.u6.ej62.v1

fun main() {
    val lines = listOf("Primera línea", "Segunda línea", "Tercera línea")

    println("[FINAL] Generando CSV...")
    val csvReport = CSVReport()
    println(csvReport.generate(title = "Informe", lines = lines))

    println("[FINAL] Generando Markdown...")
    val mdReport = MarkdownReport()
    println(mdReport.generate(title = "Informe", lines = lines))
}