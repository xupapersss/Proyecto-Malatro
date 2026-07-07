package cl.uchile.dcc

import cl.uchile.dcc.controller.observer._
import munit.FunSuite

/**
 * Test suite for the Observer pattern implementation.
 *
 * This suite uses dummy classes to verify that a Subject correctly maintains
 * its list of Observers, notifies them of events, and allows attaching and detaching.
 */
class ObserverTest extends FunSuite {

  /** * Dummy Subject to test BaseSubject since its constructor is protected.
   * Exposes the notification method for testing.
   */
  class TestSubject extends BaseSubject {
    def triggerNotification(message: Any): Unit = {
      notifyObservers(message)
    }
  }

  /** * Dummy Observer to track if updates are actually received.
   * Stores the last received response and the notification count.
   */
  class TestObserver extends Observer {
    var lastSubject: Subject = _
    var lastResponse: Any = _
    var notificationCount: Int = 0

    override def update(subject: Subject, response: Any): Unit = {
      lastSubject = subject
      lastResponse = response
      notificationCount += 1
    }
  }

  /** Verifies that a Subject can attach an observer and successfully send it an update. */
  test("BaseSubject should attach an observer and notify it") {
    val subject = new TestSubject()
    val observer = new TestObserver()

    subject.attach(observer)
    subject.triggerNotification("Test Message")

    assertEquals(observer.notificationCount, 1)
    assertEquals(observer.lastResponse, "Test Message")
    assertEquals(observer.lastSubject, subject)
  }

  /** Verifies that a detached observer no longer receives updates. */
  test("BaseSubject should detach an observer so it stops receiving notifications") {
    val subject = new TestSubject()
    val observer = new TestObserver()

    subject.attach(observer)
    subject.detach(observer)

    subject.triggerNotification("Test Message")

    assertEquals(observer.notificationCount, 0)
    assertEquals(observer.lastResponse, null)
  }

  /** Verifies that a Subject correctly iterates and notifies all its attached observers. */
  test("BaseSubject should be able to notify multiple observers at once") {
    val subject = new TestSubject()
    val observer1 = new TestObserver()
    val observer2 = new TestObserver()

    subject.attach(observer1)
    subject.attach(observer2)

    subject.triggerNotification("Broadcast Message")

    assertEquals(observer1.notificationCount, 1)
    assertEquals(observer2.notificationCount, 1)
    assertEquals(observer1.lastResponse, "Broadcast Message")
    assertEquals(observer2.lastResponse, "Broadcast Message")
  }

  /** Verifies that detaching a single observer preserves the connection with the rest. */
  test("BaseSubject should detach a specific observer without affecting others") {
    val subject = new TestSubject()
    val observer1 = new TestObserver()
    val observer2 = new TestObserver()

    subject.attach(observer1)
    subject.attach(observer2)

    // Detach only observer1
    subject.detach(observer1)

    subject.triggerNotification("Message for remaining observers")

    // observer1 should NOT be notified
    assertEquals(observer1.notificationCount, 0)
    assertEquals(observer1.lastResponse, null)

    // observer2 SHOULD be notified
    assertEquals(observer2.notificationCount, 1)
    assertEquals(observer2.lastResponse, "Message for remaining observers")
  }
}