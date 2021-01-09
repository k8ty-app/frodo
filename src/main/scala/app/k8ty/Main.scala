package app.k8ty

import java.io.File
import java.util.Base64
import scala.io.Source
import scala.io.StdIn.readLine

object Main extends App {

  val locations = (args.headOption match {
    case Some(file) => LazyList(file)
    case None => LazyList(
      "./src/main/resources/application.conf",
      "./src/main/resources/reference.conf"
    )
  }).map(f => new File(f))

  val theFile = locations.find(_.exists())

  theFile match {
    case None => {
      println(s"Couldn't find file at:")
      locations.foreach(f => println(f.getPath))
      System.exit(1)
    }
    case Some(file) => {
      val secrets = Source.fromFile(file).getLines()
        .filter(_.contains(s"$${?"))
        .map { line =>
          line.split("=").map(_.trim).last
            .drop(3).dropRight(1)
        }
        .toList

      val kv = secrets.map { key =>
        print(s"$key: ")
        val value = readLine()
        key -> Base64.getUrlEncoder.encodeToString(value.getBytes)
      }.filter(_._2.nonEmpty)

      println {
        txt.secret(kv.toMap)
      }

    }
  }

}
