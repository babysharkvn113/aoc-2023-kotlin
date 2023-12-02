fun main() {

//    fun part2(input: List<String>): Int {
//        return input.size
//    }

    // test if implementation meets criteria from the description, like:
    val input1 = readInput("data/day01");
    val d = Data(input1);

    println(d.solvePart1());
    println(d.solvePart2());
}

class Data (private val input:List<String>){
    private val words:Map<String,Int> = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
    )
    fun part1(input: List<String>): Int {
        return input.size
    }
    fun solvePart1():Int = input.sumOf { sumRowPart1(it) }
    private fun sumRowPart1(row: String) : Int = "${row.first{it.isDigit()}}${row.last{it.isDigit()}}".toInt()

    fun solvePart2():Int = input.sumOf { sumRowPart2(it)}

    private fun sumRowPart2(row: String): Int {
       val str =  row.mapIndexedNotNull{
            index:Int, c:Char ->
            if(c.isDigit()) c
            else
                row.possibleWordsAt(index).firstNotNullOfOrNull {
                    candidate:String -> words[candidate]
                }
        }.joinToString ()
        return sumRowPart1(str)
    }

    private fun String.possibleWordsAt(startingAt:Int):List<String> {
        return (3..5).map{len: Int ->
            substring(startingAt, (startingAt + len).coerceAtMost(length))
        }
    }




}