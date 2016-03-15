# debugtrace
Trace debugging and assertion tool for Scala.

____________________________________________________________________________________________________________________

Example:

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

See screenshot (coming soon)

____________________________________________________________________________________________________________________

Dependencies:

- None (except for Scala standard library). 

- Works best when run with an IDE that supports click-able stack traces. 

____________________________________________________________________________________________________________________

Instructions (for IntelliJ IDE):

- 0. Add the jar file to your project. import info.collaboration_station.debug._

- 1. Go to: Run > Edit Configurations > Add New Configuration (green plus sign)

- 2. Pick either "Application" (with a Main class) or "SBT Task" (run or test, usually)

- 3. Click the green 'Run' triangle and watch the stack traces in the console.

These instruction assume you know how to package a jar file (sbt package) and include it in your project (SBT uses the "lib" directory)

You can also put the JAR file on Dropbox (or any other public folder) and include it into your SBT project like so: 

In "build.sbt" : "libraryDependencies += "debugtrace" % "debugtrace" % "2.11" from "http://www.url.of.file.jar"

____________________________________________________________________________________________________________________

Benefits:

- Easy to locate print statements
- Convenient object oriented style syntax
- Easy to locate and remove trace statements (just Ctr-R find and replace)
- Customizable features including stack trace length and enabling/disabling of assertions and traces.

____________________________________________________________________________________________________________________

Requirements:

- Scala
- SBT
- Some sort of IDE that supports stack trace highlighing

____________________________________________________________________________________________________________________

Repository URL: Not available
____________________________________________________________________________________________________________________

More info:

See ScalaDoc in source code for in detail documentation.

____________________________________________________________________________________________________________________

Bugs:

To report or pinpoint bugs, email johnmichaelreedfas@gmail.com