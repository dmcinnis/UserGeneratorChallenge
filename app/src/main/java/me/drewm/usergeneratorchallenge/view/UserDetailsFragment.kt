package me.drewm.usergeneratorchallenge.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.drewm.usergeneratorchallenge.databinding.UserDetailsFragmentBinding

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
      // do stuff
    }

    override fun onDestroyView() {
        super.onDestroyView()
        nullableViewBinding = null
    }
}