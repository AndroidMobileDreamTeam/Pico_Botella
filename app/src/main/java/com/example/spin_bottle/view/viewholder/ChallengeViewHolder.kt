package com.example.spin_bottle.view.viewholder

import android.os.Bundle
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.spin_bottle_app.R
import com.example.spin_bottle_app.databinding.ItemChallengesBinding
import com.example.spin_bottle.model.Challenge

class ChallengeViewHolder(binding: ItemChallengesBinding, navController: NavController) : RecyclerView.ViewHolder(binding.root)  {
    val bindingItem = binding
    val navController = navController


    fun setItemChallenge(challenge: Challenge) {
        bindingItem.challenge.text = challenge.description

        /*bindingItem.tvName.text = inventory.name
        bindingItem.tvPrice.text = "$ ${inventory.price}"
        bindingItem.tvQuantity.text = "${inventory.quantity}"*/

        bindingItem.cvChallenge.setOnClickListener {
            // En teoria no deberia pasar nada si se presiona el cvChallnege
            /* val bundle = Bundle()
            bundle.putSerializable("clave", challenge)
            navController.navigate(R.id.action_firstFragment_to_secondFragment, bundle) */
        }

        bindingItem.editChallengeButton.setOnClickListener {
            // Codigo que guie a la ventana para editar reto // <============================== VEAN ESTO
        }

        bindingItem.deleteChallengeButton.setOnClickListener {
            // Codigo que guie a la ventana para eliminar reto // <============================== VEAN ESTO
        }

    }

}