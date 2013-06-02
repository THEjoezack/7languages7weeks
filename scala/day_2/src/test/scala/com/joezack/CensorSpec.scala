package com.joezack

import org.scalatest.FunSpec

class CensorSpec extends FunSpec {
  describe("Censor")  {
    val censor = new { val storageName = "Testing" } with Censor

    it("should return null when input is null") {
      assert(null == censor.Replace(null))
    }

    it("should return empty string when input is empty string") {
      assert("" == censor.Replace(""))
    }

    it("should replace all words 'shoot' with 'pucky' and 'darn' with 'beans'") {
      var input = "shoot beans shoot beans boom boom"
      val output = censor.Replace(input)
      assert("pucky beans pucky beans boom boom" == output,output)
    }

    it("should only replace whole words, not when it's part of a bigger word") {
      var input = "shooter and beaner"
      val output = censor.Replace(input)
      assert(input == output,output)
    }

    it("should not modify the original string") {
      var input = "shoot"
      val output = censor.Replace(input)
      assert(input != output,output)
    }
  }
}
