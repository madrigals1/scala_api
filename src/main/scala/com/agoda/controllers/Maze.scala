package com.agoda.controllers

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import spray.json.RootJsonFormat
import scala.concurrent.ExecutionContext
import spray.json.DefaultJsonProtocol._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import com.agoda.utils.MazeGenerator


object Maze {
  final case class MazeSize(width: Int, height: Int)

  implicit val system: ActorSystem = ActorSystem("ScalaRESTAPI")
  implicit val executor: ExecutionContext = system.dispatcher
  implicit val mazeSizeFormat: RootJsonFormat[MazeSize] = jsonFormat2(MazeSize)

  val routes: Route = concat(
    // GET /
    path("") {
      get {
        complete("Scala REST API is working!")
      }
    },
    // POST /mazes
    path("mazes") {
      post {
        entity(as[MazeSize]) { ms =>
          val mazeGenerator = new MazeGenerator(ms.width, ms.height)
          val maze = mazeGenerator.generate()
          println(maze)
          complete("Maze created")
        }
      }
    }
  )
}
