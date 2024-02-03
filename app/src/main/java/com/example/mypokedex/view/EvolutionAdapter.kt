package com.example.mypokedex.view

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedex.R
import com.example.mypokedex.model.EvolutionItem
import com.example.mypokedex.util.ProjectResources

class EvolutionAdapter(
    private val evolutionList: List<EvolutionItem>,
    private val context: Context
): RecyclerView.Adapter<EvolutionAdapter.EvolutionViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvolutionViewHolder {
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.evolution_item,parent,false)
        return EvolutionViewHolder(layout)
    }

    override fun onBindViewHolder(holder: EvolutionViewHolder, position: Int) {
        holder.bind(evolutionList[position])
    }

    override fun getItemCount(): Int {
        return evolutionList.size
    }

    inner class EvolutionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(evolutionItem: EvolutionItem){
            val itemName = itemView.findViewById<TextView>(R.id.name_evolution)
            val itemId = itemView.findViewById<TextView>(R.id.id_evolution)
            val itemImage = itemView.findViewById<ImageView>(R.id.img_evolution)
            val itemLayoutImage = itemView.findViewById<LinearLayout>(R.id.layout_img_evolution)
            itemName.text = evolutionItem.pokeName
            itemId.text = context.getString(R.string.evolution_id,evolutionItem.pokeId)
            itemImage.setImageResource(evolutionItem.pokeImage)
            val gradient = ContextCompat.getDrawable(
                context,
                R.drawable.evolutions_shape
            ) as GradientDrawable
            gradient.colors = ProjectResources.getIntArrayColors(evolutionItem.pokeColor,context)
            itemLayoutImage.background = gradient
        }
    }

}

