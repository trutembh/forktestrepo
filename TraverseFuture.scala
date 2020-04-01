package Scala_Cat

import cats.Traverse
import cats.data.Writer

import scala.concurrent.Future
import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object TraverseFuture extends App{

  ///two functions are used cats.traverse and cats.sequence
  val country=List(
    "East China",
    "India",
    "Usa",
    "WI"
  )
  def populationIncreased(name:String):Future[Int]=
    Future(name.length*100)

  //Implementation with the help of foldmap
  val FinalSum: Future[List[Int]] =
    country.foldLeft(Future(List.empty[Int])) {
      (accum, host) =>
        val uptime = populationIncreased(host)
        for {
          accum <- accum
          uptime <- uptime
        } yield accum :+ uptime
    }

  println(FinalSum)
 // Await.result(FinalSum, 1.second)

  ///////equvivalent of above methods
  val allUptimes: Future[List[Int]] =
    Future.traverse(country)(populationIncreased)

  println(allUptimes)
  //Await.result(allUptimes, 1.second)
  //////use of traverse function
  //////!)List(A).......2)A===>Future[B] .......3)Future[List[B]]

  /////////////////////////////////////////////////////////////////////////////////////
  /////use of sequence function
  ///////convert List[Future[A]] to future[List[A]]
  import cats.instances.list._
  import cats.instances.try_._
  import scala.util.{Success, Try}
  val listOfTries: List[Try[String]] = List(Success("a"), Success("b"), Success("c"))
   val tryOfList = Traverse[List].sequence(listOfTries)
  println("===========")
  println(listOfTries)
  println(tryOfList)

  //A traversal is similar to a fold in that both take some data structure and
  // apply a function to the data within in order to produce a result.
  // The difference is that traverse preserves the original structure,
  // whereas foldMap discards the structure and replaces it with the operations of a monoid.


//////using only cat framework
  import cats.Traverse
  import cats.instances.future._ // for Applicative
  import cats.instances.list._ // for Traverse
  val numbers = List(Future(1), Future(2), Future(3))
  val numbers2: Future[List[Int]] =
    Traverse[List].sequence(numbers)

  Await.result(numbers2, 1.second)
  println(numbers2)

}
