package com.minjalidze.anonimousvotes.filesystem

import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.Properties

class INI(config: String) {
    private var configurationFile: String = ""
    private var configuration: Properties? = null

    init {
        configuration = Properties()
        configurationFile = config
    }

    fun load(): Boolean {
        var retval = false
        try {
            configuration?.load(FileInputStream(configurationFile))
            retval = true
        } catch (e: Exception) { }
        return retval
    }
    fun store(): Boolean {
        var retval = false
        try {
            configuration?.store(FileOutputStream(configurationFile), null)
            retval = true
        } catch (e: Exception) { }
        return retval
    }

    fun set(key: String?, value: String?) {
        configuration!!.setProperty(key, value)
    }
    fun get(key: String?): String? {
        return configuration!!.getProperty(key)
    }
}