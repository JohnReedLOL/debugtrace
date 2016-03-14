package info.collaboration_station.debug

/**
  * Wrapper class for a value that can be traced
  *
  * @param me the original value inside the wrapper
  * @tparam MyType the original type of the value inside the wrapper
  */
final class ImplicitTrace[MyType](val me: MyType) {
  /**
    * Same as System.out.print(this), but with the function name after the object.
    */
  final def print() = {
    if(me != null) {
      System.out.print(me.toString)
    } else {
      System.out.print("null")
    }
  }
  /**
    * Same as System.out.println(this), but with the function name after the object.
    */
  final def println() = {
    if(me != null) {
      System.out.println(me.toString)
    } else {
      System.out.println("null")
    }
  }
  /**
    * Same as System.err.print(this), but with the function name after the object.
    */
  final def printStdErr() = {
    if(me != null) {
      System.err.print(me.toString)
    } else {
      System.err.print("null")
    }
  }
  /**
    * Same as System.err.println(this), but with the function name after the object.
    */
  final def printlnStdErr() = {
    if(me != null) {
      System.err.println(me.toString)
    } else {
      System.err.println("null")
    }
  }
  /**
    * Prints out this object with 0 lines of stack trace to standard error.
 *
    * @return The thing that was just printed.
    */
  final def trace0: MyType = ImplicitTraceObject.traceInternal(me, 0)
  /**
    * Prints out this object with 1 lines of stack trace to standard error. Same as trace1
 *
    * @return The thing that was just printed.
    */
  final def trace: MyType = ImplicitTraceObject.traceInternal(me, 1)
  /**
    * Prints out this object with 1 lines of stack trace to standard error.
 *
    * @return The thing that was just printed.
    */
  final def trace1: MyType = ImplicitTraceObject.traceInternal(me, 1)
  /**
    * Prints out this object with 2 lines of stack trace to standard error.
 *
    * @return The thing that was just printed.
    */
  final def trace2: MyType = ImplicitTraceObject.traceInternal(me, 2)
  /**
    * Prints out this object with 3 lines of stack trace to standard error.
 *
    * @return The thing that was just printed.
    */
  final def trace3: MyType = ImplicitTraceObject.traceInternal(me, 3)
  /**
    * Prints out this object with 4 lines of stack trace to standard error.
 *
    * @return The thing that was just printed.
    */
  final def trace4: MyType = ImplicitTraceObject.traceInternal(me, 4)
  /**
    * Prints out this object with 5 lines of stack trace to standard error.
 *
    * @return The thing that was just printed.
    */
  final def trace5: MyType = ImplicitTraceObject.traceInternal(me, 5)
  /**
    * Prints out this object with N lines of stack trace to standard error.
 *
    * @param linesOfStackTrace The number of lines of stack trace.
    * @return The thing that was just printed.
    */
  final def traceN(linesOfStackTrace: Int): MyType = ImplicitTraceObject.traceInternal(me, linesOfStackTrace)

  /**
    * Prints out this object with 0 lines of stack trace to standard out.
 *
    * @return The thing that was just printed.
    */
  final def trace0StdOut: MyType = ImplicitTraceObject.traceInternal(me, 0, useStdOut_? = true)
  /**
    * Prints out this object with 1 lines of stack trace to standard out. Same as trace1StdOut.
 *
    * @return The thing that was just printed.
    */
  final def traceStdOut: MyType = ImplicitTraceObject.traceInternal(me, 1, useStdOut_? = true)
  /**
    * Prints out this object with 1 lines of stack trace to standard out.
 *
    * @return The thing that was just printed.
    */
  final def trace1StdOut: MyType = ImplicitTraceObject.traceInternal(me, 1, useStdOut_? = true)
  /**
    * Prints out this object with 2 lines of stack trace to standard out.
 *
    * @return The thing that was just printed.
    */
  final def trace2StdOut: MyType = ImplicitTraceObject.traceInternal(me, 2, useStdOut_? = true)
  /**
    * Prints out this object with 3 lines of stack trace to standard out.
 *
    * @return The thing that was just printed.
    */
  final def trace3StdOut: MyType = ImplicitTraceObject.traceInternal(me, 3, useStdOut_? = true)
  /**
    * Prints out this object with 4 lines of stack trace to standard out.
 *
    * @return The thing that was just printed.
    */
  final def trace4StdOut: MyType = ImplicitTraceObject.traceInternal(me, 4, useStdOut_? = true)
  /**
    * Prints out this object with 5 lines of stack trace to standard out.
 *
    * @return The thing that was just printed.
    */
  final def trace5StdOut: MyType = ImplicitTraceObject.traceInternal(me, 5, useStdOut_? = true)
  /**
    * Prints out this object with N lines of stack trace to standard out.
 *
    * @param linesOfStackTrace The number of lines of stack trace.
    * @return The thing that was just printed.
    */
  final def traceNStdOut(linesOfStackTrace: Int): MyType = ImplicitTraceObject.traceInternal(me, linesOfStackTrace, useStdOut_? = true)

