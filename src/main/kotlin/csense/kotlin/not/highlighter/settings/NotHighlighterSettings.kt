package csense.kotlin.not.highlighter.settings

import com.intellij.openapi.application.*
import com.intellij.openapi.components.*
import com.intellij.openapi.editor.markup.*
import com.intellij.util.xmlb.annotations.*
import csense.kotlin.extensions.*
import org.intellij.lang.annotations.*
import java.awt.*


@State(name = "CsenseNotHighlighter", storages = [(Storage("csense_not_highlighter.xml"))])
data class NotHighlighterSettings(
    var isEnabled: Boolean = true,
    var highlightVariableNames: Boolean = true,
    var highlightFunctionNames: Boolean = true,

    @OptionTag(converter = ColorConverter::class)
    var foregroundColor: Color? = defaultForegroundColor,

    @OptionTag(converter = ColorConverter::class)
    var backgroundColor: Color? = null,

    @OptionTag(converter = ColorConverter::class)
    var errorStripeColor: Color? = null,

    @OptionTag(converter = ColorConverter::class)
    var effectColor: Color? = null,

    var effectType: EffectType? = null,

    @MagicConstant(flags = [Font.PLAIN.toLong(), Font.BOLD.toLong(), Font.ITALIC.toLong()])
    var fontType: Int = Font.PLAIN

) : PersistentStateComponent<NotHighlighterSettings> {


    override fun loadState(state: NotHighlighterSettings) {
        this.isEnabled = state.isEnabled
        this.highlightFunctionNames = state.highlightFunctionNames
        this.highlightVariableNames = state.highlightVariableNames
        this.foregroundColor = state.foregroundColor
        this.backgroundColor = state.backgroundColor
        this.errorStripeColor = state.errorStripeColor
        this.effectColor = state.effectColor
        this.effectType = state.effectType
        this.fontType = state.fontType
    }

    override fun getState(): NotHighlighterSettings = this

    companion object {
        val instance: NotHighlighterSettings
            get() = ApplicationManager.getApplication().getService(type())

        val defaultForegroundColor: Color = Color(0xBD57FF)
    }
}
