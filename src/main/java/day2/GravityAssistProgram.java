package day2

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.ArrayList
import java.util.Arrays
import java.util.stream.Collectors

object GravityAssistProgram {

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val reader = BufferedReader(InputStreamReader(GravityAssistProgram::class.java!!.getResourceAsStream("../day2_input.txt")))

        val line = reader.readLine()

        val memory = Arrays.asList<String>(*line.split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray())
                .stream()
                .map { c -> Integer.parseInt(c) }
                .collect<List<Int>, Any>(Collectors.toList())


        for (i in 0..99) {
            for (j in 0..99) {

                val freshMem = ArrayList(memory)
                freshMem[1] = i
                freshMem[2] = j

                println("Trying with i,j : " + freshMem[1] + "," + freshMem[2])
                println(freshMem[0].toString() + "," + freshMem[1] + "," + freshMem[2] + "," + freshMem[3])

                var k = 0
                while (k < freshMem.size) {
                    if (!performOperation(k, freshMem)) {
                        break
                    }
                    k += 4
                }
                println("Value at 0: " + freshMem[0])
                if (freshMem[0] == 19690720) {
                    println("Noun: $i Verb: $j")
                    return
                }
            }
        }
    }


    private fun performOperation(pos: Int, memory: MutableList<Int>): Boolean {

        when (memory[pos]) {
            1 -> {
                memory[memory[pos + 3]] = memory[memory[pos + 1]] + memory[memory[pos + 2]]
                println(memory[pos].toString() + "," + memory[pos + 1] + "," + memory[pos + 2] + "," + memory[pos + 3])
                return true
            }
            2 -> {
                memory[memory[pos + 3]] = memory[memory[pos + 1]] * memory[memory[pos + 2]]
                println(memory[pos].toString() + "," + memory[pos + 1] + "," + memory[pos + 2] + "," + memory[pos + 3])
                return true
            }
            99 -> return false
            else -> {
                println("Fatal error!! Opcode is " + memory[pos])
                return false
            }
        }

    }
}
