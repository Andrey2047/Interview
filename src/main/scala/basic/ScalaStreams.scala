package basic

object ScalaStreams {

  def main(args: Array[String]): Unit = {

    lazy val fibs:Stream[Int] = 0 #:: 1 #:: fibs.zip(fibs.tail).map{ t => t._1 + t._2 }

    lazy val fibStream2:Stream[Int] =
      Stream.cons(0, Stream.cons(1, (fibStream2 zip fibStream2.tail).map(t => t._1 + t._2)))

    println(fibStream2.take(10).foreach(println))


  }

}
