package cl.uchile.dcc
package controller

class InvalidTransitionException(action : String, state: String)
  extends Exception(
    s"Invalid transition: cannot '$action' when in state '$state'"
  )
