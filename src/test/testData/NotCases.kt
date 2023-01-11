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

}