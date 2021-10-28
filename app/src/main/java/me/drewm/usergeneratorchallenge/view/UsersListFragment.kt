package me.drewm.usergeneratorchallenge.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.drewm.usergeneratorchallenge.R
import me.drewm.usergeneratorchallenge.UserGeneratorChallengeApp
import me.drewm.usergeneratorchallenge.databinding.UsersListFragmentBinding
import me.drewm.usergeneratorchallenge.model.User
import me.drewm.usergeneratorchallenge.repo.UserDataRepository
import me.drewm.usergeneratorchallenge.viewmodel.Event
import me.drewm.usergeneratorchallenge.viewmodel.UsersListViewModel
import me.drewm.usergeneratorchallenge.viewmodel.UsersListViewModelFactory

class UsersListFragment : Fragment(), UsersListAdapter.ClickListener {

    // might need to move this into onViewCreated? activity might not be available yet
    // and this feels a bit unweildy / non-DRY as more dependencies get added
    private val viewModel by viewModels<UsersListViewModel> {
        UsersListViewModelFactory(
            UserDataRepository(
                (activity?.application as UserGeneratorChallengeApp).appContainer.userApiService
            )
        )
    }

    private var nullableViewBinding: UsersListFragmentBinding? = null
    private val viewBinding get() = nullableViewBinding!!

    private lateinit var usersListAdapter: UsersListAdapter

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

        initUsersList()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.usersDataFlow.collect {
                        usersListAdapter.updateUsersList(it)
                    }
                }

                launch {
                    viewModel.eventsFlow.collect {
                        when (it) {
                            is Event.ShowSpinner -> {}
                            is Event.HideSpinner -> {}
                            is Event.NavigateToUserDetails -> {
                                // pass this user data
                                val bundle = bundleOf("user" to it.user)
                                findNavController().navigate(R.id
                                    .action_usersListFragment_to_userDetailsFragment, bundle)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun initUsersList() {
        usersListAdapter = UsersListAdapter(emptyList(), this)

        viewBinding.usersList.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = usersListAdapter
        }
    }

    override fun onUserClicked(user: User) {
        viewModel.onUserClicked(user)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        nullableViewBinding = null
    }
}
