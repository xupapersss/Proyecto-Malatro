package cl.uchile.dcc
package controller.observer

import scala.collection.mutable

/**
 * Base implementation of the Subject trait for the Observer pattern.
 *
 * Manages a mutable collection of observers and provides standard implementations
 * to attach, detach, and notify them of any state changes.
 */
class BaseSubject protected extends Subject {
  private val observers: mutable.Set[Observer] = mutable.LinkedHashSet.empty[Observer]

  override def attach(observer: Observer): Unit = {
    observers += observer
  }

  override def detach(observer: Observer): Unit = {
    observers -= observer
  }

  override def notifyObservers(notification: Any): Unit = {
    for (observer <- observers) do observer.update(this, notification)
  }

}
