package com.example.foodcollection

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.foodcollection.adapter.PlantAdapter

class PlantPopup(private val adapter : PlantAdapter,
                 private val currentPlat : PlatModel
) : Dialog(adapter.context){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_plants_details)
        setupComponents()
        setupCloseButton()
        setupDeleteButton()
        setupStarButton()
    }

    private fun updateSter(button : ImageView){
        if(currentPlat.liked){
            button.setImageResource(R.drawable.ic_star)
        }
        else{
            button.setImageResource(R.drawable.ic_unstar)
        }

    }

    private fun setupStarButton(){
        val starButton= findViewById<ImageView>(R.id.unstar_button)
        updateSter(starButton)
        //interaction
        starButton.setOnClickListener {
            currentPlat.liked=!currentPlat.liked
            val repo = PlatRepository()
            repo.updatePlat(currentPlat)
            updateSter(starButton)
        }
    }


    private fun setupDeleteButton(){
        findViewById<ImageView>(R.id.delete_button).setOnClickListener {
        val repo = PlatRepository()
            repo.deletePlat(currentPlat)
        }
    }

    private fun setupCloseButton(){
        findViewById<ImageView>(R.id.close_button).setOnClickListener {
            dismiss()
        }    }

    private fun setupComponents() {
        // Actualiser limage de la plante
        val plantImage = findViewById<ImageView>(R.id.image_item)
        Glide.with(adapter.context).load(Uri.parse(currentPlat.imageUrl)).into(plantImage)
        //Actualise rle nom de la plante
        findViewById<TextView>(R.id.popup_plant_name).text=currentPlat.name
        findViewById<TextView>(R.id.popup_plant_description_subtitle).text=currentPlat.recette
        findViewById<TextView>(R.id.popup_plant_grow_subtitle).text=currentPlat.meal
        findViewById<TextView>(R.id.popup_plant_water_subtitle).text=currentPlat.time

    }

}