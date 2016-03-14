package info.collaboration_station.debug

/**
  * Created by johnreed on 3/12/16.
  */
object Debug {

  /**
    * Stack offset is 2 because the first row in the stack trace is Thread and the second row is internal call.
    */
  protected[debug] val stackOffset = 2

  private var _traceOut_? = true

  /**
    * Tells you whether tracing to standard out is on or off.
    * Note that disabling the "traceStdOut" feature does not disable the "assertStdOut" feature.
    */
  def traceOutOn_? = _traceOut_?

  private var _traceErr_? = true

  /**
    * Tells you whether tracing to standard error is on or off.
    * Note that disabling the "trace" feature does not disable the "assert" feature.
    */
  def traceErrOn_? = _traceErr_?

  private var _fatalAssert_? = true

  /**
    * Tells you whether fatal asserts are on or off.
    */
  def fatalAssertOn_? = _fatalAssert_?

  private var _nonFatalAssert_? = true

  /**
    * Tells you whether non-fatal asserts are on or off.
    */
  def nonFatalAssertOn_? = _nonFatalAssert_?

  // these lines disable and enable particular features.

  def traceErrOn_!() = { _traceErr_? = true }

  def traceErrOff_!() = { _traceErr_? = false }

  def traceOutOn_!() = { _traceOut_? = true }

  def traceOutOff_!() = { _traceOut_? = false }

  def fatalAssertOn_!() = { _fatalAssert_? = true }

  def fatalAssertOff_!() = { _fatalAssert_? = false }

  def nonFatalAssertOn_!() = { _nonFatalAssert_? = true }

  def nonFatalAssertOff_!() = { _nonFatalAssert_? = false }

  /**
    * Enables tracing and asserts, including fatal assertions.
    */
  def enableEverything_!() = {
    traceErrOn_!()
    traceOutOn_!()
    fatalAssertOn_!()
    nonFatalAssertOn_!()
  }

  /**
    * Disables tracing and asserts. Both fatal and non-fatal assertions are disabled. Does not disable print or println.
    */
  def disableEverything_!() = {
    traceErrOff_!()
    traceOutOff_!()
    fatalAssertOff_!()
    nonFatalAssertOff_!()
  }

  /**
    * A fatal assertion. Debug.assert( 1 + 2 = 3 )
    * Terminates the program with exit code "7"
    *
    * @param assertion the assertion that must be true for the program to run. Can be a value or a function.
    * @param message the message to be printed to standard error on assertion failure
    */
  final def assert(assertion: => Boolean, message: String) = {
    if(!assertion && Debug.fatalAssertOn_?) {
      ImplicitTraceObject.traceInternalAssert(message, Int.MaxValue) // trace the max number of lines of stack trace to std error
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
    if(!assertion && Debug.fatalAssertOn_?) {
      ImplicitTraceObject.traceInternalAssert(message, Int.MaxValue, useStdOut_? = true) // trace the max number of lines of stack trace to std out
    }
    System.exit(7)
  }

  /**
    * Like Debug.assert, but does not terminate the application.
    */
  final def assertNonFatal(assertion: => Boolean, message: String) = {
    if(!assertion && Debug.nonFatalAssertOn_?) {
      ImplicitTraceObject.traceInternalAssert(message, Int.MaxValue) // trace the max number of lines of stack trace to std error
    }
  }

  /**
    * Like Debug.assertStdOut, but does not terminate the application.
    */
  final def assertNonFatalStdOut(assertion: => Boolean, message: String) = {
    if(!assertion && Debug.nonFatalAssertOn_?) {
      ImplicitTraceObject.traceInternalAssert(message, Int.MaxValue, useStdOut_? = true) // trace the max number of lines of stack trace to std out
    }
  }
}
