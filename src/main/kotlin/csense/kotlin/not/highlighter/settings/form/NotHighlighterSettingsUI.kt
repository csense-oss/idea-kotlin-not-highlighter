package csense.kotlin.not.highlighter.settings.form

import com.intellij.openapi.editor.markup.*
import com.intellij.ui.*
import com.jetbrains.rd.swing.*
import csense.kotlin.extensions.*
import csense.kotlin.not.highlighter.settings.*
import java.awt.*
import javax.swing.*


class NotHighlighterSettingsUI {

    private lateinit var root: JPanel

    private lateinit var isEnabledBox: JCheckBox
    private lateinit var highlightVariableNames: JCheckBox
    private lateinit var highlightFunctionNames: JCheckBox

    private lateinit var boldBox: JCheckBox
    private lateinit var italicBox: JCheckBox

    private lateinit var foregroundBox: JCheckBox
    private lateinit var foregroundColorBox: ColorPanel

    private lateinit var backgroundBox: JCheckBox
    private lateinit var backgroundColorBox: ColorPanel

    private lateinit var errorStripeBox: JCheckBox
    private lateinit var errorStripeColorBox: ColorPanel

    private lateinit var effectsBox: JCheckBox
    private lateinit var effectsColorBox: ColorPanel

    private lateinit var effectType: JComboBox<EffectType>

    private val highlighterSettings: NotHighlighterSettings = NotHighlighterSettings.instance

    init {
        effectType.model = EnumComboBoxModel(type())
        loadSettings()
    }

    fun component(): JPanel = root
    fun loadSettings() {
        isEnabledBox.isSelected = highlighterSettings.isEnabled
        highlightVariableNames.isSelected = highlighterSettings.highlightVariableNames
        highlightFunctionNames.isSelected = highlighterSettings.highlightFunctionNames
        //TODO WRONG IMPL here for font type
        boldBox.isSelected = highlighterSettings.fontType == Font.BOLD
        italicBox.isSelected = highlighterSettings.fontType == Font.ITALIC

        foregroundBox.isSelected = highlighterSettings.foregroundColor != null
        foregroundColorBox.selectedColor = highlighterSettings.foregroundColor

        backgroundBox.isSelected = highlighterSettings.backgroundColor != null
        backgroundColorBox.selectedColor = highlighterSettings.backgroundColor

        errorStripeBox.isSelected = highlighterSettings.errorStripeColor != null
        errorStripeColorBox.selectedColor = highlighterSettings.errorStripeColor

        effectsBox.isSelected = highlighterSettings.effectColor != null
        effectsColorBox.selectedColor = highlighterSettings.effectColor

        effectType.selectedItem = highlighterSettings.effectType ?: EffectType.LINE_UNDERSCORE
    }

    fun isModified(): Boolean {


        return highlighterSettings.isEnabled != isEnabledBox.isSelected ||

                highlighterSettings.highlightVariableNames != highlightVariableNames.isSelected ||
                highlighterSettings.highlightFunctionNames != highlightFunctionNames.isSelected ||

                highlighterSettings.foregroundColor != selectedForegroundColor() ||
                highlighterSettings.backgroundColor != selectedBackgroundColor() ||

                highlighterSettings.errorStripeColor != selectedErrorStripeColor() ||

                highlighterSettings.effectColor != selectedEffectsColor() ||
                highlighterSettings.effectType != selectedEffectsType() ||

                highlighterSettings.fontType != selectedFontTypes()
    }

    fun update(settings: NotHighlighterSettings) {
        settings.isEnabled = isEnabledBox.isSelected

        settings.highlightVariableNames = highlightVariableNames.isSelected
        settings.highlightFunctionNames = highlightFunctionNames.isSelected

        settings.foregroundColor = selectedForegroundColor()
        settings.backgroundColor = selectedBackgroundColor()

        settings.errorStripeColor = selectedErrorStripeColor()

        settings.effectColor = selectedEffectsColor()
        settings.effectType = selectedEffectsType()

        settings.fontType = selectedFontTypes()
    }

    private fun selectedForegroundColor(): Color? =
        foregroundColorBox.colorOrNullBy(foregroundBox)

    private fun selectedBackgroundColor(): Color? =
        backgroundColorBox.colorOrNullBy(backgroundBox)

    private fun selectedErrorStripeColor(): Color? =
        errorStripeColorBox.colorOrNullBy(errorStripeBox)


    private fun selectedEffectsColor(): Color? =
        effectsColorBox.colorOrNullBy(effectsBox)


    //TODO
    private fun selectedEffectsType(): EffectType? =
        effectType.model.selectedItem as? EffectType

    @Suppress("KotlinConstantConditions")//otherwise idea wants to remove some of the code making it more difficult to understand.
    private fun selectedFontTypes(): Int {
        var result = Font.PLAIN
        if (italicBox.isSelected) {
            result = result.or(Font.ITALIC)
        }
        if (boldBox.isSelected) {
            result = result.or(Font.BOLD)
        }
        return result
    }

}

fun ColorPanel.colorOrNullBy(checkbox: JCheckBox): Color? = when {
    checkbox.isSelected -> this.selectedColor
    else -> null
}