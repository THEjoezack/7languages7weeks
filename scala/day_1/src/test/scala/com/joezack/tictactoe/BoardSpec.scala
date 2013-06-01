package com.joezack.tictactoe

import org.scalatest.FunSpec
import org.scalatest.BeforeAndAfter

class BoardSpec extends FunSpec with BeforeAndAfter {
  var board: Board = _
  describe("Boards") {
    before {
      board = new Board()
    }

    describe("when initialized") {
      it("should have no tokens set") {
        for (i <- 0 to (board.Size - 1)) {
          for (j <- 0 to (board.Size - 1)) {
            if(board.get(i,j) != Token.Blank) fail()
          }
        }
      }
    }

    it("should let you set get and set tokens") {
      board.set(0,0,Token.X)
      assert(board.get(0,0) == Token.X)
    }

    it("should throw exception when trying to set blank token") {
      intercept[IllegalArgumentException](board.set(0,0,Token.Blank))
    }

    it("should throw exception when trying to set null token") {
      intercept[IllegalArgumentException](board.set(2,1,null))
    }

    it("should throw exception when trying to set index out of bounds") {
      intercept[IndexOutOfBoundsException](board.set(-1,4,Token.X))
    }

    it("should throw exception when trying to set a spot that's already been set") {
      board.set(1,1,Token.X)
      intercept[IllegalArgumentException](board.set(1,1,Token.O))
    }

  }
}