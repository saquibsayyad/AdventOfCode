




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

var cnt = 0
for (x in 246540 until 787419) {
    if(validatePassword(x.toString())) cnt++
}

println(cnt)


//print(validatePassword("111122"))