package com.stacktrace.debug

/**
  * Wrapper class for a value that can be traced
  *
  * @param me the original value inside the wrapper
  * @tparam MyType the original type of the value inside the wrapper
  */
final class ImplicitTrace[MyType](val me: MyType) {
  /**
    * Prints out this thing with 0 lines of stack trace.
    *
    * @return The thing that was printed out.
    */
  final def trace0: MyType = traceN(0)
  /**
    * Prints out this thing with 1 lines of stack trace.
    *
    * @return The thing that was printed out.
    */
  final def trace1: MyType = traceN(1)
  /**
    * Prints out this thing with 2 lines of stack trace.
    *
    * @return The thing that was printed out.
    */
  final def trace2: MyType = traceN(2)
  /**
    * Prints out this thing with 3 lines of stack trace.
    *
    * @return The thing that was printed out.
    */
  final def trace3: MyType = traceN(3)
  /**
    * Prints out this thing with 4 lines of stack trace.
    *
    * @return The thing that was printed out.
    */
  final def trace4: MyType = traceN(4)
  /**
    * Prints out this thing with 5 lines of stack trace.
    *
    * @return The thing that was printed out.
    */
  final def trace5: MyType = traceN(5)

  /**
    * Add one to the stack offset because scala does not support true method aliases
    */
  val newStackOffset = stackOffset + 1

  /**
    * Prints out this thing with N lines of stack trace
    *
    * @param numStackLines N, the number of lines of stack trace.
    * @return The thing that was printed out.
    */
  private final def traceN(numStackLines: Int) = {
    require(numStackLines >= 0, "the number of lines must be positive.")
    if(debugModeOn_?) {
      val stack = Thread.currentThread().getStackTrace
      var toPrint = "\"" + me.toString + "\"" + " in thread " + Thread.currentThread().getName + ":"
      for (row <- 0 to Math.min(numStackLines - 1, stack.length - 1 - newStackOffset)) {
        val lineNumber = newStackOffset + row
        val stackLine = stack(lineNumber)
        toPrint += "\n" + "  at " + stackLine
      }
      toPrint += "\n"
      if(printToStdError_?) {
        System.err.println(toPrint)
      } else {
        System.out.println(toPrint)
      }
    }
    me
  }
}
