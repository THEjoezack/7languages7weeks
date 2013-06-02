package com.joezack

import org.scalatest.FunSpec
import org.scalatest.BeforeAndAfter

class BeanCounterSpec extends FunSpec with BeforeAndAfter {
  describe("BeanCounter") {
    it("should accurately count letters") {
      val list = List("test","test2")
      assert(9 == BeanCounter.countLetters(list))
    }

    it("should return 0 for nil") {
      val list = Nil
      assert(0 == BeanCounter.countLetters(list))
    }
  }
}
