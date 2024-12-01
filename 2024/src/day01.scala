package day01

import inputs.Input.loadFileSync
import locations.Directory.currentDir
import scala.collection.mutable.ListBuffer


@main def part1: Unit =  println(solvePart1(loadInput()))

@main def part2: Unit =  println(solvePart2(loadInput()))



def loadInput(): String = loadFileSync(s"$currentDir/../inputs/day01.txt")

def convertInputToArrays(input: String): (Array[Int], Array[Int]) =
    val lines = input.split("\n")
    val (array1, array2) = lines.map { line =>
        val numbers = line.trim.split("\\s+").map(_.toInt)
        (numbers(0), numbers(1))
    }.unzip
    (array1.toArray, array2.toArray)

def solvePart1(input: String): Int =
    val (array1, array2) = convertInputToArrays(input)

    val sortedArray1 = array1.sortWith((a, b) => a < b)
    val sortedArray2 = array2.sortWith((a, b) => a < b)

    val lengthArray1 = sortedArray1.length
    val lengthArray2 = sortedArray2.length

    var i = 0
    var j = 0

    var totalDistance = 0
    while i < lengthArray1 && j < lengthArray2 do
        totalDistance += Math.abs(sortedArray1(i) - sortedArray2(j))
        i += 1
        j += 1

    totalDistance

def solvePart2(input: String): Int =
    val (array1, array2) = convertInputToArrays(input)

    val occurences = ListBuffer[Int]()

    for i <- 0 until array1.length do
        val array1Value = array1(i)
        val occurence = array2.count(_ == array1Value)
        occurences += (array1Value * occurence)

    occurences.sum