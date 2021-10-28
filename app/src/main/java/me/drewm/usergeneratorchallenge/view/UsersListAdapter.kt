package me.drewm.usergeneratorchallenge.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import me.drewm.usergeneratorchallenge.R
import me.drewm.usergeneratorchallenge.databinding.UserListItemBinding
import me.drewm.usergeneratorchallenge.model.User

class UsersListAdapter(
    private var usersList: List<User>,
    private val clickListener: ClickListener
) : RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {

    inner class ViewHolder(
        val binding: UserListItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = usersList[position]

        holder.binding.apply {
            // replace w Coil call
            // include placeholder image
            //replace w actual user data
            picture.load(user.picture.thumbnail) {
                crossfade(true)
                placeholder(R.drawable.user_picture_placeholder)
                transformations(CircleCropTransformation())
            }
//            picture.setImageDrawable()
            name.text = "${user.name.first} ${user.name.last}"
            // apply data to other view elements
            card.setOnClickListener {
                clickListener.onUserClicked(user)
            }
        }
    }

    override fun getItemCount(): Int = usersList.size

    fun updateUsersList(usersList: List<User>) {
        this.usersList = usersList
        notifyItemRangeChanged(0, usersList.size)
    }

    interface ClickListener {
        fun onUserClicked(user: User)
    }
}
