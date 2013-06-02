package com.joezack

object BeanCounter {
  def countLetters(list:List[String]) : Int = {
    if(list == Nil) return 0
    list.foldLeft(0)((sum,value) => sum + value.length())
  }
}