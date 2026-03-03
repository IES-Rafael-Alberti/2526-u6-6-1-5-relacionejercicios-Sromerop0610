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