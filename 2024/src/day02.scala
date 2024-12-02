package day02

import inputs.Input.loadFileSync
import locations.Directory.currentDir

@main def part1: Unit = println(solvePart1(loadInput()))

@main def part2: Unit = println(solvePart2(loadInput()))


def loadInput(): String = loadFileSync(s"$currentDir/../inputs/day02.txt")


def parseInputTo2DList(input: String): List[List[Int]] =
    input.linesIterator.map { line =>
        line.trim.split("\\s+").map(_.toInt).toList
    }.toList

def isSafeWithDampener(report: List[Int]): Boolean = {
    if (isSafe(report)) return true // Check if the report is already safe.

    // Check if removing any single level makes the report safe.
    report.indices.exists { i =>
      val modifiedReport = report.take(i) ++ report.drop(i + 1) // Remove the level at index `i`.
      isSafe(modifiedReport)
    }
  }


def isSafe(report: List[Int]): Boolean = {
    if (report.size < 2) return false

    val differences = report.sliding(2).map { case List(a, b) => b - a }.toList

    val allIncreasing = differences.forall(diff => diff >= 1 && diff <= 3)
    val allDecreasing = differences.forall(diff => diff <= -1 && diff >= -3)

    allIncreasing || allDecreasing
  }


def solvePart1(input: String): Int =
    val reports = parseInputTo2DList(input)

    val safeReportsCount = reports.count(isSafe)
    safeReportsCount


def solvePart2(input: String): Long =
    val reports = parseInputTo2DList(input)

    val safeReportsWithDampener = reports.count(isSafeWithDampener)
    safeReportsWithDampener
