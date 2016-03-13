package com.stacktrace

/**
  * Poor man's debugging utility. Works best when run in "Debug" mode. For best results, use an IDE: http://i.imgur.com/gfi1OYx.png
  */
package object debug {

  /**
    * Stack offset is 2 because the first row in the stack trace is Thread and the second row is internal call.
    */
  protected[debug] val stackOffset = 2

  def debugModeOn_? : Boolean = {
    debugMode_!
  }

  /**
    * The exclamation point means "not pure".
    */
  private var debugMode_! = true

  private var printToStdError_! = true

  def printToStdError_? : Boolean = {
    printToStdError_!
  }

  /**
    * Turns on debug mode. Print statements with stack traces are visible and fatal assertions are enabled.
    * Note: The exclamation point means "not pure".
    */
  def debugModeOn_!(): Unit = {
    debugMode_! = true
  }

  /**
    * Turns off debug mode. Print statements with stack traces are invisible and fatal assertions are disabled.
    */
  def debugModeOff_!(): Unit = {
    debugMode_! = false
  }

  /**
    * Prints to standard out. Useful when you want black letters.
    */
  def useStdOut_!(): Unit = {
    printToStdError_! = false
  }

  /**
    * Prints to standard error. Useful when you want to no buffering of error messages.
    */
  def useStdError_!(): Unit = {
    printToStdError_! = true
  }

  /**
    * Prints a line with a stack trace. For sample usage, see: http://i.imgur.com/gfi1OYx.png
    *
    * @param message the message to print
    */
  def trace(message: String) = {
    if(debugMode_!) {
      val stack = Thread.currentThread().getStackTrace
      val stackLine = stack(stackOffset)
      val printLine1 = "\"" + message + "\"" + " in thread " + Thread.currentThread().getName + ":" + "\n"
      val printLine2 = "  at " + stackLine
      val toPrint = printLine1 + printLine2 + "\n"
      if(printToStdError_!) {
        System.err.println(toPrint)
      } else {
        System.out.println(toPrint)
      }
    }
  }

  /**
    * Prints a line with a certain numbers of rows of stack trace
    *
    * @param message the message to print
    * @param numStackLines the numbers of rows of stack trace
    */
  def trace(message: String, numStackLines: Int) = {
    require(numStackLines >= 0, "the number of lines must be positive.")
    if(debugMode_!) {
      val stack = Thread.currentThread().getStackTrace
      var toPrint = "\"" + message + "\"" + " in thread " + Thread.currentThread().getName + ":"
      for (row <- 0 to Math.min(numStackLines - 1, stack.length - 1 - stackOffset)) {
        val lineNumber = stackOffset + row
        val stackLine = stack(lineNumber)
        toPrint += "\n" + "  at " + stackLine
      }
      toPrint += "\n"
      if(printToStdError_!) {
        System.err.println(toPrint)
      } else {
        System.out.println(toPrint)
      }
    }
  }

  /**
    * Fatal assertion with stack trace. Exits with error code "7". Always prints to standard error.
    *
    * @param assertion test to see if this is true
    * @param message message to print if this if false
    */
  def assrt(assertion: Boolean, message: String) = {
    if(debugMode_!) {
      if(! assertion) {
        var toPrint = "\"" + message + "\"" + " in thread " + Thread.currentThread().getName + ":"
        val stack = Thread.currentThread().getStackTrace
        for (row <- 0 to stack.length - 1 - stackOffset) {
          val lineNumber = stackOffset + row
          val stackLine = stack(lineNumber)
          toPrint += "\n" + "  at " + stackLine
        }
        toPrint += "\n" + "  This application failed due to a fatal assertion." + "\n"
        System.err.println(toPrint)
        System.exit(7)
      }
    }
  }

  /**
    * Fatal assertion with stack trace. Exits with error code "7". Always prints to standard out.
    * Use this with "useStdOut" for black error messages (easier to read)
    *
    * @param assertion test to see if this is true
    * @param message message to print if this if false
    */
  def assrtStdOut(assertion: Boolean, message: String) = {
    if(debugMode_!) {
      if(! assertion) {
        var toPrint = "\"" + message + "\"" + " in thread " + Thread.currentThread().getName + ":"
        val stack = Thread.currentThread().getStackTrace
        for (row <- 0 to stack.length - 1 - stackOffset) {
          val lineNumber = stackOffset + row
          val stackLine = stack(lineNumber)
          toPrint += "\n" + "  at " + stackLine
        }
        toPrint += "\n" + "  This application failed due to a fatal assertion." + "\n"
        System.out.println(toPrint)
        System.exit(7)
      }
    }
  }

  import scala.language.implicitConversions // Warning: implicit conversions language feature

  /**
    * Converter method that puts the value "me" inside ImplicitTrace for additional functionality
    *
    * @param me the value to be put inside the wrapper
    * @tparam MyType the type of the value to be put inside the wrapper
    * @return A new ImplicitTrace wrapper which appends functionality to the value.
    */
  implicit def toTraceable[MyType](me: MyType) = new com.stacktrace.debug.ImplicitTrace[MyType](me)


  /**
    * This just tests that it works.
    */
  /*
  def main(args: Array[String]) {
    Print.line("Hello")
    Print.line("World", 3)
    Print.useStdOut_!()
    Print.line("Hello Std Out")
    Print.line("World Std Out", 3)
    5.printOut0
    val five = 5
    val result = 5.printOut1
    Print.assert(result == 5, "Result must equal original value")
    5.printOut2
    Print.useStdError_!()
    5.printOut3
    Print.assertStdOut(result == 4, "Result must not equal different value")
  }
  */
}