package com.joezack.tictactoe

import org.scalatest.FunSpec
import org.scalatest.BeforeAndAfter

class BoardTest extends FunSpec with BeforeAndAfter {
  var board: Board = _
  describe("Board") {
    before {
      board = new Board()
    }

    it("should be blank") {
      assert(board.get(0,0) == null)
    }

    it("should throw exception when trying to set null token") {
      this.intercept(board.get(0,0) == null)
    }
  }
}