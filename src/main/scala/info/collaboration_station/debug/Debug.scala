package info.collaboration_station.debug

/**
  * Created by johnreed on 3/12/16.
  */
object Debug {

  /**
    * Stack offset is 2 because the first row in the stack trace is Thread and the second row is internal call.
    */
  protected[debug] val stackOffset = 2

  private var _traceOn_? = true

  private var _assertOn_? = true

  def traceOn_? = _traceOn_?

  def assertOn_? = _assertOn_?

  /**
    * Turns on debug mode. Print statements with stack traces are visible and fatal assertions are enabled.
    * Note: The exclamation point means "not pure".
    */
  def debugModeOn_!() = {
    _traceOn_? = true
    _assertOn_? = true
  }

  /**
    * Turns off debug mode. Print statements with stack traces are invisible and fatal assertions are disabled.
    */
  def debugModeOff_!() = {
    _traceOn_? = true
    _assertOn_? = true
  }

  def traceOn_!() = {
    _traceOn_? = true
  }

  def traceOff_!() = {
    _traceOn_? = false
  }

  def assertOn_!() = {
    _assertOn_? = true
  }

  def assertOff_!() = {
    _assertOn_? = false
  }

  /**
    * A fatal assertion. Debug.assert( 1 + 2 = 3 )
    * Terminates the program with exit code "7"
    *
    * @param assertion the assertion that must be true for the program to run. Can be a value or a function.
    * @param message the message to be printed to standard error on assertion failure
    */
  final def assert(assertion: => Boolean, message: String) = {
    if(!assertion) {
      ImplicitTraceObject.traceInternal(message, Int.MaxValue) // trace the max number of lines of stack trace to std error
    }
    System.exit(7)
  }

  /**
    * A fatal assertion. Debug.assert( 1 + 2 = 3 )
    * Terminates the program with exit code "7"
    *
    * @param assertion the assertion that must be true for the program to run. Can be a value or a function.
    * @param message the message to be printed to standard out on assertion failure
    */
  final def assertStdOut(assertion: => Boolean, message: String) = {
    if(!assertion) {
      ImplicitTraceObject.traceInternal(message, Int.MaxValue, useStdOut_? = true) // trace the max number of lines of stack trace to std out
    }
    System.exit(7)
  }

  /**
    * Like Debug.assert, but does not terminate the application.
    */
  final def assertNonFatal(assertion: => Boolean, message: String) = {
    if(!assertion) {
      ImplicitTraceObject.traceInternal(message, Int.MaxValue) // trace the max number of lines of stack trace to std error
    }
  }

  /**
    * Like Debug.assertStdOut, but does not terminate the application.
    */
  final def assertNonFatalStdOut(assertion: => Boolean, message: String) = {
    if(!assertion) {
      ImplicitTraceObject.traceInternal(message, Int.MaxValue, useStdOut_? = true) // trace the max number of lines of stack trace to std out
    }
  }
}
