package basic


//Rule of using co cont variance:

//If your type produces or contains elements of types T T parameter should be covariant +T
//If your type consumes or acts on T T parameter should be contravariant -T

object ConContVariance {

  //ex 1
  abstract class Vehicle

  case class Car() extends Vehicle

  case class Bike() extends Vehicle

  case class Parking[A](value: A)

  case class CovParking[+A](value: A)

  case class ContrParking[-A]()

  def test1: Unit = {
    val vehicleIdentity = (vehicle:Vehicle) => vehicle

    vehicleIdentity(Car())
    vehicleIdentity(Bike())


    //Incorrect
    //val carParking: Parking[Vehicle] = Parking[Car](new Car)

    val carParking: CovParking[Vehicle] = CovParking[Car](new Car)

    val contrParking: ContrParking[Car] = ContrParking[Vehicle]()

  }

  // ex 2

  abstract class Foo[-A] {
    def foo(a: A): Unit
  }

  object NoOp extends Foo[Any] {
    override def foo(a: Any): Unit = {}
  }

  class PrintFoo[A] extends Foo[A] {
    override def foo(a: A): Unit = {
      println(a.toString)
    }
  }

  def test2(): Unit = {

    val f1: Foo[Int] = NoOp

    f1.foo(12)
  }


  trait Animal
  class Dog extends Animal
  class Cat extends Animal

  //Who can heal an animal
  trait Vet[-T] {
    def heal(animal: T): Boolean
  }

  def test3(): Unit ={

    //Vet who cat heal any animal:
    //Vet[Animal] can heal any animal - in particular dogs
    val v1: Vet[Dog] = new Vet[Animal] {
      override def heal(animal: Animal): Boolean = {
        println("Your animal will be fine")
        true
      }
    }

    v1.heal(new Dog())
  }

  def main(args: Array[String]): Unit = {
    test2()
  }



}
