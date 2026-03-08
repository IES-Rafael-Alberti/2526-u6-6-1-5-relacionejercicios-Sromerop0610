package es.ies.ejercicios.u6.ej63

/**
 * Plantilla para el ejercicio 6.3.
 *
 * Completa los TODOs para practicar:
 * - Constructores primarios y secundarios.
 * - Delegación `this(...)` en secundarios.
 * - Llamada a `super(...)` en herencia.
 * - Orden de inicialización (logs en `init`).
 */

open class Figura(
    val color: String,
    val etiqueta: String,
) {
    init {
        println("[Figura:init] color=$color etiqueta=$etiqueta")
    }

    constructor(color: String) : this(color, etiqueta = "sin-etiqueta") {
        println("[Figura:secondary] constructor(color)")
    }
}

class Rectangulo(
    color: String,
    etiqueta: String,
    val ancho: Int,
    val alto: Int,
) : Figura(color, etiqueta) {
    init {
        println("[Rectangulo:init] ancho=$ancho alto=$alto")
    }

    constructor(ancho: Int, alto: Int) : this(
        color = "sin-color",
        etiqueta = "rectangulo",
        ancho = ancho,
        alto = alto,
    ) {
        println("[Rectangulo:secondary] constructor(ancho, alto)")
    }

    constructor(lado: Int) : this(
        color = "sin-color",
        etiqueta = "cuadrado",
        ancho = lado,
        alto = lado
    ) {
        println("[Rectangulo:secondary] constructor(lado) -> cuadrado")
    }
}

class Circulo(
    color: String,
    etiqueta: String,
    val radio: Int,
) : Figura(color, etiqueta) {
    init {
        println("[Circulo:init] radio=$radio")
    }

    constructor(radio: Int) : this(
        color = "sin-color",
        etiqueta = "circulo",
        radio = radio
    ) {
        println("[Circulo:secondary] constructor(radio)")
    }
}

class Triangulo : Figura {
    val base: Int
    val altura: Int

    constructor(base: Int, altura: Int) : super("sin-color", "triangulo") {
        println("[Triangulo:secondary] constructor(base, altura) -> constructor padre")
        this.base = base
        this.altura = altura
    }

    constructor(lado: Int) : this(base = lado, altura = lado) {
        println("[Triangulo:secondary] constructor(lado) -> this(base, altura)")
    }
}

fun main() {

    println("---- Rectangulo con constructor primario ----")
    val r1 = Rectangulo("rojo", "rect1", 10, 20)

    println()

    println("---- Rectangulo con constructor secundario ----")
    val r2 = Rectangulo(5, 8)

    println()

    println("---- Rectangulo cuadrado (constructor lado) ----")
    val r3 = Rectangulo(6)

    println()

    println("---- Circulo con constructor primario ----")
    val c1 = Circulo("azul", "circulo1", 7)

    println()

    println("---- Circulo con constructor secundario ----")
    val c2 = Circulo(4)

    println()

    println("---- Triangulo (subclase sin constructor primario) ----")
    val t1 = Triangulo(3, 5)

    println()

    println("---- Triangulo cuadrado (constructor secundario) ----")
    val t2 = Triangulo(4)
}

