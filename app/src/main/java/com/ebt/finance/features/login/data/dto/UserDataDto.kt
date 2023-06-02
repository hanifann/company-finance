package com.ebt.finance.features.login.data.dto


import com.ebt.finance.features.login.domain.models.UserData
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserDataDto(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("email")
    val email: String,
    @JsonProperty("no_identitas")
    val noIdentitas: String,
    @JsonProperty("tempat_lahir")
    val tempatLahir: String,
    @JsonProperty("tgl_lahir")
    val tglLahir: String,
    @JsonProperty("no_rek")
    val noRek: String,
    @JsonProperty("role_id")
    val roleId: String,
    @JsonProperty("posisi_id")
    val posisiId: String,
    @JsonProperty("status")
    val status: String,
    @JsonProperty("domisili")
    val domisili: String,
    @JsonProperty("no_tlp")
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