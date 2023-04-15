package com.prashant.tummoc.methods

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.gson.Gson
import com.prashant.tummoc.methods.CommonMethod.convertStringInTime
import kotlin.random.Random
import kotlin.time.Duration

object CommonMethod {

    /**
     * Converts a generic object to a JSON string representation using Gson library.
     *
     * @reified Generic: The generic type to be converted to a JSON string.
     * @return String: The JSON string representation of the given generic object.
     */
    inline fun <reified Generic> toJson(response: Generic): String = Gson().toJson(response)


    /**
     * Converts the given JSON string into an object of the specified generic type.
     *
     * @param jsonString the JSON string to be converted
     * @param Generic the type of object to be returned
     * @return an object of the specified generic type, created from the given JSON string
     */
    inline fun <reified Generic> fromJson(jsonString: String): Generic =
        Gson().fromJson(jsonString, Generic::class.java)

    /**
     * getPercentageList is a function that takes a list of values and returns a list of percentages
     *
     * @param values: List<Float> - the list of values for which we need to calculate the percentages
     *
     * @return List<Float> - a list of percentages calculated from the input list of values
     */
    fun getPercentageList(values: List<Double>): List<Float> {
        val sum = values.sum()
        return values.map { (it / sum * 100).toFloat() }
    }


    /**
     * Returns a random color in 24-bit integer format
     *
     * This function uses the `Random` class to generate a random integer value between 0 and 0xffffff.
     * This value is then passed to the `Color` constructor to create a random color.
     *
     * @return A random color in 24-bit integer format
     */
    private fun randomColor(): Int {
        val r: Float = Random.nextFloat()
        val g: Float = Random.nextFloat()
        val b: Float = Random.nextFloat()
        return (Color.argb(
            255, (r * 255).toInt(), (g * 255).toInt(), (b * 255)
                .toInt())
        )
    }

    /**[getColorFromList] function takes in a List of Colors and an index and returns the Color at the specified index.
    If the index is out of bounds, it returns a randomly generated Color using the randomColor function.
     *
     * @param list: It require a list of color
     * @param index: It require a current where you want find the value
     */
    private fun getColorFromList(list: List<Int>, index: Int): Int {
        return list.getOrElse(index) { randomColor() }

    }


    /**[getIconPositions] function takes in a List of Floats and return the position for icons
     */
    private fun getIconPositions(values: List<Float>): List<Float> {
        val sum = values.sum()
        val positions = mutableListOf(0f)
        values.forEach { value ->
            positions.add(positions.last() + value / sum * 100)
        }
        return positions
    }


    /**[convertStringInTime] function is an extension function which returns the time in h, m, and sec.
     */
    fun String.convertStringInTime() = Duration.parse(
        this
            .replaceFirst("(\\d+):(\\d{2}):(\\d{2}(?:\\.\\d+)?)".toRegex(), "PT$1H$2M$3S")
    ).toString()

    /**[getFirstTwoWords] function is an extension function which returns the first word of any
     * string if there are more than 1 word else it will revert single word or if there empty or
     * null string then it will revert empty string("").
     */
    fun String.getFirstTwoWords(): String {
        val words = this.trim().split(" ")
        return when (words.size) {
            0 -> ""
            1 -> words[0]
            else -> "${words[0]} ${words[1]}"
        }
    }


    /**
     * Converts a decimal number to 2 decimal places.
     *
     * @param value the decimal number to be converted
     * @return the converted decimal number to 2 decimal places
     */
    fun convertToTwoDecimalPlaces(value: Double): String {
        return "%.2f".format(value)
    }


    /**
     * [bitmapDescriptorResource] Converts a given drawable resource into a BitmapDescriptor object
     *
     * @param context Context to access the drawable resource
     * @param icon Drawable resource id
     *
     * @return BitmapDescriptor object created from the drawable resource
     */
    fun bitmapDescriptorResource(context: Context, icon: Int): BitmapDescriptor {
        val drawable = ContextCompat.getDrawable(context, icon)
        val bitmap = Bitmap.createBitmap(
            drawable!!.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

}