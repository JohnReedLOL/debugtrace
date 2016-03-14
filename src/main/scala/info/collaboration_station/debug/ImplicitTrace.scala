package info.collaboration_station.debug

/**
  * Wrapper class for a value that can be traced
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
    * Prints out this object with 1 lines of stack trace to standard error.
    * @return The thing that was just printed.
    */
  final def trace: MyType = ImplicitTraceObject.traceInternal(me, 1)
  /**
    * Prints out this object to standard error with a user specified number of lines of stack trace.
    * @param linesOfStackTrace The number of lines of stack trace.
    * @return The thing that was just printed.
    */
  final def trace(linesOfStackTrace: Int = 1): MyType = ImplicitTraceObject.traceInternal(me, linesOfStackTrace)
  /**
    * Prints out this object to standard error along with the entire stack trace.
    * @return The thing that was just printed.
    */
  final def traceStack: MyType = ImplicitTraceObject.traceInternal(me, Int.MaxValue)
  /**
    * Prints out this object with 1 lines of stack trace to standard out.
    * @return The thing that was just printed.
    */
  final def traceStdOut: MyType = ImplicitTraceObject.traceInternal(me, 1, useStdOut_? = true)
  /**
    * Prints out this object to standard out with a user specified number of lines of stack trace.
    * @param linesOfStackTrace The number of lines of stack trace.
    * @return The thing that was just printed.
    */
  final def traceStdOut(linesOfStackTrace: Int): MyType = ImplicitTraceObject.traceInternal(me, linesOfStackTrace, useStdOut_? = true)
  /**
    * Prints out this object to standard out along with the entire stack trace.
    * @return The thing that was just printed.
    */
  final def traceStackStdOut: MyType = ImplicitTraceObject.traceInternal(me, Int.MaxValue, useStdOut_? = true)
  /**
    * A fatal assertion, but with the function name after the object. 1.assert( _ + 2 = 3 )
    * Terminates the program with exit code "7"
    * @param assertion the assertion that must be true for the program to run
    * @param message the message to be printed to standard error on assertion failure
    */
  final def assert(assertion: (MyType) => Boolean, message: String, maxLines: Int = Int.MaxValue): MyType = {
    if(! assertion(me) && Debug.fatalAssertOn_?) {
      ImplicitTraceObject.traceInternalAssert(message, maxLines) // trace the max number of lines of stack trace to std error
      System.exit(7)
    }
    me
  }
  /**
    * A fatal assertion, but with the function name after the object. 1.assert( _ + 2 = 3 )
    * Terminates the program with exit code "7"
    * @param assertion the assertion that must be true for the program to run
    * @param message the message to be printed  to standard out on assertion failure
    */
  final def assertStdOut(assertion: (MyType) => Boolean, message: String, maxLines: Int = Int.MaxValue): MyType = {
    if(! assertion(me) && Debug.fatalAssertOn_?) {
      ImplicitTraceObject.traceInternalAssert(message, maxLines, useStdOut_? = true) // trace the max number of lines of stack trace to std out
      System.exit(7)
    }
    me
  }
  final def assertEquals(other: MyType, message: String, maxLines: Int = Int.MaxValue): MyType = {
    val assertionTrue_? = (me.equals(other))
    if(! assertionTrue_? && Debug.fatalAssertOn_?) {
      ImplicitTraceObject.traceInternalAssert(message, maxLines) // trace the max number of lines of stack trace to std error
      System.exit(7)
    }
    me
  }
  final def assertEqualsStdOut(other: MyType, message: String, maxLines: Int = Int.MaxValue): MyType = {
    val assertionTrue_? = (me.equals(other))
    if(! assertionTrue_? && Debug.fatalAssertOn_?) {
      ImplicitTraceObject.traceInternalAssert(message, maxLines, useStdOut_? = true) // trace the max number of lines of stack trace to std out
      System.exit(7)
    }
    me
  }
  final def assertNonFatal(assertion: (MyType) => Boolean, message: String, maxLines: Int = Int.MaxValue): MyType = {
    val assertionTrue_? = assertion(me)
    if(! assertionTrue_? && Debug.nonFatalAssertOn_?) {
      ImplicitTraceObject.traceInternalAssert(message, maxLines) // trace the max number of lines of stack trace to std error
    }
    me
  }
  final def assertNonFatalStdOut(assertion: (MyType) => Boolean, message: String, maxLines: Int = Int.MaxValue): MyType = {
    val assertionTrue_? = assertion(me)
    if(! assertionTrue_? && Debug.nonFatalAssertOn_?) {
      ImplicitTraceObject.traceInternalAssert(message, maxLines, useStdOut_? = true) // trace the max number of lines of stack trace to std out
    }
    me
  }
  final def assertNonFatalEquals(other: MyType, message: String, maxLines: Int = Int.MaxValue): MyType = {
    val assertionTrue_? = (me.equals(other))
    if(! assertionTrue_? && Debug.nonFatalAssertOn_?) {
      ImplicitTraceObject.traceInternalAssert(message, maxLines) // trace the max number of lines of stack trace to std error
    }
    me
  }
  final def assertNonFatalEqualsStdOut(other: MyType, message: String, maxLines: Int = Int.MaxValue): MyType = {
    val assertionTrue_? = (me.equals(other))
    if(! assertionTrue_? && Debug.nonFatalAssertOn_?) {
      ImplicitTraceObject.traceInternalAssert(message, maxLines, useStdOut_? = true) // trace the max number of lines of stack trace to std out
    }
    me
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
    * Prints out the object with N lines of stack trace. Do not use with assertions.
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
  /**
    * Prints out the object with N lines of stack trace. Meant to be used only for asserts.
    * @param toPrintOutNullable the object to print out. May be "null".
    * @param numStackLinesIntended N, the number of lines of stack trace intended. Defaults to zero actual lines of stack trace for negative values.
    * @param useStdOut_? Whether to use standard out for trace (as opposed to std error). Uses standard error by default.
    * @return The thing that was put into the first parameter
    */
  protected[debug] final def traceInternalAssert[A](toPrintOutNullable: A, numStackLinesIntended: Int, useStdOut_? : Boolean = false): A = {
    // Disabling trace does not also disable assert. They are two separate things.
    //if( !Debug.traceErrOn_? && !useStdOut_?) {
    //  return toPrintOutNullable // if tracing to standard error is off and we trace to standard error, return
    //}
    //if( !Debug.traceOutOn_? && useStdOut_?) {
    //  return toPrintOutNullable // if tracing to standard out is off and we trace to out, return
    //}
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
    toPrint += "\n" + "^ The above stack trace leads to an assertion failure. ^" + "\n"
    if(useStdOut_?) {
      System.out.println(toPrint)
    } else {
      System.err.println(toPrint)
    }
    toPrintOutNullable // return the origional thing, even if it is null.
  }
}