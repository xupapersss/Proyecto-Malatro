package cl.uchile.dcc
package controller.observer

/**
 * Trait representing a Subject in the Observer design pattern.
 *
 * A Subject maintains a list of dependents (observers) and notifies them
 * of any state changes or specific events.
 */
trait Subject {

  /**
   * Attaches an observer to the subject.
   *
   * @param observer the observer to be added
   */
  def attach(observer: Observer): Unit

  /**
   * Detaches an observer from the subject.
   *
   * @param observer the observer to be removed
   */
  def detach(observer: Observer): Unit

  /**
   * Notifies all attached observers about an event.
   *
   * @param notification the event data or message to broadcast
   */
  def notifyObservers(notification: Any): Unit
}
