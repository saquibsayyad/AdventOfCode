import java.io.File

fun readInput(filename: String):  String {
    return File(filename).readText()
}

var memory = readInput("day2_input.txt")
        .split(",")
        .map { t -> t.toInt() }.toMutableList()

for (p in 0 until memory.size step 4) {
   when(memory[p]) {
       1 -> {
           memory[memory[p+3]] = memory[memory[p+1]] + memory[memory[p+2]]
       }
       2 -> {
           memory[memory[p+3]] = memory[memory[p+1]] * memory[memory[p+2]]
       }
    }
    if(memory[p] == 99 ) break
}
print(memory[0])