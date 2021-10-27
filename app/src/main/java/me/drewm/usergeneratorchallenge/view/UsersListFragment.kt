package me.drewm.usergeneratorchallenge.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import me.drewm.usergeneratorchallenge.R
import me.drewm.usergeneratorchallenge.UserGeneratorChallengeApp
import me.drewm.usergeneratorchallenge.databinding.UsersListFragmentBinding
import me.drewm.usergeneratorchallenge.repo.UserDataRepository
import me.drewm.usergeneratorchallenge.viewmodel.UsersListViewModel
import me.drewm.usergeneratorchallenge.viewmodel.UsersListViewModelFactory

class UsersListFragment : Fragment() {

    // might need to move this into onViewCreated? activity might not be available yet
    // and this feels a bit unweildy / non-DRY as more dependencies get added
    private val viewModel by viewModels<UsersListViewModel> {
        UsersListViewModelFactory(
            UserDataRepository(
                (activity?.application as UserGeneratorChallengeApp).appContainer
                    .userApiService
            )
        )
    }

    private var nullableViewBinding: UsersListFragmentBinding? = null
    private val viewBinding get() = nullableViewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nullableViewBinding = UsersListFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.doSomething()

        viewBinding.button.setOnClickListener {
            findNavController().navigate(R.id.action_usersListFragment_to_userDetailsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        nullableViewBinding = null
    }
}
