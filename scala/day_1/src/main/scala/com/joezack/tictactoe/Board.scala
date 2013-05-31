package com.joezack.tictactoe

class Board {
  val Size = 3
  protected[Board] var matrix = Array.ofDim[Token.Token](Size,Size)
  initialize()

  def get(x:Int,y:Int) : Token.Token = {
    matrix(x)(y)
  }

  def set(x:Int,y:Int,t:Token.Token) {
    if(t == null) throw new IllegalArgumentException("Can't set a blank token")
    if(matrix(x)(y) != null) throw new IllegalArgumentException("Token cannot be placed, space has been used")
    matrix(x)(y) = t
  }

  protected[Board] def initialize() {
    for(i <- 0 to Size - 1) {
      for(j <- 0 to Size - 1) {
        matrix(i)(j) = null
      }
    }
  }
}