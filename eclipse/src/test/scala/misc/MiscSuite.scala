package misc

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class implements a ScalaTest test suite for the methods in object `Insertion` that need to be implemented as part of
 * this assignment. A test suite is simply a collection of individual tests for some specific component of a program.
 *
 * A test suite is created by defining a class which extends the type `org.scalatest.FunSuite`. When running ScalaTest, it
 * will automatically find this class and execute all of its tests.
 *
 * Adding the `@RunWith` annotation enables the test suite to be executed inside eclipse using the built-in JUnit test runner.
 *
 * You have two options for running this test suite:
 *
 * - Right-click this file in eclipse and chose "Run As" - "JUnit Test"
 */

@RunWith(classOf[JUnitRunner])
class TriSuite2 extends FunSuite {

  import Misc._

  test("test fonctionACorriger") {
    assert(fonctionACorriger(List(), 1) === false)
    assert(fonctionACorriger(List(2), 1) === false)
    assert(fonctionACorriger(List(2, 56, 896, 1, 7896), 1) === true)
  }

  test("test modulus") {
    assert(modulus(List()) === List())
    assert(modulus(List(2, 1)) === List(2, 1))
    assert(modulus(List(2, 3, 6, 9, 11)) === List(2, 0, 0, 0, 2))
  }

  test("test primeTuple") {
    assert(primeTuple(1) === List())
    assert(primeTuple(3) === List((2, 1)))
    assert(primeTuple(11) === List((2, 1), (3, 2), (4, 1), (4, 3), (5, 2), (6, 1), (6, 5), (7, 4), (7, 6), (8, 3), (8, 5),
      (9, 2), (9, 4), (9, 8), (10, 1), (10, 3), (10, 7), (10, 9)))
  }

  //function listMale tests
  test("test isMale") {
    assert(false)
  }
  
  //function childrenOf tests
  test("test childrenOf") {
    assert(false)
  }
}

 	