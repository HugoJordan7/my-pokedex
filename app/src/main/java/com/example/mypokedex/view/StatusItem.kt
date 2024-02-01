package com.example.mypokedex.view

import android.content.Context
import android.content.res.Resources
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.mypokedex.R
import com.example.mypokedex.model.Pokemon
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class StatusItem(
    private val statusText: String,
    private val statusIcon: Int,
    private val colorTheme: Int? = null
): Item<StatusItem.StatusViewHolder>() {

    companion object{
        fun getStatusItemList(context: Context, pokemon: Pokemon): List<StatusItem>{
            return mutableListOf(
                StatusItem(context.getString(R.string.hp,pokemon.stats[0].baseStat),R.drawable.ic_hp),
                StatusItem(context.getString(R.string.attack,pokemon.stats[1].baseStat),R.drawable.ic_attack),
                StatusItem(context.getString(R.string.defense,pokemon.stats[2].baseStat),R.drawable.ic_defense),
                StatusItem(context.getString(R.string.special_attack,pokemon.stats[3].baseStat),R.drawable.ic_special_attack),
                StatusItem(context.getString(R.string.special_defense,pokemon.stats[4].baseStat),R.drawable.ic_special_defense),
                StatusItem(context.getString(R.string.speed,pokemon.stats[5].baseStat),R.drawable.ic_speed)
            )
        }
    }

    inner class StatusViewHolder(itemView: View): GroupieViewHolder(itemView)
    override fun getLayout(): Int = R.layout.status_item
    override fun createViewHolder(itemView: View) = StatusViewHolder(itemView)

    override fun bind(viewHolder: StatusViewHolder, position: Int) {
        viewHolder.itemView.findViewById<TextView>(R.id.status_text).text = statusText
        viewHolder.itemView.findViewById<ImageView>(R.id.status_image).setImageResource(statusIcon)
    }
}