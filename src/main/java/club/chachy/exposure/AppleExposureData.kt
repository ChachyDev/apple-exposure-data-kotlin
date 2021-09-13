package club.chachy.exposure

import club.chachy.exposure.data.ExposureData
import com.google.gson.Gson
import java.io.InputStream
import java.io.Reader

object AppleExposureData {
    private val gson = Gson()

    fun parse(`is`: InputStream): ExposureData {
        return parse(`is`.bufferedReader())
    }

    fun parse(reader: Reader): ExposureData {
        return parse(reader.use { it.readText() })
    }

    fun parse(json: String): ExposureData {
        return gson.fromJson(json, ExposureData::class.java)
    }
}