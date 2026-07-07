package cl.uchile.dcc
package controller.observer

/**
 * Trait representing an Observer in the Observer design pattern.
 *
 * Observers listen to updates from a Subject and react accordingly.
 */
trait Observer {
  /**
   * Called by the Subject to notify this observer of a change.
   *
   * @param subject  the subject that triggered the update
   * @param response the notification message or data sent by the subject
   */
  def update(subject: Subject, response: Any): Unit
}
