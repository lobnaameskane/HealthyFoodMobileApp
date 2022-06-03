package com.example.foodcollection

//class pour definir les proprietes de chaque plat
class PlatModel(
    val id: String = "id123",
    val name : String ="plat1",
    val recette : String = "Recette du plat",
    val imageUrl : String ="Rien",
    val meal : String ="Diner",
    val time : String="moins de 30min",
    var liked : Boolean =false

)

