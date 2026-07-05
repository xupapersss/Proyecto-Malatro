package cl.uchile.dcc
package controller.observer

trait Subject {
  def attach(observer: Observer): Unit
  def detach(observer: Observer): Unit
  def notifyObservers(notification: Any): Unit
}
