
fun validatePassword(password: String): Boolean {

    if (password.length != 6) return false

    // atleast two same adjacent number and increasing order
    var repFlag = false
    var signFlag = true;

    var i=0;
    while(i < password.length - 1) {
        if (password[i] == password[i+1]) {
            repFlag = true
        }

        if(password[i].toInt() > password[i+1].toInt()) {
            signFlag = false
            break
        }
        i++

    }
    if(!repFlag) return false

    if(!signFlag) return false

    return true
}

fun twoAdjacentNotPartofLargeGroup(password: String): Boolean {

    var counts: HashMap<String, Int?> = hashMapOf()


    password.fold(mutableMapOf()) { acc: MutableMap<Char, Int>, char: Char ->
        val count = acc[char]
        acc[char] = count?.let { it + 1 } ?: 0
        acc
    }

    for (x in password){
        counts[x.toString()] = counts[x.toString()]?.plus(1)
    }

    for ( x in counts.values) {
        if ( x == 2)
            return true
    }
    return false
}

var cntPart1 =0
var cntPart2 = 0

for (x in 246540 until 787419) {
    if(validatePassword(x.toString())){
        cntPart1++

        if (twoAdjacentNotPartofLargeGroup(x.toString())) {
            cntPart2++
        }

    }
}

// part 1
println(cntPart1)

// part 2
println(cntPart2)
