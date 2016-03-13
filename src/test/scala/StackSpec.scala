import java.io._

import collection.mutable.Stack
import org.scalatest._
import org.scalatest.Assertions._
import info.collaboration_station.debug._
import scala.util.control.Breaks._

class StackSpec extends FlatSpec {

  "one plus one" should "equal two" in {
    assertResult(2) {
      1 + 1
    }
    // fail("I've got a bad feeling about this") // to force failure
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