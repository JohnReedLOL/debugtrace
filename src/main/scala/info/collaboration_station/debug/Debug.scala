package info.collaboration_station.debug

/**
  * Created by johnreed on 3/12/16
  */
object Debug {

  /**
    * Stack offset is 2 because the first row in the stack trace is Thread and the second row is internal call
    */
  protected[debug] val stackOffset = 2

  @volatile private var _traceOutOn_? = true

  /** Tells you whether tracing to standard out is on or off
    * Note that disabling the "traceStdOut" feature does not disable the "assertStdOut" feature
    */
  def traceOutOn_? = _traceOutOn_?

  @volatile private var _traceErrOn_? = true

  /** Tells you whether tracing to standard error is on or off
    * Note that disabling the "trace" feature does not disable the "assert" feature
    */
  def traceErrOn_? = _traceErrOn_?

  @volatile private var _fatalAssertOn_? = true

  /**
    * Tells you whether fatal asserts are on or off
    */
  def fatalAssertOn_? = _fatalAssertOn_?

  @volatile private var _nonFatalAssertOn_? = true

  /**
    * Tells you whether non-fatal asserts are on or off
    */
  def nonFatalAssertOn_? = _nonFatalAssertOn_?

  // these lines disable and enable particular features

  /**
    * Enables tracing to standard error. Has no effect on "print" or "println", only on "trace" methods
    */
  def traceErrOn_!() = {
    _traceErrOn_? = true
  }

  /**
    * Disables tracing to standard error. Has no effect on "print" or "println", only on "trace" methods
    */
  def traceErrOff_!() = {
    _traceErrOn_? = false
  }

  /**
    * Enables tracing to standard out. Has no effect on "print" or "println", only on "traceStdOut" methods
    */
  def traceOutOn_!() = {
    _traceOutOn_? = true
  }

  /**
    * Disables tracing to standard out. Has no effect on "print" or "println", only on "traceStdOut" methods
    */
  def traceOutOff_!() = {
    _traceOutOn_? = false
  }

  /**
    * Enables fatal assertions. Has no effect on "assertNonFatal", only on "assert" and other fatal assert methods (assertEquals, etc.)
    */
  def fatalAssertOn_!() = {
    _fatalAssertOn_? = true
  }

  /**
    * Disables fatal assertions. Has no effect on "assertNonFatal", only on "assert" and other fatal assert methods (assertEquals, etc.)
    */
  def fatalAssertOff_!() = {
    _fatalAssertOn_? = false
  }

  /**
    * Enables non-fatal assertions. Has no effect on "assert" and other fatal assert methods (assertEquals, etc.)
    */
  def nonFatalAssertOn_!() = {
    _nonFatalAssertOn_? = true
  }

  /**
    * Disables non-fatal assertions. Has no effect on "assert" and other fatal assert methods (assertEquals, etc.)
    */
  def nonFatalAssertOff_!() = {
    _nonFatalAssertOn_? = false
  }

  /**
    * Enables tracing and asserts, including fatal assertions
    */
  def enableEverything_!() = {
    traceErrOn_!()
    traceOutOn_!()
    fatalAssertOn_!()
    nonFatalAssertOn_!()
  }

  /**
    * Disables tracing and asserts. Both fatal and non-fatal assertions are disabled. Does not disable print or println
    */
  def disableEverything_!() = {
    traceErrOff_!()
    traceOutOff_!()
    fatalAssertOff_!()
    nonFatalAssertOff_!()
  }

  /** Prints the trace message with a one line stack trace
    *
    * @param message the message to print before the one line stack trace
    */
  final def trace(message: String): Unit = ImplicitTraceObject.traceInternal(message, 1)

  /** Prints the trace message with N lines of stack trace
    *
    * @param message           the message to print before the stack trace
    * @param linesOfStackTrace the number of lines of stack trace to print
    */
  final def trace(message: String, linesOfStackTrace: Int): Unit = ImplicitTraceObject.traceInternal(message, linesOfStackTrace)

  /** Prints a trace message to standard error along with an entire stack trace
    *
    * @param message the message to print before the stack trace
    */
  final def traceStack(message: String): Unit = ImplicitTraceObject.traceInternal(message, Int.MaxValue)

  /** Prints a trace message to standard out with one line of stack trace
    *
    * @param message the message to print
    */
  final def traceStdOut(message: String): Unit = ImplicitTraceObject.traceInternal(message, 1, useStdOut_? = true)

  /** Prints a trace message to standard out with variable lines of stack trace
    *
    * @param message           the message to print
    * @param linesOfStackTrace the number of lines of stack trace
    */
  final def traceStdOut(message: String, linesOfStackTrace: Int): Unit = ImplicitTraceObject.traceInternal(message, linesOfStackTrace, useStdOut_? = true)

  /**
    * Prints a trace message to standard out along with an entire stack trace
    *
    * @param message the message to print
    */
  final def traceStackStdOut(message: String): Unit = ImplicitTraceObject.traceInternal(message, Int.MaxValue, useStdOut_? = true)


  /** A fatal assertion
    * Terminates the program with exit code "7"
    *
    * @param assertion the assertion that must be true for the program to run. Can be a value or a function
    * @param message   the message to be printed to standard error on assertion failure
    * @example Debug.assert( 1 + 2 == 4, "one plus two is not equal to four" )
    */
  final def assert(assertion: => Boolean, message: String, maxLines: Int = Int.MaxValue): Unit = {
    if (!assertion && Debug.fatalAssertOn_?) {
      ImplicitTraceObject.traceInternalAssert(message, maxLines) // trace the max number of lines of stack trace to std error
      System.exit(7)
    }
  }

  /** A fatal assertion
    * Terminates the program with exit code "7"
    *
    * @param assertion the assertion that must be true for the program to run. Can be a value or a function
    * @param message   the message to be printed to standard out on assertion failure
    * @example Debug.assertStdOut( 1 + 2 == 4, "one plus two is not equal to four" )
    */
  final def assertStdOut(assertion: => Boolean, message: String, maxLines: Int = Int.MaxValue): Unit = {
    if (!assertion && Debug.fatalAssertOn_?) {
      ImplicitTraceObject.traceInternalAssert(message, maxLines, useStdOut_? = true) // trace the max number of lines of stack trace to std out
      System.exit(7)
    }
  }

  /**
    * Like Debug.assert(), but does not terminate the application
    */
  final def assertNonFatal(assertion: => Boolean, message: String, maxLines: Int = Int.MaxValue): Unit = {
    if (!assertion && Debug.nonFatalAssertOn_?) {
      ImplicitTraceObject.traceInternalAssert(message, maxLines) // trace the max number of lines of stack trace to std error
    }
  }

  /**
    * Like Debug.assertStdOut(), but does not terminate the application
    */
  final def assertNonFatalStdOut(assertion: => Boolean, message: String, maxLines: Int = Int.MaxValue): Unit = {
    if (!assertion && Debug.nonFatalAssertOn_?) {
      ImplicitTraceObject.traceInternalAssert(message, maxLines, useStdOut_? = true) // trace the max number of lines of stack trace to std out
    }
  }
}