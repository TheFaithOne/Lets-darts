package one.vitaliy.letsdarts

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

class App : Application() {
    override fun onCreate() {
        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("cs-CZ")
        AppCompatDelegate.setApplicationLocales(appLocale)
        super.onCreate()
    }
}
