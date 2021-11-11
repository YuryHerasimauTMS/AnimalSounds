package com.example.animalsounds

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.animalsounds.mvvm.model.Animal

class CustomRecyclerAdapter(private val names: MutableList<Animal>) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var animalPicture: ImageView? = null

        init {
            animalPicture = itemView.findViewById(R.id.animal_pic)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_animal, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        names.forEach {
            it.animalAvatar
            holder.animalPicture?.setBackgroundResource(R.drawable.image_leaf)
        }
    }

    override fun getItemCount(): Int {
        return names.size
    }
}
