package day03

import inputs.Input.loadFileSync
import locations.Directory.currentDir
import scala.util.matching.Regex

@main def part1: Unit = println(solvePart1(loadInput()))

@main def part2: Unit = println(solvePart2(loadInput()))

def loadInput(): String = loadFileSync(s"$currentDir/../inputs/day03.txt")

def parseInstructions(instructions: List[String]): List[Int] =
    val pattern: Regex = """mul\((\d+),(\d+)\)""".r

    instructions.collect {
        case pattern(x, y) => x.toInt * y.toInt
    }

def parseAdvancedInstructions(input: String): Int = {
  val mulRegex: Regex = """mul\((\d+),(\d+)\)""".r
  val doRegex: Regex = """do\(\)""".r
  val dontRegex: Regex = """don't\(\)""".r

  val matches = """mul\(\d+,\d+\)|do\(\)|don't\(\)""".r.findAllIn(input)

  var isEnabled = true
  var sum = 0

  matches.foreach {
    case doRegex() =>
      isEnabled = true
    case dontRegex() =>
      isEnabled = false
    case mulRegex(x, y) if isEnabled =>
      sum += x.toInt * y.toInt
    case _ => // Ignore invalid or disabled instructions
  }

  sum
}

def solvePart1(input: String): Unit = 
    val pattern: Regex = """mul\(\d+,\d+\)""".r
    val matches = pattern.findAllIn(input).toList

    val instructions = parseInstructions(matches)
    println(s"Parsed instructions: ${instructions.mkString(", ")}")
    println(s"Sum: ${instructions.sum}")

def solvePart2(input: String): Unit =
    val results = parseAdvancedInstructions(input)
    println(s"Results: ${results}")