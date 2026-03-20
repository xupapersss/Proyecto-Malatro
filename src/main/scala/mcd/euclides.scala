package cl.uchile.dcc
package mcd

/*
El siguiente import es necesario sí quieren leer números enteros desde la consola.
Pueden leer un entero de la siguiente forma:

  val a = readInt()
  
y luego usarlo como deseen

  val res = a + 5
  println(s"El resultado del número ingresado más 5 es $res")
  
*/
import scala.io.StdIn.readInt

@main def euclidesInteractivo(): Unit = {
  // Inicio de la zona donde deben editar el código

  def mcd(a: Int, b: Int): Int = {
    if (b == 0) {
      a
    }else {
      mcd(b,a % b)
    }
  }


  println("Ingrese el primer numero (0 para salir): ")
  val a = readInt()
  if (a == 0){
    println("Adios!")
  } else {
    println("Ingrese el segundo numero")
    val b = readInt()

    val c = mcd(a,b)
    println(s"El MCD de $a y $b es $c")
  }
  // Fin de la zona donde deben editar el código
}
