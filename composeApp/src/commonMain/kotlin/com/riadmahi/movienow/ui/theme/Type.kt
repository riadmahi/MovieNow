package com.riadmahi.movienow.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import movienow.composeapp.generated.resources.*
import movienow.composeapp.generated.resources.Res
import movienow.composeapp.generated.resources.brsonoma_bold
import movienow.composeapp.generated.resources.brsonoma_regular
import movienow.composeapp.generated.resources.brsonoma_semibold
import org.jetbrains.compose.resources.Font
import androidx.compose.material.Typography

@Composable
fun BrSonomaFontFamily() = FontFamily(
    Font(Res.font.brsonoma_regular, FontWeight.Normal),
    Font(Res.font.brsonoma_bold, FontWeight.Bold),
    Font(Res.font.brsonoma_semibold, FontWeight.SemiBold),
    Font(Res.font.brsonoma_medium, FontWeight.Medium),
)

@Composable
fun BrSonomaTypography() = Typography().run {
    val fontFamily = BrSonomaFontFamily()
    copy(
        body1 = body1.copy(fontFamily = fontFamily),
        body2 = body2.copy(fontFamily = fontFamily),
        h1 = h1.copy(fontFamily = fontFamily),
        h2 = h2.copy(fontFamily = fontFamily),
        h3 = h3.copy(fontFamily = fontFamily),
        h4 = h4.copy(fontFamily = fontFamily),
        h5 = h6.copy(fontFamily = fontFamily),
        h6 = h6.copy(fontFamily = fontFamily),
        subtitle1 = subtitle1.copy(fontFamily = fontFamily),
        subtitle2 = subtitle2.copy(fontFamily = fontFamily),
        button = button.copy(fontFamily = fontFamily),
        caption = caption.copy(fontFamily = fontFamily),
        overline = overline.copy(fontFamily = fontFamily)
    )
}