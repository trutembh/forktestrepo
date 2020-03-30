//https://github.com/alvinj/FPTypeClasses/tree/master/src/main/scala/typeclasses
//https://alvinalexander.com/scala/fp-book/type-classes-101-introduction
package typeclasses.humanlike

sealed trait Animal
final case class Dog(name: String) extends Animal

object HumanLikeDriver extends App {

  import BehavesLikeHumanInstances.dogBehavesLikeHuman
  val rover = Dog("Rover")
  //apply functions to Dog Instance
  BehavesLikeHuman.speak(rover)(dogBehavesLikeHuman)
  BehavesLikeHuman.eatHumanFood(rover)(dogBehavesLikeHuman)

  import BehavesLikeHumanSyntax.BehavesLikeHumanOps
  rover.speak
  rover.eatHumanFood
  rover.speakNew

}
