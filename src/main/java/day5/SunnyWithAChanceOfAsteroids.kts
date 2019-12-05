import java.io.File
import java.util.*

fun loadInstructions(filename: String) = File(filename).readText().split(",").map { t -> t.toInt() }.toMutableList()

enum class Mode {
    IMMEDIATE,
    POSITIONAL
}

fun normalizeOpCode(op: String): String {
    return "0".repeat(5 -op.length) + op
}

fun getOperation(opcode: String): Int {
    var op = normalizeOpCode(opcode)

    if(op.length == 5) {
        return (op[op.length -2].toString() + op[op.length -1].toString()).toInt()
    } else if (op.length in 1..2) {
        return op.toInt()
    }

    throw error("Invalid opcode $opcode")
}

fun getModes(opcode: String): Map<String, Mode> {

    var op = normalizeOpCode(opcode)
    var map = mutableMapOf<String, Mode>()

    if (op.length == 5) {

        if( op[0] == '1') {
            map.put("C", Mode.IMMEDIATE)
        }else {
            map.put("C", Mode.POSITIONAL)
        }


        if( op[1] == '1') {
            map.put("B", Mode.IMMEDIATE)
        }else {
            map.put("B", Mode.POSITIONAL)
        }


        if( op[2] == '1') {
            map.put("A", Mode.IMMEDIATE)
        }else {
            map.put("A", Mode.POSITIONAL)
        }

    } else {
        print("") // works as getVal defaults to position
    }

    return map
}

fun getVal(pos: Int, mode: Mode?, mem: MutableList<Int>): Int {

    if( mode == Mode.IMMEDIATE) {
        return mem[pos]
    } else {
        return mem[mem[pos]]
    }
}


fun exec(pointer: Int, mem: MutableList<Int>) : Int {

    val console = Scanner(System.`in`)

    var opcode = mem[pointer]

    val operation = getOperation(opcode.toString()) // 1,2,3,4,99 etc
    val modes = getModes(opcode.toString()) // {A: P/I, B: P/I, C:P/I}


    println("opcode $opcode - mode $modes")
    println(mem)

    when(operation) {
        1 -> {
            val a = getVal(pointer + 1, modes["A"], mem)
            val b = getVal(pointer + 2, modes["B"], mem)
            mem[mem[pointer + 3]] = a + b
            return pointer+4
        }

        2 -> {
            val a = getVal(pointer + 1, modes["A"], mem)
            val b = getVal(pointer + 2, modes["B"], mem)
            mem[mem[pointer + 3]] = a * b
            return pointer + 4
        }

        3 -> {
            val n = console.nextInt();
            mem[mem[pointer +1]] = n
            return pointer + 2
        }

        4 -> {
            println(getVal(pointer + 1, modes["A"], mem))
            return pointer + 2
        }

        5 -> {
            val a = getVal(pointer + 1, modes["A"], mem)
            val b = getVal(pointer + 2, modes["B"], mem)

            return if(a != 0)
                b
            else
                pointer + 1
        }

        6 -> {
            val a = getVal(pointer + 1, modes["A"], mem)
            val b = getVal(pointer + 2, modes["B"], mem)

            return if(a == 0)
                b
            else
                pointer + 2

        }

        7 -> {
            val a = getVal(pointer + 1, modes["A"], mem)
            val b = getVal(pointer + 2, modes["B"], mem)

            if (a < b) {
                mem[mem[pointer + 3]] = 1
            }else {
                mem[mem[pointer + 3]] = 0
            }

            return pointer + 2
        }

        8 -> {
            val a = getVal(pointer + 1, modes["A"], mem)
            val b = getVal(pointer + 2, modes["B"], mem)

            if (a == b) {
                mem[mem[pointer + 3]] = 1
            }else {
                mem[mem[pointer + 3]] = 0
            }

            if(mem[pointer + 1] == mem[mem[pointer + 3]]) // if pointer values gets overriden, donnt increment
                return pointer
            else
                return pointer + 4
        }

        else -> {
            return pointer + 1
        }

    }
}


//var mem = loadInstructions("day5_input.txt")

var mem = "3,3,1108,-1,8,3,4,3,99".split(",").map { t -> t.toInt() }.toMutableList()
var pointer = 0
while(pointer < mem.size && mem[pointer] != 99) {
    pointer = exec(pointer, mem)
}