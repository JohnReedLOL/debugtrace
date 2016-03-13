# debugtrace
Trace assert library for Scala.

Sample usage:

```scala
import info.collaboration_station.debug._

object Main {
  def main(args: Array[String]) {
    "Hello World".trace
    "Hello World".traceStdOut
  }
}
```
---
Output:

> "Hello World" in thread run-main-0:

>        at info.collaboration_station.Main$.main(Main.scala:8)
