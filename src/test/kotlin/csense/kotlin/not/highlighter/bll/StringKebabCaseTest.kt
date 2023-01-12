package csense.kotlin.not.highlighter.bll

import csense.kotlin.tests.assertions.*
import org.junit.*

class StringKebabCaseTest {
    class StringforEachKebabCase {

        @Test
        fun empty() {
            "".kebabCase.forEachKebabCase { _, _ -> shouldNotBeCalled() }
        }

        @Test
        fun singleLowercase() {
            val testData = listOf(
                "a" to 0
            )
            assertCallbackCalledWith(testData, "a")
        }

        @Test
        fun singleUppercase() {
            val testData = listOf(
                "A" to 0
            )
            assertCallbackCalledWith(testData, "A")
        }

        @Test
        fun multipleAllUppercase() {
            val testData = listOf(
                "ABC" to 0
            )
            assertCallbackCalledWith(testData, "ABC")
        }


        @Test
        fun multipleAllLowercase() {
            val testData = listOf(
                "abc" to 0
            )
            assertCallbackCalledWith(testData, "abc")
        }

        @Test
        fun multipleWordsStartLowerCase() {
            val testData = listOf(
                "abc" to 0,
                "To" to 3
            )
            assertCallbackCalledWith(testData, "abcTo")
        }

        @Test
        fun multipleWordsStartUpperCase() {
            val testData = listOf(
                "ABC" to 0,
                "to" to 3
            )
            assertCallbackCalledWith(testData, "ABCto")
        }

        @Test
        fun mixed() {
            val testData = listOf(
                "abc" to 0,
                "TO" to 3,
                "qw" to 5
            )
            assertCallbackCalledWith(testData, "abcTOqw")
        }

        @Test
        fun shouldRespectNumberSequences() {
            val testData = listOf(
                "1234" to 0
            )
            assertCallbackCalledWith(testData, "1234")
        }

        @Test
        fun shouldRespectNumberSequencesAndStrings() {
            val testData = listOf(
                "1234" to 0,
                "Abc" to 4
            )
            assertCallbackCalledWith(testData, "1234Abc")
        }


        @Test
        fun shouldRespectNumbersInStrings() {
            val testData = listOf(
                "QWE" to 0,
                "1234" to 3,
                "Abc" to 7
            )
            assertCallbackCalledWith(testData, "QWE1234Abc")
        }

        private fun assertCallbackCalledWith(testData: List<Pair<String, Int>>, string: String) {
            assertCallbackCalledWith(
                testData, assertFunction = { lhs, rhs ->
                    lhs.first == rhs.first && lhs.second == rhs.second
                },
                testCode = { expected ->
                    string.kebabCase.forEachKebabCase { string, firstIndex ->
                        expected(Pair(string, firstIndex))
                    }
                })
        }
    }

    @Test
    fun charBreakOnDifferent() {
        val nullable: KebabCaseBreakChar? = null
        'a'.kebabCase.breakOnDifferent(nullable).assertFalse()

        'a'.kebabCase.breakOnDifferent(KebabCaseBreakChar.LowerCase).assertFalse("lowercase & lowercase is not a break")
        'a'.kebabCase.breakOnDifferent(KebabCaseBreakChar.UpperCase).assertTrue("lowercase & uppercase is a break")
        'a'.kebabCase.breakOnDifferent(KebabCaseBreakChar.Symbol).assertTrue("lowercase & symbol is a break")

        'A'.kebabCase.breakOnDifferent(KebabCaseBreakChar.LowerCase).assertTrue("uppercase & lowercase is a break")
        'A'.kebabCase.breakOnDifferent(KebabCaseBreakChar.UpperCase).assertFalse("uppercase & uppercase is not a break")
        'A'.kebabCase.breakOnDifferent(KebabCaseBreakChar.Symbol).assertTrue("uppercase & symbol a break")

        '1'.kebabCase.breakOnDifferent(KebabCaseBreakChar.LowerCase).assertTrue("symbol & lowercase is not a break")
        '1'.kebabCase.breakOnDifferent(KebabCaseBreakChar.UpperCase).assertTrue("symbol & uppercase a break")
        '1'.kebabCase.breakOnDifferent(KebabCaseBreakChar.Symbol).assertFalse("symbol & symbol a break")
    }

    @Test
    fun charToKebabCaseBreakChar() {
        'a'.kebabCase.toKebabCaseBreakChar().assert(KebabCaseBreakChar.LowerCase)
        'A'.kebabCase.toKebabCaseBreakChar().assert(KebabCaseBreakChar.UpperCase)
        '1'.kebabCase.toKebabCaseBreakChar().assert(KebabCaseBreakChar.Symbol)
    }
}
