package ipca.aulas.shopping.ui.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import ipca.aulas.shopping.ui.models.Item

object ItemRepository {

    val db = Firebase.firestore

    fun add(listId: String, item: Item) {
        db.collection("listItems")
            .document(listId)
            .collection("Items")
            .add(item)
    }

    fun remove(listId : String, itemId: String ) {
        db.collection("listItems")
            .document(listId)
            .collection("Items")
            .document(itemId)
            .delete()
    }


    fun update(listId : String, item: Item){
        db.collection("listItems")
            .document(listId)
            .collection("Items")
            .document(item.docId!!)
            .set(item)
    }


    fun getAll( listId : String, onResult : (List<Item>,String?)->Unit) {

        db.collection("listItems")
            .document(listId)
            .collection("Items")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    onResult(emptyList(),e.message)
                    return@addSnapshotListener
                }

                val items = ArrayList<Item>()
                for (doc in value!!) {
                    val item = doc.toObject(Item::class.java)
                    item.docId = doc.id
                    items.add(item)
                }
                onResult(items,null)
            }
    }
}