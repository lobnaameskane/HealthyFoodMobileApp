package com.example.foodcollection.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodcollection.*
// L'interface graphique associé au recyclerView
class PlantAdapter(val context: MainActivity, private val platList: List<PlatModel>, private val layoutId: Int): RecyclerView.Adapter<PlantAdapter.ViewHolder>() {
    //Boite pour ranger tous les composants à controller
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val platImage = view.findViewById<ImageView>(R.id.image_item)
        val platName : TextView?= view.findViewById(R.id.name_item)
        val platDescription : TextView?= view.findViewById(R.id.description_item)
        val starIcon = view.findViewById<ImageView>(R.id.star_icon)
    }
//Injecter layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).
                inflate(layoutId , parent , false)
    return ViewHolder(view)
    }
    //Recuperer les informations relatives a chaque plante
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val currentPlant = platList[position] //recuperer la plante en cours


        //recuperer repository
        val repo = PlatRepository()
        //on utilise glide pour recupere limage a partir de son URL => ajouter au composant
        Glide.with(context).load(Uri.parse(currentPlant.imageUrl)).into(holder.platImage)
        //modifier nom  descrip par defaut
        holder.platName?.text= currentPlant.name
        holder.platDescription?.text= currentPlant.recette
        //verifier si la plante a ete liker ou non
        if(currentPlant.liked){
            holder.starIcon.setImageResource(R.drawable.ic_star)
        }
        else{
            holder.starIcon.setImageResource(R.drawable.ic_unstar)
        }
        //rajputer une itnteration sur cettee etoile
        holder.starIcon.setOnClickListener {
            //inverser si le btton est like lu nn
            currentPlant.liked = !currentPlant.liked
            //mettre a jour lobjet plante
            repo.updatePlat(currentPlant)

        }
    // Inter action lors du clic sur une plante
        holder.itemView.setOnClickListener {
            // Afficher le popoup
            PlantPopup(this ,currentPlant ).show()
        }
    } /// ? : si la valeur est null laisser la valeur par defaut


    //Combien ditems afficher par defaut
    override fun getItemCount(): Int = platList.size


}