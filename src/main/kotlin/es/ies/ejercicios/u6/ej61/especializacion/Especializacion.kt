package es.ies.ejercicios.u6.ej61.especializacion

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

fun main() {
    val empleado1: Empleado = Programador("Ana", 1500.0, 10)
    val empleado2: Empleado = Diseñador("Luis", 1400.0, 300.0)

    println(empleado1.descripcion())
    println("Salario: ${empleado1.calcularSalario()}")

    println(empleado2.descripcion())
    println("Salario: ${empleado2.calcularSalario()}")
}