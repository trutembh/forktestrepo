package typeclasses.humanlike

import cats.Show
import cats.syntax.show._

object HumanLikeDriverWithCats extends App {

  implicit val speakShow = Show.show[Dog] {
    dog => s"I'm a Dog, my name is ${dog.name}"
  }

  /*implicit val eatHumanFoodShow = Show.show[Dog] {
    dog => s"I ate the food you left on the table. It was good."
  }*/

  val rover = Dog("Rover")
  println(rover.show)
}
