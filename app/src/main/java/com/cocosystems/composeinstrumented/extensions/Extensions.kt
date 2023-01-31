package com.cocosystems.composeinstrumented.extensions

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

fun <T: Number> T.asMoney(): String {
    val decimalSymbols = DecimalFormatSymbols.getInstance()
    decimalSymbols.decimalSeparator = '.'
    decimalSymbols.groupingSeparator = ','
    return DecimalFormat("$###,###,##0.00", decimalSymbols).format(this)
}