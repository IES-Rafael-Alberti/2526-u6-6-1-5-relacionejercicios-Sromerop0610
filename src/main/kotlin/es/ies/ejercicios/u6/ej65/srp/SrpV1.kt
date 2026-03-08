package es.ies.ejercicios.u6.ej65.srp

import es.ies.ejercicios.u6.ej64.Alumno
import es.ies.ejercicios.u6.ej64.InformeMarkdown
import es.ies.ejercicios.u6.ej64.Persona
import es.ies.ejercicios.u6.ej64.RegistroPersonas
import es.ies.ejercicios.u6.ej64.Resumible

/**
 * Servicio responsable de preparar los datos iniciales
 */
class PreparadorDatos {
    fun obtenerDatos(): List<Resumible> {
        return listOf(
            Persona(" Ana ", 20),
            Alumno("Luis", 19, "1DAM"),
            Persona("Marta", 18)
        )
    }
}

/**
 * Servicio responsable de registrar personas en un registro
 */
class ServicioRegistroPersonas(private val registro: RegistroPersonas = RegistroPersonas()) {
    fun registrar(items: List<Resumible>) {
        for (item in items) {
            if (item is Persona) registro.registrar(item)
        }
    }

    fun buscar(nombre: String): Persona? = registro.buscar(nombre)
}

/**
 * Servicio responsable de generar informes
 */
class GeneradorInforme(private val informe: InformeMarkdown = InformeMarkdown()) {
    fun generar(titulo: String, items: List<Resumible>): String {
        return informe.generar(titulo, items)
    }
}

/**
 * Servicio responsable de logs simples por consola
 */
class Logger {
    fun info(mensaje: String) = println(mensaje)
}

/**
 * Aplicación principal SRP: coordina los servicios sin hacer trabajo que no le corresponde
 */
class InformeAppService {
    private val logger = Logger()
    private val preparador = PreparadorDatos()
    private val registroService = ServicioRegistroPersonas()
    private val generador = GeneradorInforme()

    fun ejecutar() {
        logger.info("[SRP] Preparando datos...")
        val items = preparador.obtenerDatos()

        logger.info("[SRP] Registrando personas...")
        registroService.registrar(items)

        logger.info("[SRP] Generando informe Markdown...")
        val salida = generador.generar("Listado", items)

        logger.info("[SRP] Resultado:")
        println(salida)

        val busqueda = registroService.buscar("ana")
        logger.info("[SRP] Buscar 'ana' -> ${busqueda?.resumen()}")
    }
}

fun main() {
    InformeAppService().ejecutar()
}


