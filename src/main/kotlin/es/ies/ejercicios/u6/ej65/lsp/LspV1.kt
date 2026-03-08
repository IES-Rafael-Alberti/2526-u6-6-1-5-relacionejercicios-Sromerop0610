package es.ies.ejercicios.u6.ej65.lsp

import es.ies.ejercicios.u6.ej64.Persona

/**
 * Contrato base: cualquier repositorio de personas debe permitir buscar.
 */
interface RepositorioPersonas {
    fun buscar(nombre: String): Persona?
}

/**
 * Contrato para repositorios que además permiten guardar.
 * Extiende el contrato de lectura.
 */
interface RepositorioPersonasEscritura : RepositorioPersonas {
    fun guardar(persona: Persona)
}

/**
 * Implementación normal: permite guardar y buscar personas.
 */
class RepositorioPersonasMemoria : RepositorioPersonasEscritura {

    private val map = mutableMapOf<String, Persona>()

    override fun guardar(persona: Persona) {
        map[persona.nombre] = persona
    }

    override fun buscar(nombre: String): Persona? = map[nombre]
}

/**
 * Implementación de solo lectura.
 * Solo cumple el contrato de lectura.
 */
class RepositorioSoloLectura(
    private val datos: Map<String, Persona>
) : RepositorioPersonas {

    override fun buscar(nombre: String): Persona? = datos[nombre]
}

/**
 * Cliente que necesita lectura y escritura.
 */
fun clienteEscritura(repo: RepositorioPersonasEscritura) {
    repo.guardar(Persona("Ana", 20))
    println("Buscar Ana -> ${repo.buscar("Ana")?.resumen()}")
}

/**
 * Cliente que solo necesita lectura.
 */
fun clienteLectura(repo: RepositorioPersonas) {
    println("Buscar Ana -> ${repo.buscar("Ana")?.resumen()}")
}

fun main() {

    println("[LSP] Repositorio con escritura")
    val repoNormal = RepositorioPersonasMemoria()
    clienteEscritura(repoNormal)

    println("\n[LSP] Repositorio solo lectura")
    val datos = mapOf(
        "Ana" to Persona("Ana", 20)
    )
    val repoSoloLectura = RepositorioSoloLectura(datos)
    clienteLectura(repoSoloLectura)
}