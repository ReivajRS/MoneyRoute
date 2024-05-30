package com.example.moneyroute.movements.domain

class GetCategoriesUseCase() {
    private val categories = listOf(
        "General",
        "Ropa",
        "Comida",
        "Alquiler",
        "Casa",
        "Seguros",
        "Salud",
        "Viajes",
        "Mascotas",
        "Libros",
        "Regalos",
        "Electricidad",
        "Agua",
        "Internet",
        "Coche",
        "Educacion",
        "Deportes",
        "Musica",
        "Amigos"
    )

    suspend operator fun invoke(): List<String> = categories
}