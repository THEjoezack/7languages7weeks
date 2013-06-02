package com.joezack

import org.scalatest.FunSpec
import scala.io

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
      var input = "shoot darn shoot beans boom boom"
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

    describe("when adding words from file") {
      val additionalWords = <xml><replacements>
        <replace dirty="dirtyWord" clean="cleanWord" />
        <replace dirty="kitty" clean="puppy" />
      </replacements></xml>
      val fileName = java.io.File.createTempFile("censorSpec","")
      val printer = new java.io.PrintWriter(fileName)
      printer.print(additionalWords)
      printer.close()
      censor.AddReplacementWords(fileName.toString())

      it("should be able to do replacements with the new pairs") {
        val output = censor.Replace("kitty dirtyWord untouched")
        val expected = "puppy cleanWord untouched"
        assert(expected == output, output)
      }

      it("should still be able to do replacements with the old pairs") {
        val output = censor.Replace("shoot and darn")
        val expected = "pucky and beans"
        assert(expected == output, output)
      }
    }
  }
}