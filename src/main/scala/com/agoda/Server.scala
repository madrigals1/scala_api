package com.agoda

import akka.actor.ActorSystem
import akka.http.scaladsl.Http

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}
import com.agoda.controllers.Maze

object Server extends App {
  implicit val system: ActorSystem = ActorSystem("SimpleAPI")
  implicit val executor: ExecutionContext = system.dispatcher

  val bindingFuture = Http().bindAndHandle(Maze.routes, Constants.host, Constants.port)
  bindingFuture.onComplete {
    case Success(_) => println(s"listening to http://${Constants.host}:${Constants.port}")
    case Failure(error) => println(s"error: ${error.getMessage}")
  }
}
