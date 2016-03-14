package info.collaboration_station
import info.collaboration_station.debug._
/**
  * Created by johnreed on 3/12/16.
  */
object Main {
  def main(args: Array[String]) {
    "Hello World".trace
    "Hello World".traceStdOut
    "foo".assertNonFatalEquals("bar", "message")
    "foo".assertEquals("foo", "message2")
    "foo".assertEquals("bar", "message3") // exits with code 7
  }
}