  /**
    * A fatal assertion, but with the function name after the object. 1.assert( _ + 2 = 3 )
    * Terminates the program with exit code "7"
    *
    * @param assertion the assertion that must be true for the program to run
    * @param message the message to be printed to standard error on assertion failure
    */
  final def assert(assertion: (MyType) => Boolean, message: String) = {
    if(! assertion(me) && Debug.fatalAssertOn_?) {
      ImplicitTraceObject.traceInternal(me, Int.MaxValue) // trace the max number of lines of stack trace to std error
    }
    System.exit(7)
  }
  /**
    * A fatal assertion, but with the function name after the object. 1.assert( _ + 2 = 3 )
    * Terminates the program with exit code "7"
    *
    * @param assertion the assertion that must be true for the program to run
    * @param message the message to be printed  to standard out on assertion failure
    */
  final def assertStdOut(assertion: (MyType) => Boolean, message: String) = {
    if(! assertion(me) && Debug.fatalAssertOn_?) {
      ImplicitTraceObject.traceInternal(me, Int.MaxValue, useStdOut_? = true) // trace the max number of lines of stack trace to std out
    }
    System.exit(7)
  }
  final def assertEquals(other: MyType, message: String) = {
    val assertionTrue_? = this == other
    if(! assertionTrue_? && Debug.fatalAssertOn_?) {
      ImplicitTraceObject.traceInternal(me, Int.MaxValue) // trace the max number of lines of stack trace to std error
    }
    System.exit(7)
  }
  final def assertEqualsStdOut(other: MyType, message: String) = {
    val assertionTrue_? = this == other
    if(! assertionTrue_? && Debug.fatalAssertOn_?) {
      ImplicitTraceObject.traceInternal(me, Int.MaxValue, useStdOut_? = true) // trace the max number of lines of stack trace to std out
    }
    System.exit(7)
  }
  final def assertNonFatal(assertion: (MyType) => Boolean, message: String) = {
    val assertionTrue_? = assertion(me)
    if(! assertionTrue_? && Debug.nonFatalAssertOn_?) {
      ImplicitTraceObject.traceInternal(me, Int.MaxValue) // trace the max number of lines of stack trace to std error
    }
  }
  final def assertNonFatalStdOut(assertion: (MyType) => Boolean, message: String) = {
    val assertionTrue_? = assertion(me)
    if(! assertionTrue_? && Debug.nonFatalAssertOn_?) {
      ImplicitTraceObject.traceInternal(me, Int.MaxValue, useStdOut_? = true) // trace the max number of lines of stack trace to std out
    }
  }
  final def assertNonFatalEquals(other: MyType, message: String) = {
    val assertionTrue_? = this == other
    if(! assertionTrue_? && Debug.nonFatalAssertOn_?) {
      ImplicitTraceObject.traceInternal(me, Int.MaxValue) // trace the max number of lines of stack trace to std error
    }
  }
  final def assertNonFatalEqualsStdOut(other: MyType, message: String) = {
    val assertionTrue_? = this == other
    if(! assertionTrue_? && Debug.nonFatalAssertOn_?) {
      ImplicitTraceObject.traceInternal(me, Int.MaxValue, useStdOut_? = true) // trace the max number of lines of stack trace to std out
    }
  }
}

/**
  * Contains static methods for ImplicitTraceObject
  */
object ImplicitTraceObject {
  /**
    * The offset of the first line from the base of the stack trace.
    * The +1 is necessary because the method call traceInternal adds one to the offset of the stack trace.
    */
  val newStackOffset = Debug.stackOffset + 1
  /**
    * Prints out the object with N lines of stack trace
    *
    * @param toPrintOutNullable the object to print out. May be "null".
    * @param numStackLinesIntended N, the number of lines of stack trace intended. Defaults to zero actual lines of stack trace for negative values.
    * @param useStdOut_? Whether to use standard out for trace (as opposed to std error). Uses standard error by default.
    * @return The thing that was put into the first parameter
    */
  protected[debug] final def traceInternal[A](toPrintOutNullable: A, numStackLinesIntended: Int, useStdOut_? : Boolean = false): A = {
    if( !Debug.traceErrOn_? && !useStdOut_?) {
      return toPrintOutNullable // if tracing to standard error is off and we trace to standard error, return
    }
    if( !Debug.traceOutOn_? && useStdOut_?) {
      return toPrintOutNullable // if tracing to standard out is off and we trace to out, return
    }
    val numStackLines = if(numStackLinesIntended > 0) {
      numStackLinesIntended // the number of lines must be positive or zero
    } else {
      0
    }
    val stack = Thread.currentThread().getStackTrace
    val toPrintOut: String = if(toPrintOutNullable == null) {"null"} else {toPrintOutNullable.toString}
    var toPrint = "\"" + toPrintOut + "\"" + " in thread " + Thread.currentThread().getName + ":"
    for (row <- 0 to Math.min(numStackLines - 1, stack.length - 1 - newStackOffset)) {
      val lineNumber = newStackOffset + row
      val stackLine = stack(lineNumber)
      // The java stack traces use a tab character, not a space
      val tab = "\t"
      toPrint += "\n" + tab + "at " + stackLine
    }
    toPrint += "\n"
    if(useStdOut_?) {
      System.out.println(toPrint)
    } else {
      System.err.println(toPrint)
    }
    toPrintOutNullable // return the origional thing, even if it is null.
  }
}