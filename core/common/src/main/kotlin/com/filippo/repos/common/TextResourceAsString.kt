package com.filippo.repos.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun TextResource.asString(): String = when (this) {
    is FromText -> text
    is FromStrings -> stringResource(resId)
    is FromStringsFormatted -> stringResource(resId, *formatArgs.toTypedArray())
}
