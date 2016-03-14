package info.collaboration_station.debug.testing

import java.io._

import info.collaboration_station.debug._
import org.scalatest._
// import info.collaboration_station.debug.testing.TestingUtils // this import comes from debugtrace/test/scala

class StackSpec extends FlatSpec {

  "one plus one" should "equal two" in {
    assertResult(2) {
      1 + 1
    }
    // fail("I've got a bad feeling about this") // to force failure
  }

  "Enabling trace to std err" should "allow tracing to std err" in {
    Debug.traceErrOn_!
    val traceMessage = {
      val originalErr: PrintStream = System.err;
      // To get it back later
      val baosErr: ByteArrayOutputStream = new ByteArrayOutputStream();
      // replaces standard error with new PrintStream
      val newErr: PrintStream = new PrintStream(baosErr)
      System.setErr(newErr)
      "Hello  World".trace; // write stuff to System.err
      System.err.flush()
      System.setErr(originalErr); // So you can print again
      val baisErr: ByteArrayInputStream = new ByteArrayInputStream(baosErr.toByteArray())
      val bfReaderErr = new BufferedReader(new InputStreamReader(baisErr))
      // (bfReaderErr == null).trace // must be false
      val message = TestingUtils.getMessage(bfReaderErr)
      // (message == null).trace // must be false
      val didTraceMessage_? : Boolean = (message.length != 0)
      assert(didTraceMessage_?)
    }
  }

  "Disabling trace to std err" should "disable tracing to std err" in {
    Debug.traceErrOff_!
    val traceMessage = {
      val originalErr: PrintStream = System.err;
      // To get it back later
      val baosErr: ByteArrayOutputStream = new ByteArrayOutputStream();
      // replaces standard error with new PrintStream
      val newErr: PrintStream = new PrintStream(baosErr)
      System.setErr(newErr)
      "Hello  World".trace; // write stuff to System.err
      System.err.flush()
      System.setErr(originalErr); // So you can print again
      val baisErr: ByteArrayInputStream = new ByteArrayInputStream(baosErr.toByteArray())
      val bfReaderErr = new BufferedReader(new InputStreamReader(baisErr))
      // (bfReaderErr == null).trace // must be false
      val message = TestingUtils.getMessage(bfReaderErr)
      // (message == null).trace // must be false
      assert(message.length == 0) // no message was obtained
    }
  }


  "Enabling trace to std out" should "allow tracing to std out" in {
    Debug.traceOutOn_!
    val traceMessage = {
      val originalOut: PrintStream = System.out;
      // To get it back later
      val baosOut: ByteArrayOutputStream = new ByteArrayOutputStream();
      // replaces standard error with new PrintStream
      val newOut: PrintStream = new PrintStream(baosOut)
      System.setOut(newOut)
      "Hello  World".traceStdOut; // write stuff to System.out
      System.out.flush()
      System.setOut(originalOut); // So you can print again
      val baisOut: ByteArrayInputStream = new ByteArrayInputStream(baosOut.toByteArray())
      val bfReaderOut = new BufferedReader(new InputStreamReader(baisOut))
      // (bfReaderOut == null).trace // must be false
      val message = TestingUtils.getMessage(bfReaderOut)
      // (message == null).trace // must be false
      val didTraceMessage_? : Boolean = (message.length != 0)
      assert(didTraceMessage_?)
    }
  }

  "Disabling trace to std out" should "disable tracingto std out" in {
    Debug.traceOutOff_!
    val traceMessage = {
      val originalOut: PrintStream = System.out;
      // To get it back later
      val baosOut: ByteArrayOutputStream = new ByteArrayOutputStream();
      // replaces standard error with new PrintStream
      val newOut: PrintStream = new PrintStream(baosOut)
      System.setOut(newOut)
      "Hello  World".traceStdOut; // write stuff to System.out
      System.out.flush()
      System.setOut(originalOut); // So you can print again
      val baisOut: ByteArrayInputStream = new ByteArrayInputStream(baosOut.toByteArray())
      val bfReaderOut = new BufferedReader(new InputStreamReader(baisOut))
      // (bfReaderOut == null).trace // must be false
      val message = TestingUtils.getMessage(bfReaderOut)
      // (message == null).trace // must be false
      assert(message.length == 0) // no message was obtained
    }
  }
/*
  "An empty Set" should "should have size 0" in {
    assert(Set.empty.size == 0)
  }

  it should "produce NoSuchElementException when head is invoked" in {
    intercept[NoSuchElementException] {
      Set.empty.head
    }
  }

  "A Stack" should "pop values in last-in-first-out order" in {
    val stack = new Stack[Int]
    stack.push(1)
    stack.push(2)
    assert(stack.pop() === 2)
    assert(stack.pop() === 1)
  }

  ignore should "throw NoSuchElementException if an empty stack is popped" in {
    val emptyStack = new Stack[String]
    intercept[NoSuchElementException] {
      emptyStack.pop()
    }
  }
*/
}

// http://www.scalatest.org/user_guide/tagging_your_tests

/*
import org.scalatest.FunSuite

class SetSuite extends FunSuite {

  test("An empty Set should have size 0") {
    assert(Set.empty.size == 0)
  }

  test("Invoking head on an empty Set should produce NoSuchElementException") {
    intercept[NoSuchElementException] {
      Set.empty.head
    }
  }
}
*/