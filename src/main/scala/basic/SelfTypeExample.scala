package basic

class SelfTypeExample {

  trait Service1 {
    def doSmt: String
  }

  trait Service2 {
    this: Service1 =>
    def doSmth2: Unit = println(doSmt)

  }

}
