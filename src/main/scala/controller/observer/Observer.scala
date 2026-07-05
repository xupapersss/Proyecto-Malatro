package cl.uchile.dcc
package controller.observer

trait Observer {
  def update(subject: Subject, response: Any): Unit
}
