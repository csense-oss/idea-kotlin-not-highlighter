package csense.kotlin.not.highlighter.settings

import com.intellij.openapi.application.*
import com.intellij.openapi.components.*
import com.intellij.util.xmlb.annotations.*
import csense.idea.base.uicomponents.colorFont.*
import csense.kotlin.extensions.*
import java.awt.*


@State(name = "CsenseNotHighlighter", storages = [(Storage("csense_not_highlighter.xml"))])
data class NotHighlighterSettings(
    var isEnabled: Boolean = true,

    var highlightOperators: Boolean = true,

    var highlightVariableNames: Boolean = true,
    var highlightFunctionNames: Boolean = true,
    var highlightComments: Boolean = true,
    var highlightStrings: Boolean = true,
    var highlightDisabledText: Boolean = true,


    //For operators
    @Deprecated("Should use colorFontForOperators instead")
    @OptionTag(converter = ColorFontPanelDataConverter::class)
    private var colorFontPanelData: ColorFontPanelData? = defaultColorFontPanelData,

    @OptionTag(converter = ColorFontPanelDataConverter::class)
    var colorFontForOperators: ColorFontPanelData = defaultColorFontPanelData,

    @OptionTag(converter = ColorFontPanelDataConverter::class)
    var colorFontForNames: ColorFontPanelData = defaultColorFontPanelData,

    @OptionTag(converter = ColorFontPanelDataConverter::class)
    var colorFontForComments: ColorFontPanelData = defaultColorFontPanelData

) : PersistentStateComponent<NotHighlighterSettings> {


    override fun loadState(state: NotHighlighterSettings) {
        this.isEnabled = state.isEnabled

        this.highlightOperators = state.highlightOperators

        this.highlightFunctionNames = state.highlightFunctionNames
        this.highlightVariableNames = state.highlightVariableNames
        this.highlightComments = state.highlightComments
        this.highlightStrings = state.highlightStrings
        this.highlightDisabledText = state.highlightDisabledText

        if (state.colorFontForOperators == defaultColorFontPanelData) {
            this.colorFontForOperators = state.colorFontPanelData ?: state.colorFontForOperators
        }

        this.colorFontForNames = state.colorFontForNames
        this.colorFontForComments = state.colorFontForComments

    }

    override fun getState(): NotHighlighterSettings = this

    companion object {

        private val defaultForegroundColor: Color = Color(0xBD57FF)

        private const val defaultFontType: Int = Font.PLAIN.or(Font.BOLD).or(Font.ITALIC)

        private val defaultColorFontPanelData = ColorFontPanelData(
            foregroundColor = defaultForegroundColor,
            fontType = defaultFontType
        )

        val instance: NotHighlighterSettings
            get() = ApplicationManager.getApplication().getService(type())

    }
}