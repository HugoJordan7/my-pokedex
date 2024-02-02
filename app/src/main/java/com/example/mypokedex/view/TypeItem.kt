package com.example.mypokedex.view

import android.view.View
import android.widget.ImageView
import com.example.mypokedex.R
import com.example.mypokedex.util.ProjectResources
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class TypeItem(
    private val typeImage: Int
): Item<TypeItem.TypeViewHolder>() {
    class TypeViewHolder(itemView: View): GroupieViewHolder(itemView)
    override fun createViewHolder(itemView: View) = TypeViewHolder(itemView)
    override fun getLayout() = R.layout.type_item

    override fun bind(viewHolder: TypeViewHolder, position: Int) {
        val itemImage: ImageView = viewHolder.itemView.findViewById(R.id.type_image)
        itemImage.setImageResource(typeImage)
    }

    companion object{
        fun getTypeItemListByNames(list: List<String>): List<TypeItem>{
            val typeList: MutableList<TypeItem> = mutableListOf()
            for (typeName in list){
                val image = ProjectResources.getImageByPokemonType(typeName)
                typeList.add(TypeItem(image))
            }
            return typeList
        }
    }

}