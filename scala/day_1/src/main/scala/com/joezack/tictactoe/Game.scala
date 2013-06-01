package com.joezack.tictactoe

object Game {
  def main(args:Array[String]) {
    val board = new Board()
    var currentPlayer = Token.X

    while(Referee.isMoveAvailable(board)) {
      drawBoard(board)
      try {
        println("Your move " + playerString(currentPlayer))
        makeMove(board,currentPlayer,Console.readInt())
        currentPlayer = nextPlayer(currentPlayer)
      } catch {
        case e:Exception => println("Error! ",e)
      }
    }

    if(Referee.isTie(board)) {
      println("Tie game!")
    } else {
      val winner = Referee.getWinner(board)
      print(playerString(winner) + " is the winner!")
    }
  }

  protected[Game] def nextPlayer(currentPlayer:Token.Token):Token.Token = {
    if(currentPlayer == Token.O) return Token.X
    Token.O
  }

  protected[Game] def makeMove(board:Board,token:Token.Token,i:Int) {
    val x = i / board.Size
    val y = i - (x * board.Size)
    board.set(x,y,token)
  }

  protected[Game] def drawBoard(board:Board) {
    for(i <- 0 to board.Size - 1) {
      for(j <- 0 to board.Size - 1) {
        var token = board.get(i,j)
        if(token == Token.Blank) {
          print(i * board.Size + j)
        } else {
          print(playerString(token))
        }
        if(j != (board.Size - 1)) {
          print("|")
        }
      }
      println()
    }
  }

  protected[Game] def playerString(token:Token.Token) : String = {
    if(token == Token.X) return "X"
    "O"
  }
}
