package info.collaboration_station.test

/**
  * Created by johnreed on 3/14/16.
  */
import info.collaboration_station.debug._

/**
  * Once you type on the Scala console in IntelliJ Community Edition,
  * hit Ctrl+Enter instead of enter to execute.
  */
object Main {
  // changed name from "main" to "mainT" for ease of use as library
  def mainT(args: Array[String]) {
    "Hello World 1".trace()
    "Hello World 2".traceStdOut
    Debug.trace("Hello World 3")
    "foo".assertNonFatalEquals("bar", "message")
    "foo".assertEquals("foo", "message2")
    "foo".assertEquals("bar", "message3") // exits with code 7
    assert(true)
  }
}
