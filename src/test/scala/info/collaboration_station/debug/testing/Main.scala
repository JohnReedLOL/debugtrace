package info.collaboration_station.debug.testing

import info.collaboration_station.debug._
/**
  * Created by johnreed on 3/14/16.
  */

/**
  * Once you type on the Scala console in IntelliJ Community Edition,
  * hit Ctrl+Enter instead of enter to execute.
  */
object Main {
  /**
    * run this by doing "sbt test:run"
    */
  def main(args: Array[String]) {
    "Hello World 1".trace()
    "Hello World 2".traceStdOut
    Debug.trace("Hello World 3")
    "foo".assertNonFatalEquals("bar", "message")
    "foo".assertEquals("foo", "message2")
    "foo".assertEquals("bar", "message3") // exits with code 7
    assert(true)
  }
}
