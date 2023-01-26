package csense.kotlin.not.highlighter.settings

import com.intellij.util.xmlb.*
import java.awt.*


class ColorConverter : Converter<Color>() {
    private val radix = 10
    override fun fromString(value: String): Color {
        return Color(value.toInt(radix))
    }

    override fun toString(value: Color): String {
        return value.rgb.toString(radix)
    }
}
