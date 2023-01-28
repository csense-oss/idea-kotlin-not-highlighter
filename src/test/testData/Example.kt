<info descr="null">@<info descr="null">file</info>:Suppress</info>("unused", "ControlFlowWithEmptyBody", "KotlinConstantConditions")

fun <info descr="null">isTheWeather<info descr="null">Not</info>Nice</info>() = false
fun <info descr="null">is<info descr="null">Not</info>Monday</info>() = true

class <info descr="null">WeatherLiker</info> {
    <info descr="null">private</info> val <info descr="null">isIt<info descr="null">Not</info>Night</info> = false

    fun <info descr="null">shouldYouLikeTheWeather</info>() {
        if (<info descr="null">isTheWeather<info descr="null">Not</info>Nice</info>()) {
            return
        }
        val <info descr="null">isItMonday</info> = <info descr="null">!</info><info descr="null">is<info descr="null">Not</info>Monday</info>()
        if (<info descr="null">isItMonday</info> && <info descr="null">isIt<info descr="null">Not</info>Night</info>) {
            <info descr="null">println</info>("Good day")
        } else {
            <info descr="null">println</info>("...")
        }
    }
}


fun <info descr="null">combineInCall</info>() {
    val <info descr="null">someRef</info> = ""
    val <info descr="null"><info descr="null">not</info>Ref</info> = 42
    <info descr="null">takes<info descr="null">Not</info>Refs</info>(<info descr="null">a =</info> <info descr="null">someRef</info>, <info descr="null"><info descr="null">not</info>B =</info> <info descr="null"><info descr="null">not</info>Ref</info>)
}

fun <info descr="null">takes<info descr="null">Not</info>Refs</info>(<info descr="null">a</info>: <info descr="null">String</info>, <info descr="null"><info descr="null">not</info>B</info>: <info descr="null">Int</info>): <info descr="null">String</info> {
    return <info descr="null">a</info> + <info descr="null"><info descr="null">not</info>B</info>.<info descr="null">toString</info>()
}

fun <info descr="null">multiple<info descr="null">Not</info>Are<info descr="null">Not</info></info>() {
    val <info descr="null">x</info>: <info descr="null">Any</info>? = null
    if (<info descr="null">x</info> <info descr="null">!is</info> <info descr="null">String</info> || <info descr="null">x</info> <info descr="null">!=</info> <info descr="null">Unit</info>) {

    }
    if (<info descr="null">x</info> <info descr="null">!=</info> <info descr="null">Unit</info>) {

    }
    if (<info descr="null">x</info> == <info descr="null">Unit</info>) {

    }
    if (<info descr="null">x</info> <info descr="null">!in</info> <info descr="null">setOf</info><<info descr="null">Any</info>>()) {

    }
    if (<info descr="null">x</info> in <info descr="null">setOf</info><<info descr="null">Any</info>>()) {

    }


}