package data.entity

data class Medicine(
    val id: Int? = null,
    val name: String,
    val description: String?,
    val expirationDateUTC: String?,
    val typeId: Int?
)
