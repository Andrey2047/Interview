package basic

class TypeClass {

  trait Shower[A] {
    def show(a: A): String
  }

  case class Student(name: String)

  case class Employee(id: Int, name: String)

  object ShowerImpl {

    implicit val studentShower: Shower[Student] = new Shower[Student] {
      override def show(a: Student): String = a.name
    }


    implicit val employeeShower: Shower[Employee] = new Shower[Employee] {
      override def show(a: Employee): String = a.name + a.id
    }
  }

  implicit class LikeShower[A](a: A) {
    def show(implicit shower: Shower[A]): String = shower.show(a)
  }


  def main(args: Array[String]): Unit = {
    import ShowerImpl._

    Student("").show
  }





}
