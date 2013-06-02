package com.joezack

import scala.xml.XML

trait Censor {

  protected[Censor] var blackList = scala.collection.mutable.Map(
    "shoot" -> "pucky",
    "darn" -> "beans"
  )

  def AddReplacementWords(fileName:String) {
    val xml = XML.loadFile(fileName)
    val words = (xml \\ "xml" \\ "replacements" \\ "replace")

    words.foreach(
      w => blackList.put(
        (w \\ "@dirty").toString(),
        (w \\ "@clean").toString())
    )
  }

  def Replace(input:String) : String = {
    if(input == null) return null
    var output = input.toString()
    blackList.foreach(
      p => output = output.replaceAll("\\b" + p._1 + "\\b",p._2)
    )
    output
  }
}