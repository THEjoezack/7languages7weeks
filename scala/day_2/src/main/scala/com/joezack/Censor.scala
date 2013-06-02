package com.joezack

trait Censor {
  protected[Censor] val blackList = Map(
    "shoot" -> "pucky",
    "darn" -> "beans"
  )

  def Replace(input:String) : String = {
    if(input == null) return null
    var output = input.toString()
    blackList.foreach(
      p => output = output.replaceAll("\\b" + p._1 + "\\b",p._2)
    )
    output
  }
}