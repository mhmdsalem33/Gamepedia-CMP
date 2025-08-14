package org.gamepdia.coreNetwork.model.game



import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("added")
    val added: Int ? = -1 ,
    @SerialName("background_image")
    val backgroundImage: String ? = "" ,
    @SerialName("dominant_color")
    val dominantColor: String ? = "" ,
    @SerialName("id")
    val id: Int ? = -1 ,
//    @SerialName("metacritic")
//    val metacritic: Float,
    @SerialName("name")
    val name: String ? = "" ,
    @SerialName("playtime")
    val playtime: Int ? = -1 ,
    @SerialName("rating")
    val rating: Double,
    @SerialName("rating_top")
    val ratingTop: Int ? = -1 ,
    @SerialName("ratings_count")
    val ratingsCount: Int ? = -1 ,
    @SerialName("released")
    val released: String ? = ""  ,
    @SerialName("reviews_count")
    val reviewsCount: Int ? = -1 ,
    @SerialName("reviews_text_count")
    val reviewsTextCount: Int ? = -1 ,
    @SerialName("saturated_color")
    val saturatedColor: String ? = "" ,
    @SerialName("slug")
    val slug: String ? = "" ,
    @SerialName("suggestions_count")
    val suggestionsCount: Int ? = -1 ,
    @SerialName("tba")
    val tba: Boolean  ? = false ,
    @SerialName("updated")
    val updated: String ? = ""

)