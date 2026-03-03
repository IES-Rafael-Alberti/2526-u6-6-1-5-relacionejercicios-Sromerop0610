package es.ies.ejercicios.u6.ej61.extension

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

fun main() {
    val vehiculo1: Vehiculo = CocheElectrico("Tesla", 80)
    val vehiculo2: Vehiculo = Camion("Volvo", 5000)

    vehiculo1.arrancar()
    vehiculo2.arrancar()
}