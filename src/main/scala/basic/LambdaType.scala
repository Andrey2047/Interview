package basic

class LambdaType {

  trait Functor[F[_]]

  type F5 = Functor[({ type T[A] = Map[Int, A] })#T]

}
