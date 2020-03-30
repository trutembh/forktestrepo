//https://github.com/alvinj/FPTypeClasses/blob/master/src/main/scala/typeclasses/
package typeclasses.humanlike

//define behaviour in a trait that takes in generic type
trait BehavesLikeHuman[A] {
  def speak(a: A): Unit
  def eatHumanFood(a: A): Unit
}

//create instances
object BehavesLikeHumanInstances {
  //implement an instance for a Dog
  implicit val dogBehavesLikeHuman = new BehavesLikeHuman[Dog] {
    override def speak(dog: Dog): Unit = {
      println(s"I'm a Dog, my name is ${dog.name}")
    }

    override def eatHumanFood(dog: Dog): Unit = {
      println(s"I ate the food you left on the table. It was good.")
    }
  }
}

//add functions that can be used on Dog instance; use like speak(dog)
object BehavesLikeHuman {
  def speak[A](a: A)(implicit behavesLikeHumanInstances: BehavesLikeHuman[A]): Unit = {
    behavesLikeHumanInstances.speak(a)
  }

  def eatHumanFood[A](a: A)(implicit behavesLikeHumanInstances: BehavesLikeHuman[A]): Unit = {
    behavesLikeHumanInstances.eatHumanFood(a)
  }
}

//add methods to dog class; use like dog.speak
object BehavesLikeHumanSyntax {
  implicit class BehavesLikeHumanOps[A](value: A) {
    def speak(implicit behavesLikeHumanInstances: BehavesLikeHuman[A]): Unit = {
      behavesLikeHumanInstances.speak(value)
    }

    def eatHumanFood(implicit behavesLikeHumanInstances: BehavesLikeHuman[A]): Unit = {
      behavesLikeHumanInstances.eatHumanFood(value)
    }

    def speakNew(): Unit = {
      println(s"hi")
    }
  }
}


