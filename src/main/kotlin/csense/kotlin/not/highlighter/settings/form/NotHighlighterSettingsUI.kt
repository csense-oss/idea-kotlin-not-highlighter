package csense.kotlin.not.highlighter.settings.form

import csense.idea.base.uicomponents.colorFont.*
import csense.kotlin.not.highlighter.settings.*
import javax.swing.*


class NotHighlighterSettingsUI {

    private lateinit var root: JPanel

    private lateinit var isEnabledBox: JCheckBox
    private lateinit var highlightVariableNames: JCheckBox
    private lateinit var highlightFunctionNames: JCheckBox
    private lateinit var highlightOperators: JCheckBox
    private lateinit var highlightComments: JCheckBox
    private lateinit var highlightStrings: JCheckBox
    private lateinit var highlightDisabledText: JCheckBox

    private lateinit var colorFontPanel: ColorFontPanel

    private val highlighterSettings: NotHighlighterSettings = NotHighlighterSettings.instance

    init {
        loadSettings()
    }

    fun component(): JPanel = root
    fun loadSettings() {
        isEnabledBox.isSelected = highlighterSettings.isEnabled

        highlightVariableNames.isSelected = highlighterSettings.highlightVariableNames
        highlightFunctionNames.isSelected = highlighterSettings.highlightFunctionNames
        highlightOperators.isSelected = highlighterSettings.highlightOperators
        highlightComments.isSelected = highlighterSettings.highlightComments
        highlightStrings.isSelected = highlighterSettings.highlightStrings
        highlightDisabledText.isSelected = highlighterSettings.highlightDisabledText

        colorFontPanel.updateWithData(highlighterSettings.colorFontPanelData)
    }

    fun isModified(): Boolean {
        return highlighterSettings.isEnabled != isEnabledBox.isSelected ||

                highlighterSettings.highlightVariableNames != highlightVariableNames.isSelected ||
                highlighterSettings.highlightFunctionNames != highlightFunctionNames.isSelected ||
                highlighterSettings.highlightOperators != highlightOperators.isSelected ||
                highlightComments.isSelected != highlighterSettings.highlightComments ||
                highlightStrings.isSelected != highlighterSettings.highlightStrings ||
                highlightDisabledText.isSelected != highlighterSettings.highlightDisabledText ||

                colorFontPanel.getData() != highlighterSettings.colorFontPanelData
    }

    fun update(settings: NotHighlighterSettings) {
        settings.isEnabled = isEnabledBox.isSelected

        settings.highlightVariableNames = highlightVariableNames.isSelected
        settings.highlightFunctionNames = highlightFunctionNames.isSelected
        settings.highlightOperators = highlightOperators.isSelected

        settings.highlightComments = highlightComments.isSelected
        settings.highlightStrings = highlightStrings.isSelected
        settings.highlightDisabledText = highlightDisabledText.isSelected

        settings.colorFontPanelData = colorFontPanel.getData()
    }

}

