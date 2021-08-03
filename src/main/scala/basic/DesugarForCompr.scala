package basic

class DesugarForCompr {

  def main(args: Array[String]): Unit = {

    //for(x <- List(1,2,3)) yield x*x

    List(1,2,3).map {x => x*x}

    //for(x <- List(1,2,3)) println(x)
    List(1, 2, 3).foreach(println)

    //for(x <- List(1,2,3); y <- List(4,5,6)) yield x*y

    List(1,2,3).flatMap(x => List(4,5,6).map(y => x*y))

    //for(x <- List(1,2,3); y <- List(4,5,6); z <- List(7,8,9)) yield x*y

    List(1,2,3).flatMap(x => List(4,5,6).flatMap(y => List(7,8,9).map(z => x*y*z)))

  }

}
