package com.example.foodcollection

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.util.*

class PlatRepository {

    object Singleton {
        //Donner le lien pour acceder a bucket
       private val BUCKET_URL : String ="gs://foodcollection-24fdd.appspot.com"
        //se connecter a lespace de stockage
        val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(BUCKET_URL)
        //Se connecter a la reference plats
        val databaseRef = FirebaseDatabase.getInstance().getReference("plants")
        //Creer une liste qui va cobtenir les palntes
        val platList = arrayListOf<PlatModel>()
    }

fun updateData(callback : () -> Unit) {
    // absorber les données recuperer depuis la databse references = > liste de plantes
    Singleton.databaseRef.addValueEventListener(object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
// Retire les anciens : mettre a jour la base 
         Singleton.platList.clear()
//Recoler la liste DataSnapshot: objet qui contient toutes les donnes recuperes sous forme dune liste dobjet et pas model.
            for (ds in snapshot.children) // recupere tous les enfants
                //construire un objet plants a partir des childrens
            {
                val plant = ds.getValue(PlatModel :: class.java)
                // Verifier que la plante nest pas null
                if(plant!=null){
                         Singleton.platList.add(plant)
                    }
                }
            // actiooner le claaback
            callback()

        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    })
}
    //Creer une fonction pour envoyer des fichiers sur Storage
    fun uploadImage(file: Uri){
        // verifier que ce fichier nest pas null
        if(file != null){
            val filename = UUID.randomUUID().toString() + ".jpg"
            val ref = Singleton.storageReference.child(filename)
            val uploadTask = ref.putFile(file)


            //demarrer la tache denvoi
            uploadTask.continueWithTask(com.google.android.gms.tasks.Continuation<UploadTask.TaskSnapshot, Task<Uri>> {  task ->
                if(!task.isSuccessful){
                    task.exception?.let { throw  it }
                }
    return@Continuation ref.downloadUrl

            }).addOnCompleteListener { task ->
                // verifier fonctionnmnt
                if(task.isSuccessful){
                    val downloadURI = task.result
                }
            }
                // SI il y a eu un probleme lors de lenvoi du fichier
              /*  if(!task.isSuccess){
                   task.exception.let{ throw it}
                }*/


        }

    }

    //mettre a jour l'objet plat dans db
    fun updatePlat(plat : PlatModel) =Singleton.databaseRef.child(plat.id).setValue(plat)

    //Inserer un nouveau plat
    fun insertPlat(plat : PlatModel) =Singleton.databaseRef.child(plat.id).setValue(plat)


    //Supprimer un plat de la base
    fun deletePlat(plat : PlatModel) = Singleton.databaseRef.child(plat.id).removeValue()
}