package csense.kotlin.not.highlighter.settings.form

import csense.idea.base.uicomponents.colorFont.*
import csense.kotlin.not.highlighter.repo.*
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

    private lateinit var operatorColorFontPanel: ColorFontPanel
    private lateinit var namesColorFontPanel: ColorFontPanel
    private lateinit var commentsColorFontPanel: ColorFontPanel

    private lateinit var createCustomNamesButton: JButton
    private lateinit var resetButton: JButton

    init {
        loadSettings()
        resetButton.addActionListener {
            loadSettings(NotHighlighterSettings())
        }
        createCustomNamesButton.addActionListener {
            createCustomNamesFile()
        }
    }


    fun component(): JPanel = root
    fun loadSettings() {
        val highlighterSettings: NotHighlighterSettings = NotHighlighterSettings.instance
        loadSettings(highlighterSettings)
    }

    private fun loadSettings(highlighterSettings: NotHighlighterSettings) {
        isEnabledBox.isSelected = highlighterSettings.isEnabled

        highlightVariableNames.isSelected = highlighterSettings.highlightVariableNames
        highlightFunctionNames.isSelected = highlighterSettings.highlightFunctionNames
        highlightOperators.isSelected = highlighterSettings.highlightOperators
        highlightComments.isSelected = highlighterSettings.highlightComments
        highlightStrings.isSelected = highlighterSettings.highlightStrings
        highlightDisabledText.isSelected = highlighterSettings.highlightDisabledText

        operatorColorFontPanel.updateWithData(highlighterSettings.colorFontForOperators)
        namesColorFontPanel.updateWithData(highlighterSettings.colorFontForNames)
        commentsColorFontPanel.updateWithData(highlighterSettings.colorFontForComments)
    }

    fun isModified(): Boolean {
        val highlighterSettings: NotHighlighterSettings = NotHighlighterSettings.instance
        return highlighterSettings.isEnabled != isEnabledBox.isSelected ||

                highlighterSettings.highlightVariableNames != highlightVariableNames.isSelected ||
                highlighterSettings.highlightFunctionNames != highlightFunctionNames.isSelected ||
                highlighterSettings.highlightOperators != highlightOperators.isSelected ||
                highlightComments.isSelected != highlighterSettings.highlightComments ||
                highlightStrings.isSelected != highlighterSettings.highlightStrings ||
                highlightDisabledText.isSelected != highlighterSettings.highlightDisabledText ||

                operatorColorFontPanel.getData() != highlighterSettings.colorFontForOperators ||
                namesColorFontPanel.getData() != highlighterSettings.colorFontForNames ||
                commentsColorFontPanel.getData() != highlighterSettings.colorFontForComments

    }

    fun updateStoredSettings(settings: NotHighlighterSettings) {
        settings.isEnabled = isEnabledBox.isSelected

        settings.highlightVariableNames = highlightVariableNames.isSelected
        settings.highlightFunctionNames = highlightFunctionNames.isSelected
        settings.highlightOperators = highlightOperators.isSelected

        settings.highlightComments = highlightComments.isSelected
        settings.highlightStrings = highlightStrings.isSelected
        settings.highlightDisabledText = highlightDisabledText.isSelected

        settings.colorFontForOperators = operatorColorFontPanel.getData()
        settings.colorFontForNames = namesColorFontPanel.getData()
        settings.colorFontForComments = commentsColorFontPanel.getData()
    }

    private fun createCustomNamesFile() {
        NamesHighlighterRepo.allOpened { it: NamesHighlighterRepo ->
            it.save()
        }
    }


}