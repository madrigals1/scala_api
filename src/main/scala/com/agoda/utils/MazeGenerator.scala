package com.agoda.utils

import scala.collection.mutable.ArrayBuffer
import scala.util.Random
import scala.util.control.Breaks.{break, breakable}
import com.agoda.utils.models.{Matrix, HotPoint, Point}

class MazeGenerator(width: Int, height: Int) {
  val random = new Random()

  def initialize: Matrix = {
    val tempMaze = Matrix(width, height)

    for (i <- 0 until tempMaze.width) {
      for (j <- 0 until tempMaze.height) {
        if (i % 2 == 0 || j % 2 == 0) {
          tempMaze.set(i, j, 1)
        }
      }
    }

    tempMaze
  }
  def getVisitable: ArrayBuffer[Point] = {
    val tempVisitable = new ArrayBuffer[Point]()

    for (i <- 0 until this.maze.width) {
      for (j <- 0 until this.maze.height) {
        if (i % 2 == 1 && j % 2 == 1) tempVisitable += Point(i, j)
      }
    }

    tempVisitable
  }

  val maze: Matrix = this.initialize
  val visitable: ArrayBuffer[Point] = this.getVisitable
  var hotPositions = new ArrayBuffer[HotPoint]()

  def getDirections(x: Int, y: Int): Array[Point] = {
    val directions = new ArrayBuffer[Point]()

    if (x - 2 != -1 && this.maze.get(x - 2, y) == 0) directions += Point(-2, 0)
    if (y - 2 != -1 && this.maze.get(x, y - 2) == 0) directions += Point(0, -2)
    if (x + 2 < this.maze.width && this.maze.get(x + 2, y) == 0) directions += Point(2, 0)
    if (y + 2 < this.maze.height && this.maze.get(x, y + 2) == 0) directions += Point(0, 2)

    directions.toArray
  }
  def getDirection(x: Int, y: Int, directions: Array[Point]): Point = {
    val randomIndex = random.nextInt(directions.length)

    for (i <- directions.indices) {
      if(i != randomIndex) {
        val direction = directions(i)
        hotPositions += HotPoint(Point(x, y), direction)
      }
    }

    directions(randomIndex)
  }

  def getHotPosition: HotPoint = {
    var hotPoint = HotPoint(Point(0, 0, empty = true), Point(0, 0, empty = true))

    breakable {
      while(this.hotPositions.nonEmpty) {
        val hotPosition: HotPoint = this.hotPositions.remove(0)
        val from: Point = hotPosition.from
        val direction: Point = hotPosition.direction

        if(this.maze.get(from.x + direction.x, from.y + direction.y) == 0) {
          hotPoint = hotPosition
          break
        }
      }
    }

    hotPoint
  }

  def walk(x: Int, y: Int): Unit = {
    this.maze.set(x, y, 2)
    val directions: Array[Point] = this.getDirections(x, y)

    if (directions.nonEmpty) {
      val direction: Point = this.getDirection(x, y, directions)
      val nextPosition: Point = Point(x + direction.x, y + direction.y)
      val halfNextPosition: Point = Point(x + direction.x / 2, y + direction.y / 2)

      this.visitable -= nextPosition

      this.maze.set(halfNextPosition.x, halfNextPosition.y, 2)

      this.walk(nextPosition.x, nextPosition.y)
    } else if (this.hotPositions.nonEmpty) {
      val hotPoint: HotPoint = this.getHotPosition
      if (!hotPoint.isEmpty) {
        val from: Point = hotPoint.from
        val direction: Point = hotPoint.direction
        val nextPosition: Point = Point(from.x + direction.x, from.y + direction.y)
        val halfNextPosition: Point = Point(from.x + direction.x / 2, from.y + direction.y / 2)

        visitable -= nextPosition

        this.maze.set(halfNextPosition.x, halfNextPosition.y, 2)

        this.walk(nextPosition.x, nextPosition.y)
      }
    }

    this.visitable -= Point(x, y)
  }

  def generate(): Matrix = {
    while (this.visitable.nonEmpty) {
      val position: Point = this.visitable.remove(0)

      val x: Int = position.x
      val y: Int = position.y

      this.walk(x, y)
    }
    maze.replace(2, 0)
    maze
  }
}
