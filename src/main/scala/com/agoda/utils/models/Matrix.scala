package com.agoda.utils.models

case class Matrix(var width: Int, var height: Int, fill: Int = 0) {
  width = this.width * 2 + 1
  height = this.height * 2 + 1

  val M: Array[Array[Int]] = Array.fill(this.width)(Array.fill(this.height)(this.fill))

  def get(x: Int, y: Int): Int = this.M(x)(y)
  def set(x: Int, y: Int, value: Int): Unit = this.M(x)(y) = value
  def replace(a: Int, b: Int): Unit = {
    for(i <- 0 until this.width) {
      for(j <- 0 until this.height) {
        if(this.M(i)(j) == a) this.M(i)(j) = b
      }
    }
  }

  override def toString: String = {
    var s = ""
    for(row <- this.M) {
      for(elem <- row) {
        if (elem == 1) {
          s += "███"
        } else if (elem == 0) {
          s += "   "
        } else {
          s += s" $elem "
        }
      }
      s += "\n"
    }
    s
  }
}
