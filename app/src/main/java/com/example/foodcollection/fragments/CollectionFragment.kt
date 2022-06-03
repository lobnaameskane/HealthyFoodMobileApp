package com.example.foodcollection.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodcollection.MainActivity
import com.example.foodcollection.PlatRepository.Singleton.platList
import com.example.foodcollection.R
import com.example.foodcollection.adapter.PlantAdapter
import com.example.foodcollection.adapter.PlantItemDecoration

class CollectionFragment(private val context : MainActivity) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_collection , container , false )
        //Recuperer ùa recyclerview
        val collectionRecyclerView = view.findViewById<RecyclerView>(R.id.collection_recycler_list)
       collectionRecyclerView.adapter = PlantAdapter(context , platList.filter { it.liked }, R.layout.item_vertical_plant)
        collectionRecyclerView.layoutManager = LinearLayoutManager(context)
        collectionRecyclerView.addItemDecoration(PlantItemDecoration())
        return view
    }

}