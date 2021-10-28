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
    ): View {
        nullableViewBinding = UserDetailsFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    // TODO ViewModel to accommodate more user interactions
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = arguments?.getSerializable("user") as User

        viewBinding.apply {
            picture.load(user.picture.large) {
                crossfade(true)
                placeholder(R.drawable.user_picture_placeholder)
                transformations(CircleCropTransformation())
            }
            name.text = getString(R.string.name, user.name.first, user.name.last)
            age.text = getString(R.string.age, user.dob.age)
            email.text = user.email
            cell.text = user.cell
            timezone.text = getString(R.string.timezone, user.location.timezone.description)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        nullableViewBinding = null
    }
}