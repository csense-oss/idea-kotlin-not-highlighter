package csense.kotlin.not.highlighter.bll

import csense.idea.kotlin.test.*
import csense.kotlin.not.highlighter.*
import org.junit.*

class AnnotatorTest : KotlinLightCodeInsightFixtureTestCaseJunit4() {
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
            /* checkWarnings = */ true,
            /* checkInfos = */ true,
            /* checkWeakWarnings = */ true,
            /* ...filePaths = */ "NotCases.kt"
        )
    }

}