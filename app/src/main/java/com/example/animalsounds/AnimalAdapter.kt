package com.example.animalsounds

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animalsounds.databinding.ItemAnimalBinding
import com.example.animalsounds.mvvm.model.Animal

class AnimalAdapter(private val action: (Animal) -> Unit) :
    RecyclerView.Adapter<AnimalAdapter.MyViewHolder>() {

    private val animalList = mutableListOf<Animal>()
    private val animalPicIdList = mutableMapOf<Animal, Int>()

    @SuppressLint("NotifyDataSetChanged")
    fun setAnimalsList(list: List<Animal>, mapOfPic: MutableMap<Animal, Int>) {
        animalList.clear()
        animalList.addAll(list)
        animalPicIdList.clear()
        animalPicIdList.putAll(mapOfPic)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View, val binding: ItemAnimalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var animalPicture: ImageView? = null

        init {
            animalPicture = itemView.findViewById(R.id.animal_pic)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_animal, parent, false)
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemAnimalBinding>(
            inflater,
            R.layout.item_animal,
            parent,
            false
        )
        return MyViewHolder(itemView, binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentAnimal = animalList[position]
        val currentAnimalPicId = animalPicIdList.getValue(currentAnimal)
        holder.binding.animal = currentAnimal
        holder.binding.root.setOnClickListener {
            action.invoke(currentAnimal)
        }
        Glide
            .with(holder.binding.root)
            .load(currentAnimalPicId)
            .into(holder.binding.animalPic)
    }

    override fun getItemCount(): Int {
        return animalList.size
    }
}
