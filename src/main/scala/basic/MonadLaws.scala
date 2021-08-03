package basic

object MonadLaws {

  def main(args: Array[String]): Unit = {

    //left unit law
    //unit(x) flatMap f == f(x)

    val f: Int => Option[Int] = _ => None

    Some(1).flatMap(f) == f(1)

    //right unit law
    //monad flatMap unit == monad

    Some(1).flatMap(Option(_)) == Some(1)

    //associative law
    //(monad flatMap f) flatMap g == monad flatMap(x => f(x) flatMap g)

    val g: Int => Option[Int] = _ => None
    Some(1).flatMap(f).flatMap(g) == Some(1).flatMap(x => f(x).flatMap(g))


    val s = new L[Int] {
      override def a: Int = 1
    }


  }


  trait L[+A] {
    def a: A
  }


}
