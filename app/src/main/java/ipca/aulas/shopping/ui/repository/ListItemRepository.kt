package ipca.aulas.shopping.ui.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import ipca.aulas.shopping.ui.models.ListItem

object ListItemRepository {

    val db = Firebase.firestore

    fun add(listItem: ListItem) {
        db.collection("listItems")
            .add(listItem)
    }

    fun remove(listId : String) {
        db.collection("listItems")
            .document(listId)
            .delete()
    }


    fun update(listItem : ListItem){
        db.collection("listItems")
            .document(listItem.docId!!)
            .set(listItem)
    }


    fun getAll( onResult : (List<ListItem>,String?)->Unit) {

        db.collection("listItems")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    onResult(emptyList(),e.message)
                    return@addSnapshotListener
                }

                val listItems = ArrayList<ListItem>()
                for (doc in value!!) {
                    val listItem = doc.toObject(ListItem::class.java)
                    listItem.docId = doc.id
                    listItems.add(listItem)
                }
                onResult(listItems,null)
            }
    }

}