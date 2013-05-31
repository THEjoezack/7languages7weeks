package com.joezack.tictactoe

object Referee {
  def getWinner(board:Board) : Token.Value = {
    if(isWinner(board,Token.X)) return Token.X
    if(isWinner(board,Token.O)) return Token.O
    null
  }

  def isTie(board:Board) : Boolean = {
    isMoveAvailable(board) && getWinner(board) == null
  }

  def isMoveAvailable(board:Board) : Boolean = {
    for(i <- 0 to board.Size - 1) {
      for(j <- 0 to board.Size - 1) {
        if(board.get(i,j) == null) return true
      }
    }
    false
  }

  protected[Referee] def isWinner(board:Board, token:Token.Token) : Boolean = {
    horizontalWin(board,token) || verticalWin(board,token) || diagonalWin(board,token)
  }

  protected[Referee] def horizontalWin(board:Board, token:Token.Token) : Boolean = {
    for(i <- 0 to board.Size - 1) {
      if (board.get(i,0) == token && board.get(0,1) == token && board.get(0,2) == token)return true
      allMatch(token,board.get(i,0),board.get(0,1),board.get(0,2))
    }
    false
  }

  protected[Referee] def verticalWin(board:Board, token:Token.Token) : Boolean = {
    for(i <- 0 to board.Size - 1) {
      if(allMatch(token,board.get(i,0),board.get(i,1),board.get(i,2))) return true
    }
    false
  }

  protected[Referee] def diagonalWin(board:Board, token:Token.Token) : Boolean = {
    allMatch(token,board.get(0,0),board.get(1,1),board.get(2,2)) ||
      allMatch(token,board.get(2,0),board.get(1,1),board.get(0,2))
  }

  protected[Referee] def allMatch(tokens: Token.Token*) : Boolean = {
    tokens.distinct.length == 1
  }
}