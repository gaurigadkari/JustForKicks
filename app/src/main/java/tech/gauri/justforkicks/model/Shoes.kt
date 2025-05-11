package tech.gauri.justforkicks.model

data class Shoe(
    val id: Int,
    val title: String,
    val category: Category,
    val brand: String,
    val price: Float,
    val imageUrl: String
)

enum class Category(val label: String) {
    MEN("Men"),
    WOMEN("Women"),
    BOYS("Boys"),
    GIRLS("Girls"),
    SPORTS("Sports"),
    CASUAL("Casual")
}
