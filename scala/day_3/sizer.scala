import scala.io._
import scala.actors._
import Actor._

object PageLoader {
  val linksRegex = "<a\\b+[^>]\\bhref\\b*=\\b*\"([^\"]+)\"" // Only counting hrefs using double-quotes, not worrying about invalid html...not too worried about accuracy for this

  def getPageSize(url:String) = Source.fromURL(url).mkString.length
  def getLinkCounts(url:String) = linksRegex.r.findAllIn(Source.fromURL(url).mkString).length
  def getLinks(url:String) = linksRegex.r.findAllIn(Source.fromURL(url).mkString).matchData.map(x => x.subgroups(0)).toList
}

val urls = List("http://www.amazon.com/", "http://www.twitter.com/", "http://www.google.com/", "http://www.cnn.com/")

def timeMethod(method: () => Unit) = {
  val start = System.nanoTime
  method()
  val end = System.nanoTime
  println("Method took " + (end - start)/1000000000.0 + " seconds.")
}

def getPageSizeSequentially() = {
  for(url <- urls) {
    println("Size for " + url + ": " + PageLoader.getPageSize(url))
  }
}

def getPageSizeConcurrently() = {
  getPageSizes(urls)
}

def getPageSizes(hrefs:List[String]) = {
  val caller = self
  for(url <- hrefs) {
    actor { caller ! (url, PageLoader.getPageSize(url)) }
  }
  for(i <- 1 to hrefs.size) {
  	receive {
      case (url, size) => println("Size for " + url + ": " + size)
    }
  }
}

def getLinkCount() = {
	val caller = self
	for(url <- urls) {
	  actor { caller ! (url, PageLoader.getLinkCounts(url)) }
	}
	for(i <- 1 to urls.size) {
	  receive {
	    case (url, count) => println("Link count for " + url + ": " + count)
	  }
	}
}

def getPageSizeWithChildren() = {
	val caller = self
	for(url <- urls) {
	  actor { caller ! (url, PageLoader.getLinks(url)) }
	}
	for(i <- 1 to urls.size) {
      receive {
        case (url, links) => {
          actor { caller ! ( getPageSizes(links.toString().split(",").toList) ) }// not worrying about absolute v. relative paths or bad way of differentiating hrefs
        }
        receive {
			case (url,count) => println("Size of parent's child links for " + url + " " + count.toString())
			// TODO need to handle exceptions and sum up sizes
        }
      }
	}
}

println("Sequential run:")
timeMethod { getPageSizeSequentially }
println("Concurrent run")
timeMethod { getPageSizeConcurrently }
println("Count lengths")
timeMethod { getLinkCount }
println("Concurrent page sizes with children (depth of 1)")
timeMethod { getPageSizeWithChildren }