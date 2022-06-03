package com.example.foodcollection.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.foodcollection.MainActivity
import com.example.foodcollection.PlatRepository.Singleton.platList
import com.example.foodcollection.R
import com.example.foodcollection.adapter.PlantAdapter
import com.example.foodcollection.adapter.PlantItemDecoration

//Pour dire que la class HomeFragmnt fait party du fragment on met : equivalent a extends en java


class HomeFragment(private val context : MainActivity): Fragment(){
// Lorsque on fait appel a la focntion oncreateview qui va venir injecter fraglent home sur la page
    //fonction pour injecter le layout fragment
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater?.inflate(R.layout.fragment_home , container , false)

    //CREER UNE LISTE QUI VA STOKER LES PLANTES
 //   val plantlist = arrayListOf<PlantModel>()
    //Enregistere la premiere plante dans la liste
   /* plantlist.add(PlantModel(
        "Pissenlit" ,
        "jaune Soleil",
        "https://cdn.pixabay.com/photo/2016/07/11/21/23/water-lily-1510707__340.jpg" ,
        false
    ))

    //Enregistere la deuxieme plante dans la liste
    plantlist.add(PlantModel(
        "rose" ,
        "JOLI",
        "https://cdn.pixabay.com/photo/2013/07/21/13/00/rose-165819__340.jpg",
        false
    ))

    //Enregistere la troisieme plante dans la liste
    plantlist.add(PlantModel(
        "TULIPE" ,
        "TULIPE2",
        "https://cdn.pixabay.com/photo/2017/02/15/13/40/tulips-2068692__480.jpg"   ,
        false
    ))


    //Enregistere la Quatriemr plante dans la liste
    plantlist.add(PlantModel(
          "TULIPE" ,
        "TULIPE2",
        "https://cdn.pixabay.com/photo/2017/02/15/13/40/tulips-2068692__480.jpg"   ,
        false
    ))

*/

    val horizontalRecycleView = view.findViewById<RecyclerView>(R.id.horizontal_recycle_view)
    horizontalRecycleView.adapter= PlantAdapter(context , platList ,R.layout.item_horizontal_plant)



    //Recuperer le second recyclerview
    val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycle_view)
    verticalRecyclerView.adapter =PlantAdapter(context, platList , R.layout.item_vertical_plant)
    verticalRecyclerView.addItemDecoration(PlantItemDecoration())
    return view
            }
}