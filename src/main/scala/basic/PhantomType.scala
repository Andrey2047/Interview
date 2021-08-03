package basic

//https://medium.com/@maximilianofelice/builder-pattern-in-scala-with-phantom-types-3e29a167e863
class PhantomType {


  //ex. 1
  sealed trait DoorState
  sealed trait Open extends DoorState
  sealed trait Closed extends DoorState


  case class Door[State <: DoorState](){
    def open(implicit ev: State =:= Closed) = Door[Open]()
    def close(implicit ev: State =:= Open) = Door[Closed]()
  }

  // ex.2
  object Chef {

    sealed trait Pizza
    object Pizza {
      sealed trait EmptyPizza extends Pizza
      sealed trait Cheese extends Pizza
      sealed trait Topping extends Pizza
      sealed trait Dough extends Pizza

      type FullPizza = EmptyPizza with Cheese with Topping with Dough
    }
  }

  class Food[Pizza <: Chef.Pizza](ingredients: Seq[String] = Seq()) {
    import Chef.Pizza._

    def addCheese(cheeseType: String): Food[Pizza with Cheese] = new Food(ingredients :+ cheeseType)

    def addTopping(toppingType: String): Food[Pizza with Topping] = new Food(ingredients :+ toppingType)

    def addDough: Food[Pizza with Dough] = new Food(ingredients :+ "dough")

    def build(implicit ev: Pizza =:= FullPizza): Food[Pizza] = new Food(ingredients)
  }



  def main(args: Array[String]): Unit = {
    val openDoor = Door[Open]()
    val closedDoor = openDoor.close

    //will not compile
    //closedDoor.close

    //will compile
    closedDoor.open
  }

}
