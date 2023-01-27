package csense.kotlin.not.highlighter.settings

import com.intellij.openapi.application.*
import com.intellij.openapi.components.*
import com.intellij.util.xmlb.annotations.OptionTag
import csense.idea.base.uicomponents.colorFont.*
import csense.kotlin.extensions.*
import java.awt.*


@State(name = "CsenseNotHighlighter", storages = [(Storage("csense_not_highlighter.xml"))])
data class NotHighlighterSettings(
    var isEnabled: Boolean = true,
    var highlightVariableNames: Boolean = true,
    var highlightFunctionNames: Boolean = true,

    @OptionTag(converter = ColorFontPanelDataConverter::class)
    var colorFontPanelData: ColorFontPanelData = defaultColorFontPanelData

) : PersistentStateComponent<NotHighlighterSettings> {


    override fun loadState(state: NotHighlighterSettings) {
        this.isEnabled = state.isEnabled

        this.highlightFunctionNames = state.highlightFunctionNames
        this.highlightVariableNames = state.highlightVariableNames

        this.colorFontPanelData = state.colorFontPanelData
    }

    override fun getState(): NotHighlighterSettings = this

    companion object {

        val defaultForegroundColor: Color = Color(0xBD57FF)

        const val defaultFontType: Int = Font.PLAIN.or(Font.BOLD).or(Font.ITALIC)
        val instance: NotHighlighterSettings
            get() = ApplicationManager.getApplication().getService(type())

        val defaultColorFontPanelData = ColorFontPanelData(
            foregroundColor = defaultForegroundColor,
            fontType = defaultFontType
        )


    }
}
