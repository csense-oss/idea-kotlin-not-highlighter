package csense.kotlin.not.highlighter.settings.form

import com.intellij.ui.*
import csense.kotlin.not.highlighter.settings.*
import java.awt.*
import javax.swing.*


class NotHighlighterSettingsUI {

    private lateinit var root: JPanel
    private lateinit var isEnabledBox: JCheckBox

    private lateinit var foregroundColor: ColorPanel
    private lateinit var backgroundColor: ColorPanel

    private lateinit var foregroundBox: JCheckBox
    private lateinit var backgroundBox: JCheckBox

    private lateinit var highlightVariableNames: JCheckBox
    private lateinit var highlightFunctionNames: JCheckBox

    private lateinit var boldBox: JCheckBox
    private lateinit var italicBox: JCheckBox

    private val settings: NotHighlighterSettings = NotHighlighterSettings.instance

    init {
        loadSettings()
        setupBindings()
    }

    private fun setupBindings() {
        foregroundBox.addActionListener {
            if (foregroundColor.selectedColor == null) {
                foregroundColor.selectedColor = Color.black
            } else {
                foregroundColor.selectedColor = null
            }
        }
        backgroundBox.addActionListener {
            if (backgroundColor.selectedColor == null) {
                backgroundColor.selectedColor = Color.black
            } else {
                backgroundColor.selectedColor = null
            }
        }
        backgroundColor.addActionListener {
            backgroundBox.isSelected = true
        }
        foregroundColor.addActionListener {
            foregroundBox.isSelected = true
        }
    }

    fun component() = root

    fun loadSettings() {
        isEnabledBox.isSelected = settings.isEnabled

        foregroundColor.selectedColor = settings.foregroundColor
        backgroundColor.selectedColor = settings.backgroundColor

        foregroundBox.isSelected = settings.foregroundColor != null
        backgroundBox.isSelected = settings.backgroundColor != null

        highlightVariableNames.isSelected = settings.highlightVariableNames
        highlightFunctionNames.isSelected = settings.highlightFunctionNames

        boldBox.isSelected = settings.bold
        italicBox.isSelected = settings.italic
    }

    fun isModified(): Boolean {
        val isEnabledChanged = isEnabledBox.isSelected != settings.isEnabled

        val isForegroundChanged = foregroundColor.selectedColor != settings.foregroundColor
        val isBackgroundChanged = backgroundColor.selectedColor != settings.backgroundColor

        val isBoldChanged = boldBox.isSelected != settings.bold
        val isItalicChanged = italicBox.isSelected != settings.italic

        val isHighlightVariablesChanged = highlightVariableNames.isSelected != settings.highlightVariableNames
        val isHighlightFunctionsChanged = highlightVariableNames.isSelected != settings.highlightFunctionNames

        return isEnabledChanged || isForegroundChanged || isBackgroundChanged || isHighlightVariablesChanged || isHighlightFunctionsChanged || isItalicChanged || isBoldChanged
    }

    fun isEnabled() = isEnabledBox.isSelected

    fun foregroundColor(): Color? = foregroundColor.selectedColor
    fun backgroundColor(): Color? = backgroundColor.selectedColor

    fun highlightFunctionNames() = highlightFunctionNames.isSelected
    fun highlightVariableNames() = highlightVariableNames.isSelected

    fun italic(): Boolean = italicBox.isSelected
    fun bold(): Boolean = boldBox.isSelected


}