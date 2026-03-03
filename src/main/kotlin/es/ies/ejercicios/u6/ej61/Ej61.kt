package es.ies.ejercicios.u6.ej61

/**
 * Ejercicio 6.1 — Tipos de herencia, clases y subclases (RA7.a).
 *
 * TODO: Implementa aquí las clases del ejercicio.
 * Recomendación: crea subpaquetes (p. ej. `especializacion`, `extension`, etc.)
 * y añade un `main` de ejemplo que muestre polimorfismo.
 */
object Ej611 {

    open class Empleado(
        val nombre: String,
        val salarioBase: Double
    ) {
        open fun calcularSalario(): Double {
            return salarioBase
        }

        open fun descripcion(): String {
            return "Empleado: $nombre"
        }
    }

    class Programador(
        nombre: String,
        salarioBase: Double,
        val horasExtra: Int
    ) : Empleado(nombre, salarioBase) {

        override fun calcularSalario(): Double {
            return salarioBase + (horasExtra * 20)
        }

        override fun descripcion(): String {
            return "Programador: $nombre"
        }
    }

    class Diseñador(
        nombre: String,
        salarioBase: Double,
        val plusCreativo: Double
    ) : Empleado(nombre, salarioBase) {

        override fun calcularSalario(): Double {
            return salarioBase + plusCreativo
        }

        override fun descripcion(): String {
            return "Diseñador: $nombre"
        }
    }
}

object Ej612 {
    open class Vehiculo(
        val marca: String
    ) {
        open fun arrancar() {
            println("El vehículo $marca está arrancando.")
        }
    }
    class CocheElectrico(
        marca: String,
        val nivelBateria: Int
    ) : Vehiculo(marca) {

        fun mostrarBateria() {
            println("Nivel de batería: $nivelBateria%")
        }
    }
    class Camion(
        marca: String,
        val capacidadCarga: Int
    ) : Vehiculo(marca) {

        fun mostrarCarga() {
            println("Capacidad de carga: $capacidadCarga kg")
        }
    }
}