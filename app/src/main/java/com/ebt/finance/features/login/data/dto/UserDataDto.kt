package com.ebt.finance.features.login.data.dto


import com.ebt.finance.features.login.domain.models.UserData
import com.google.gson.annotations.SerializedName

data class UserDataDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("no_identitas")
    val noIdentitas: String,
    @SerializedName("tempat_lahir")
    val tempatLahir: String,
    @SerializedName("tgl_lahir")
    val tglLahir: String,
    @SerializedName("no_rek")
    val noRek: String,
    @SerializedName("role_id")
    val roleId: String,
    @SerializedName("posisi_id")
    val posisiId: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("domisili")
    val domisili: String,
    @SerializedName("no_tlp")
    val noTlp: String
)

fun UserDataDto.toUserData(): UserData {
    return UserData(
        id,
        name,
        email,
        noIdentitas,
        tempatLahir,
        tglLahir,
        noRek,
        roleId,
        posisiId,
        status,
        domisili,
        noTlp
    )
}