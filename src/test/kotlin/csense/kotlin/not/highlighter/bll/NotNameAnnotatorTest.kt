package csense.kotlin.not.highlighter.bll

import csense.idea.kotlin.test.*
import org.junit.*

class NotNameAnnotatorTest : KotlinLightCodeInsightFixtureTestCaseJunit4() {

    override fun getTestDataPath(): String {
        return "src/test/testData/"
    }

    @Before
    fun setup() {
        myFixture.allowTreeAccessForAllFiles()
    }

    @Test
    fun notCases() {
        myFixture.testHighlighting(
            /* checkWarnings = */ false,
            /* checkInfos = */ true,
            /* checkWeakWarnings = */ false,
            /* ...filePaths = */ "NotCases.kt"
        )
    }

}