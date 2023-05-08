package com.ebt.finance.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ebt.finance.R


val Poppins = FontFamily(
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_semi_bold, FontWeight.SemiBold),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_light, FontWeight.Light),
        Font(R.font.poppins_thin, FontWeight.ExtraLight),
        Font(R.font.poppins_black, FontWeight.Black),
        Font(R.font.poppins_italic, FontWeight.Normal, FontStyle.Italic)
)
// Set of Material typography styles to start with
val Typography = Typography(
        bodyLarge = TextStyle(
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp,
                color = Secondary
        ),
    titleLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        color = Secondary
    ),
    labelSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        color = Secondary
    )
)