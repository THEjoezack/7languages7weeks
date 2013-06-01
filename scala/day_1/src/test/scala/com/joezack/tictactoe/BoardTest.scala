package com.joezack.tictactoe

import org.scalatest.FunSpec
import org.scalatest.BeforeAndAfter

class BoardTest extends FunSpec with BeforeAndAfter {
  var board: Board = _
  describe("Board") {
    before {
      board = new Board()
    }

    it("should let you set get and set tokens") {
      board.set(0,0,Token.X)
      assert(board.get(0,0) == Token.X)
    }

    it("should have no tokens set initially") {
      for (i <- 0 to (board.Size - 1)) {
        for (j <- 0 to (board.Size - 1)) {
          if(board.get(i,j) != null) fail()
        }
      }
    }

    it("should throw exception when trying to set null token") {
      this.intercept[IllegalArgumentException](board.set(0,0,null))
    }

    it("should throw exception when trying to set index out of bounds") {
      this.intercept[IndexOutOfBoundsException](board.set(-1,4,null))
    }

  }
}