fun main(){
    lastTimeOnline()
    commissionCalc()
}
fun lastTimeOnline() {
    println("Введите количество времени")
    val time = readLine()?.toIntOrNull()
    var timeName = "только что"
    val minuteTime = time?.div(60)
    val hourTime = minuteTime?.div(60)
    if (time != null) {
        when {
            time in 0..60 -> timeName = "только что"
            time in 61..60*60 -> if (minuteTime != null) {
                timeName = when (minuteTime % 100 ) {
                    in 11..19 -> "$minuteTime минут назад"
                    else -> when (minuteTime % 10) {
                        1 -> "$minuteTime минуту назад"
                        2,3,4 -> "$minuteTime минуты назад"
                        else -> "$minuteTime минут назад"
                    }
                }
            }
            time in 60*60+1..60*60*24 -> if (hourTime != null) {
                timeName = when (hourTime % 100) {
                    in 11..19 -> "$hourTime часов назад"
                    else -> when (hourTime % 10) {
                        1 -> "$hourTime час назад"
                        2,3,4 -> "$hourTime часа назад"
                        else -> "$hourTime часов назад"
                    }
                }
            }
            time in 60*60*24+1..60*60*24*2 -> timeName = "сегодня"
            time in 60*60*24*2+1..60*60*24*3 -> timeName = "вчера"
            time >= (60*60*24*3+1) -> timeName = "давно"
        }
    }
    println("Был в сети $timeName")
}
fun commissionCalc() {
    println(
        "Выберите тип оплаты:" +
                "\n1. Mastercard или Maestro" +
                "\n2. Visa или Мир" +
                "\n3. VK Pay"
    )
    val paymentType = readLine()?.toIntOrNull()
    println("Введите сумму перевода в рублях")
    val sumToCalc = readLine()?.toIntOrNull()
    val previousSum = 0
    var commission = 0.0
    when (paymentType) {
        1 -> when (previousSum) {
            in 300..75000 -> commission = 0.0
            else -> if (sumToCalc != null) {
                commission = 20 + sumToCalc * 0.006
            }
        }
        2 -> if (sumToCalc != null) {
            commission = 35 + sumToCalc * 0.0075
        }
        3 -> commission = 0.0
    }
    println("Сумма перевеода: $sumToCalc" +
            "\nКомиссия за перевод : $commission")
}
