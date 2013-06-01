package com.joezack.tictactoe

import org.scalatest.FunSpec
import org.scalatest.BeforeAndAfter

class RefereeSpec extends FunSpec with BeforeAndAfter {
  describe("Referee") {

    describe("when game begins") {
      it("should know that moves are available") {
        val board = new Board()
        assert(Referee.isMoveAvailable(board))
      }

      it("should know that the game is not a tie") {
        val board = new Board()
        assert(!Referee.isTie(board))
      }
    }

    describe("when game is over") {
      it("should know there are no moves left when a game is won") {
        val board = new Board()
        val x = Token.X
        board.set(0,0,x)
        board.set(1,0,x)
        board.set(2,0,x)
        assert(!Referee.isMoveAvailable(board))
      }

      it("should recognize a horizontal win") {
        val board = new Board()
        val x = Token.X
        board.set(0,0,x)
        board.set(1,0,x)
        board.set(2,0,x)
        assert(x == Referee.getWinner(board))
      }

      it("should recognize a vertical win") {
        val board = new Board()
        val o = Token.O
        board.set(1,0,o)
        board.set(1,1,o)
        board.set(1,2,o)
        assert(o == Referee.getWinner(board))
      }

      it("should recognize a diagonal win") {
        val board = new Board()
        val x = Token.X
        board.set(0,0,x)
        board.set(1,1,x)
        board.set(2,2,x)
        assert(x == Referee.getWinner(board))
      }

      it("should know when no more moves are available") {
        val board = new Board()
        for(i <- 0 to board.Size - 1)
          for(j <- 0 to board.Size - 1)
            board.set(i,j,Token.X)
        assert(!Referee.isMoveAvailable(board))
      }

      it("should recognize a tie game") {
        val board = new Board()
        val (x,o) = (Token.X,Token.O)

        board.set(0,0,x)
        board.set(0,1,x)
        board.set(0,2,o)

        board.set(1,0,o)
        board.set(1,1,o)
        board.set(1,2,x)

        board.set(2,0,x)
        board.set(2,1,x)
        board.set(2,2,o)

        assert(!Referee.isMoveAvailable(board))
      }
    }
  }
}
