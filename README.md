# debugtrace
Trace assert library for Scala.

Sample usage:

```scala
import info.collaboration_station.debug._

object Main {
  def main(args: Array[String]) {
    Debug.enableEverything_!()
    "Hello World 1".trace // 1 line of stack trace
    // "Hello World 2".traceStdOut
    "Hello World 3".trace(3) // 3 lines of stack trace
    Debug.trace("Hello World 4")
    Debug.trace("Hello World 5", 2) // 2 lines of stack trace
    "foo".assertNonFatalEquals("bar", "assertFailure1", maxLines = 2)
    "foo".assertEquals("foo", "assertFailure2")
    2.assert( _ + 1 == 3, "2 + 1 = 3")
    Debug.fatalAssertOff_!() // disables fatal assert
    "foo".assertEquals("bar", "message3") // assert cancelled
  }
}
```
---
Output:

```scala
	"Hello World 1" in thread pool-4-thread-6-ScalaTest-running-StackSpec:
		at info.collaboration_station.debug.testing.StackSpec$$anonfun$8.apply$mcV$sp(StackSpec.scala:149)

	"Hello World 3" in thread pool-4-thread-6-ScalaTest-running-StackSpec:
		at info.collaboration_station.debug.testing.StackSpec$$anonfun$8.apply$mcV$sp(StackSpec.scala:151)
		at info.collaboration_station.debug.testing.StackSpec$$anonfun$8.apply(StackSpec.scala:147)
		at info.collaboration_station.debug.testing.StackSpec$$anonfun$8.apply(StackSpec.scala:147)

	"Hello World 4" in thread pool-4-thread-6-ScalaTest-running-StackSpec:
		at info.collaboration_station.debug.testing.StackSpec$$anonfun$8.apply$mcV$sp(StackSpec.scala:152)

	"Hello World 5" in thread pool-4-thread-6-ScalaTest-running-StackSpec:
		at info.collaboration_station.debug.testing.StackSpec$$anonfun$8.apply$mcV$sp(StackSpec.scala:153)
		at info.collaboration_station.debug.testing.StackSpec$$anonfun$8.apply(StackSpec.scala:147)

	"assertFailure1" in thread pool-4-thread-6-ScalaTest-running-StackSpec:
		at info.collaboration_station.debug.testing.StackSpec$$anonfun$8.apply$mcV$sp(StackSpec.scala:154)
		at info.collaboration_station.debug.testing.StackSpec$$anonfun$8.apply(StackSpec.scala:147)
	^ The above stack trace leads to an assertion failure. ^
```