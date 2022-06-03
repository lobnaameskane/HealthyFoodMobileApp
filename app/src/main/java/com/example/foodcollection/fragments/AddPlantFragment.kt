package com.example.foodcollection.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.foodcollection.MainActivity
import com.example.foodcollection.PlatRepository
import com.example.foodcollection.R
import android.widget.EditText
import android.widget.Spinner

import com.example.foodcollection.PlatModel
import java.util.*



class AddPlantFragment(
    private val context : MainActivity
    ): Fragment(){
      val imagePlat = arrayListOf<String>("https://cdn.pixabay.com/photo/2016/11/29/11/15/fruits-1869132__340.jpg", "https://cdn.pixabay.com/photo/2018/07/31/07/55/tomatoes-3574427__340.jpg","https://cdn.pixabay.com/photo/2016/11/18/19/00/bread-1836411__340.jpg" ,"https://cdn.pixabay.com/photo/2016/11/19/14/18/oatmeal-1839515__340.jpg" ,"https://cdn.pixabay.com/photo/2019/02/25/16/46/breakfast-4020028__340.jpg" ,"https://cdn.pixabay.com/photo/2017/03/26/11/53/hors-doeuvre-2175326__340.jpg" ,"https://cdn.pixabay.com/photo/2021/07/05/01/44/taco-6387934__340.jpg")

    private var uploadedImage: ImageView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_add_plant,container,false)

        // recupere uploadede image pour lui associer son composant
     uploadedImage = view.findViewById(R.id.preview_image)

      //  // Recuperer le bouton pour charger limage
        val pickupImageButton = view.findViewById<Button>(R.id.upload_button)
        val confirmButton = view.findViewById<Button>(R.id.confirm_button)
        confirmButton.setOnClickListener { sendForm(view) }
        //Lorsque on clique dessus ca ouvre les images du telephone
       pickupImageButton.setOnClickListener{
         pickupImage()
       }


     /*   pickupImageButton.setOnClickListener { view ->
            {
                //Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                val  intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
               launcher.launch(intent);
            }
        }*/

        return view
    }

    private fun uploadImage() {

    }



   private fun sendForm(view : View){
       val repo = PlatRepository()
        val platName = view.findViewById<EditText>(R.id.name_input).text.toString()
        val recette = view.findViewById<EditText>(R.id.description_input).text.toString()
        val meal = view.findViewById<Spinner>(R.id.grow_spinner).selectedItem.toString()
        val time = view.findViewById<Spinner>(R.id.water_spinner).selectedItem.toString()
       val image = imagePlat.random()
       val plat = PlatModel(
        UUID.randomUUID().toString(),
        platName,
        recette,
           image,
           meal,
           time
    )
repo.insertPlat(plat)

   }



    // Receiver


    /*  private val launcher = registerForActivityResult(
          StartActivityForResult()
      ) { result: ActivityResult ->
          if (result.resultCode == Activity.RESULT_OK
              && result.data != null
          ) {
              val photoUri = result.data!!.data
              //use photoUri here
          }
      }


*/
      private fun pickupImage() {
          //creation d'intent
         val intent = Intent()
          intent.type = "image/"
          //Recuperation de limage
          intent.action = Intent.ACTION_GET_CONTENT
         // demarer instruction et attendre un resultat
          startActivityForResult(Intent.createChooser(intent, "select picture"),46 )
      }
    /*startActivityForResult
**/
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==47 && resultCode== Activity.RESULT_OK){
            // verifier si les donners sont nulles
            if(data== null || data.data== null) return
            //recuperer limage sleelctionni
            val selectedImage= data.data
            //Mettre a jour lappercu de limage
            uploadedImage?.setImageURI(selectedImage)

            val repo = PlatRepository()
            repo.uploadImage(selectedImage!!)
        }
    }


}
