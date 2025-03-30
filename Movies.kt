package com.example.bingebuddybca.model

import android.os.Parcel
import android.os.Parcelable

data class Movies (
    var documentId: String? = null,
    var categoryType: String? = null,
    var movieName: String? = null,
    var movieDescription: String? = null,
    var movieDrawableName: String? = null,
    var movieDrawableNamel: String? = null,
    var movieRating: String? = null,
    var movieYear: String? = null,
    var movieHr: String? = null,
    var moviePg: String? = null,
    var movieCast: String? = null,
    var movieGenres: String? = null,

    var movieDirector: String? = null,
    var movieWriter: String? = null,
    var cast1Img: String? = null,
    var cast2Img: String? = null,
    var cast3Img: String? = null,
    var cast4Img: String? = null,
    var cast5Img: String? = null,
    var cast6Img: String? = null,
    var cast1Name: String? = null,
    var cast2Name: String? = null,
    var cast3Name: String? = null,
    var cast4Name: String? = null,
    var cast5Name: String? = null,
    var cast6Name: String? = null,
    var movieImg1: String? = null,
    var movieImg2: String? = null,
    var movieImg3: String? = null,
    var movieImg4: String? = null,
    var movieImg5: String? = null,
    var violence: String? = null,
    var nudity: String? = null,
    var profanity: String? = null,
    var alcohol: String? = null,
    var frightening: String? = null,
    var clr0: String? = null,
    var clr1: String? =null,
    var clr2: String? = null,
    var clr3: String? = null,
    var clr4: String? = null,
    var summary: String? = null,
    var tagline: String? = null,
    var date: String? = null,
    var origin: String? = null,
    var language: String? = null,
    var official: String? = null,
    var company: String? = null,
    ) : Parcelable{
        constructor(parcel: Parcel):this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
        )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(documentId)
        parcel.writeString(categoryType)
        parcel.writeString(movieName)
        parcel.writeString(movieDescription)
        parcel.writeString(movieDrawableName)
        parcel.writeString(movieDrawableNamel)
        parcel.writeString(movieRating)
        parcel.writeString(movieYear)
        parcel.writeString(movieHr)
        parcel.writeString(moviePg)
        parcel.writeString(movieCast)
        parcel.writeString(movieGenres)
        parcel.writeString(movieDirector)
        parcel.writeString(movieWriter)
        parcel.writeString(cast1Img)
        parcel.writeString(cast2Img)
        parcel.writeString(cast3Img)
        parcel.writeString(cast4Img)
        parcel.writeString(cast5Img)
        parcel.writeString(cast6Img)
        parcel.writeString(cast1Name)
        parcel.writeString(cast2Name)
        parcel.writeString(cast3Name)
        parcel.writeString(cast4Name)
        parcel.writeString(cast5Name)
        parcel.writeString(cast6Name)
        parcel.writeString(movieImg1)
        parcel.writeString(movieImg2)
        parcel.writeString(movieImg3)
        parcel.writeString(movieImg4)
        parcel.writeString(movieImg5)
        parcel.writeString(violence)
        parcel.writeString(nudity)
        parcel.writeString(profanity)
        parcel.writeString(alcohol)
        parcel.writeString(frightening)
        parcel.writeString(clr0)
        parcel.writeString(clr1)
        parcel.writeString(clr2)
        parcel.writeString(clr3)
        parcel.writeString(clr4)
        parcel.writeString(summary)
        parcel.writeString(tagline)
        parcel.writeString(date)
        parcel.writeString(origin)
        parcel.writeString(language)
        parcel.writeString(official)
        parcel.writeString(company)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movies> {
        override fun createFromParcel(parcel: Parcel): Movies {
            return Movies(parcel)
        }

        override fun newArray(size: Int): Array<Movies?> {
            return arrayOfNulls(size)
        }
    }


}
