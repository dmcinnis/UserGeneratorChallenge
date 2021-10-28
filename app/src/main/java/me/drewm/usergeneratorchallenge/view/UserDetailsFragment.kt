package me.drewm.usergeneratorchallenge.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.CircleCropTransformation
import me.drewm.usergeneratorchallenge.R
import me.drewm.usergeneratorchallenge.databinding.UserDetailsFragmentBinding
import me.drewm.usergeneratorchallenge.model.User

class UserDetailsFragment : Fragment() {

    private var nullableViewBinding: UserDetailsFragmentBinding? = null
    private val viewBinding get() = nullableViewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nullableViewBinding = UserDetailsFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = arguments?.getSerializable("user") as User
        viewBinding.name.text = user.name.last
        viewBinding.picture.load(user.picture.large) {
            crossfade(true)
            placeholder(R.drawable.user_picture_placeholder)
            transformations(CircleCropTransformation())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        nullableViewBinding = null
    }
}