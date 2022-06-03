package com.example.foodcollection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.foodcollection.fragments.AddPlantFragment
import com.example.foodcollection.fragments.CollectionFragment
import com.example.foodcollection.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Charger notre plante repository



        //importer navigationView
        val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        navigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_page -> {
                    loadFragment(HomeFragment(this))
                    return@setOnItemSelectedListener true
                }

                R.id.collection_page -> {
                    loadFragment(CollectionFragment(this))
                    return@setOnItemSelectedListener true
                }
                R.id.add_plant_page -> {
                    loadFragment(AddPlantFragment(this))
                    return@setOnItemSelectedListener true
                }
                else -> false
            }

        }
    }
        private fun loadFragment(fragment : Fragment) {
            val repo = PlatRepository()
            //mettre ajour la liste des plates
            repo.updateData {

                //Injecter fragment dans notre boite frgmrnt container
                //support... getion des fragments dans android
                val transaction = supportFragmentManager.beginTransaction()
                //Garce a transaction on va remplacer fragment container par notre fragmnt home fragmnt
                transaction.replace(R.id.fragment_container, fragment)
                transaction.addToBackStack(null)
                transaction.commit() // envoyer changement
            }

        }




}