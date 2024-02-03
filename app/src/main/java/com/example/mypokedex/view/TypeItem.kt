package com.example.mypokedex.view

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.mypokedex.R
import com.example.mypokedex.util.ProjectResources
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class TypeItem(
    private val typeImage: Int,
    private val isDoubleDamage: Boolean = false
): Item<TypeItem.TypeViewHolder>() {
    class TypeViewHolder(itemView: View): GroupieViewHolder(itemView)
    override fun createViewHolder(itemView: View) = TypeViewHolder(itemView)
    override fun getLayout() = R.layout.type_item

    override fun bind(viewHolder: TypeViewHolder, position: Int) {
        val itemImage: ImageView = viewHolder.itemView.findViewById(R.id.type_image)
        itemImage.setImageResource(typeImage)
        if (isDoubleDamage){
            val typeDamageSize: TextView = viewHolder.itemView.findViewById(R.id.type_size_dmg)
            typeDamageSize.text = "4x"
            typeDamageSize.setTextColor(Color.RED)
        }

    }

    companion object{
        fun getTypeItemListByNames(list: List<String>): MutableList<TypeItem>{
            val typeList: MutableList<TypeItem> = mutableListOf()
            val copyList: MutableList<String> = mutableListOf()
            copyList.addAll(list)
            val namesDoubleDamage: MutableList<String> = mutableListOf()
            for (typeName in list){
                val image = ProjectResources.getImageByPokemonType(typeName)
                var count = 0
                for(copyName in copyList){
                    if (typeName == copyName){
                        count++
                    }
                }
                val repeatDouble = namesDoubleDamage.count{ it == typeName}
                if(count == 1){
                    typeList.add(TypeItem(image))
                } else if (count > 1 && repeatDouble == 0){
                    typeList.add(TypeItem(image,true))
                    namesDoubleDamage.add(typeName)
                }
            }
            return typeList
        }
    }

}