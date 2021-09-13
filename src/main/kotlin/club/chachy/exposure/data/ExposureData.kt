package club.chachy.exposure.data

import com.google.gson.annotations.SerializedName

data class ExposureData(
    @SerializedName("Build")
    val exposureBuild: String,
    @SerializedName("ExportVersion")
    val exportVersion: Int,
    @SerializedName("ExposureChecks")
    val exposureChecks: List<ExposureCheck>,
    @SerializedName("DeviceProductType")
    val deviceType: String
) {
    data class ExposureCheck(
        @SerializedName("Timestamp")
        val timestamp: String, // Format: YYYY-MM-DD HH:MM:SS +(UTC DIFFERENCE FROM TIMEZONE)
        @SerializedName("Files")
        val files: List<ExposureFile>,
        @SerializedName("AppBundleIdentifier")
        val appIdentifier: String
    ) {
        data class ExposureFile(
            @SerializedName("Hash")
            val hash: String,
            @SerializedName("KeyCount")
            val keyCount: Int,
            @SerializedName("AppBundleIdentifier")
            val appIdentifier: String,
            @SerializedName("Timestamp")
            val timestamp: String  // Same as above
        )
    }
}