@file:Suppress("NOTHING_TO_INLINE")

package com.egoriku.ladyhappy.extensions

import androidx.exifinterface.media.ExifInterface
import com.egoriku.ladyhappy.extensions.DateFormat.exifInterfaceDateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateFormat {
    val exifInterfaceDateFormat = SimpleDateFormat("yyyy:MM:dd HH:mm:ss", Locale.US).apply {
        timeZone = TimeZone.getTimeZone("GMT+03:00")
    }
}

inline val ExifInterface.imageCreationDate
    get() = runCatching {
        val attribute = requireNotNull(getAttribute(ExifInterface.TAG_DATETIME))

        exifInterfaceDateFormat.parse(attribute).time
    }.getOrDefault(0L)