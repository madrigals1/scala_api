package com.agoda.utils.models

case class HotPoint(from: Point, direction: Point) {
  def isEmpty: Boolean = from.empty && direction.empty
}
