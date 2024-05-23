package csense.kotlin.not.highlighter.settings

import com.intellij.application.options.colors.*
import com.intellij.codeInsight.daemon.*
import com.intellij.openapi.options.*
import com.intellij.openapi.project.*
import com.intellij.ui.*
import csense.idea.base.bll.*
import csense.idea.base.bll.linemarkers.*
import csense.kotlin.not.highlighter.bll.*
import csense.kotlin.not.highlighter.settings.form.*
import java.awt.*
import javax.swing.*

class NotHighlighterSettingsConfigurable : SearchableConfigurable {

    private var ui: NotHighlighterSettingsUI? = null

    override fun createComponent(): JComponent? {
        ui = ui ?: NotHighlighterSettingsUI()
        return ui?.component()
    }

    override fun isModified(): Boolean {
        return ui?.isModified() ?: return false
    }

    @Throws(ConfigurationException::class)
    override fun apply() {
        val ui: NotHighlighterSettingsUI = ui ?: return
        val settings: NotHighlighterSettings = NotHighlighterSettings.instance
        ui.updateStoredSettings(settings)
        restartLineMarkersForAllProjects()
    }

    override fun reset() {
        ui?.loadSettings()
    }

    override fun disposeUIResources() {
        ui = null
    }

    override fun getDisplayName() = "Csense Kotlin Not Highlighter"
    override fun getId(): String {
        return NotHighlighterSettingsConfigurable::class.qualifiedName ?: ""
    }
}