fun main() {
    val d = Data02(readInput("data/day02"))
    println("Result Part One=${d.solvePart1()}")
    println("Result Part Two=${d.solvePart2()}")
}

class Data02 (private val input:List<String>){
    data class Set(val red:Int, val blue: Int, val green: Int)
    data class Game(val id:Int, val sets:List<Set>)
    fun solvePart1():Int {
        val expectedRed = 12
        val expectedGreen = 13
        val expectedBlue = 14

        return parse().filter {
            it.sets.none{it.red > expectedRed || it.blue > expectedBlue || it.green > expectedGreen}
        }.sumOf { it.id }
    }

    fun solvePart2() : Int {
       return parse().map{
            var maxRed = 0
            var maxGreen = 0
            var maxBlue = 0
            it.sets.forEach{
                maxRed = max(maxRed, it.red)
                maxBlue = max(maxBlue, it.blue)
                maxGreen = max(maxGreen, it.green)
            }
            maxRed*maxBlue*maxGreen
        }.sumOf { it }
    }
    fun max(a: Int, b: Int) = if (a > b) a else b

    private fun parse():List<Game> {
        return input.map {
            val (game: String, sets: String) = it.split(":")
            val id:Int = "Game (\\d+)".toRegex().find(game)!!.groupValues[1].toInt()
            sets.split(";").map{
                val red:Int = "(\\d+) red".toRegex().find(it)?.groupValues?.get(1)?.toInt() ?:0
                val blue:Int = "(\\d+) blue".toRegex().find(it)?.groupValues?.get(1)?.toInt() ?:0
                val green:Int = "(\\d+) green".toRegex().find(it)?.groupValues?.get(1)?.toInt() ?:0
                Set(red,blue, green)
            }.let{
                Game(id, it)
            }
        }
    }
}