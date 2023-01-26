<info descr="null">@<info descr="null">file</info>:Suppress</info>("unused", "ControlFlowWithEmptyBody", "KotlinConstantConditions")
package csense.kotlin.<info descr="null">not</info>.test

class <info descr="null"><info descr="null">Not</info>Cases</info>(
    <info descr="null">private</info> val <info descr="null">thisValShouldBehighlighted<info descr="null">Not</info></info>: <info descr="null">Boolean</info>
) {

    fun <info descr="null">shouldHiglightThis<info descr="null">Not</info></info>() {
        if (<info descr="null">thisValShouldBehighlighted<info descr="null">Not</info></info>) {
            <info descr="null">println</info>("")
        }
    }

    fun <info descr="null">useExclamationMark</info>() {
        if (<info descr="null">!</info><info descr="null">thisValShouldBehighlighted<info descr="null">Not</info></info>) {
            <info descr="null">println</info>("")
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

    fun <info descr="null">highlightesIsCorrectly</info>(){
        val <info descr="null">x</info>: <info descr="null">Any</info>? = "asd"
        if(<info descr="null">x</info> is <info descr="null">String</info>){
            //shouldnt be highligted
        }
        if(<info descr="null">x</info> <info descr="null">!is</info> <info descr="null">Int</info>){
            //should be highligted
        }
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
}