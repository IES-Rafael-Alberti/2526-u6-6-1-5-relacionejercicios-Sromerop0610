package es.ies.ejercicios.u6.ej65.isp

import es.ies.ejercicios.u6.ej64.Persona

/**
 * Interfaces pequeñas y específicas (cumplen ISP).
 */

interface RepositorioGuardarPersonas {
    fun guardar(persona: Persona)
}

interface RepositorioBuscarPersonas {
    fun buscar(nombre: String): Persona?
}

interface RepositorioExportarPersonas {
    fun exportarCsv(): String
}

interface RepositorioBorrarPersonas {
    fun borrarTodo()
}

/**
 * Implementación del repositorio en memoria.
 * Puede implementar todas las interfaces si ofrece todas las funcionalidades.
 */
class RepositorioMemoria :
    RepositorioGuardarPersonas,
    RepositorioBuscarPersonas,
    RepositorioExportarPersonas,
    RepositorioBorrarPersonas {

    private val map = mutableMapOf<String, Persona>()

    override fun guardar(persona: Persona) {
        map[persona.nombre] = persona
    }

    override fun buscar(nombre: String): Persona? = map[nombre]

    override fun exportarCsv(): String =
        buildString {
            appendLine("nombre,edad")
            for (p in map.values) {
                appendLine("${p.nombre},${p.edad}")
            }
        }

    override fun borrarTodo() {
        map.clear()
    }
}

/**
 * Cliente que solo necesita buscar personas.
 * Ahora depende solo de la interfaz que realmente usa.
 */
class BuscadorPersonas(private val repo: RepositorioBuscarPersonas) {

    fun buscar(nombre: String): Persona? {
        return repo.buscar(nombre)
    }
}

fun main() {

    val repo = RepositorioMemoria()

    repo.guardar(Persona("Ana", 20))
    repo.guardar(Persona("Luis", 25))

    val buscador = BuscadorPersonas(repo)

    println("Buscar Ana -> ${buscador.buscar("Ana")?.resumen()}")

    println()
    println("Exportar CSV:")
    println(repo.exportarCsv())
}