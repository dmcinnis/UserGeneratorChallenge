package me.drewm.usergeneratorchallenge

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import me.drewm.usergeneratorchallenge.model.*
import me.drewm.usergeneratorchallenge.repo.UserDataRepository
import me.drewm.usergeneratorchallenge.viewmodel.Event
import me.drewm.usergeneratorchallenge.viewmodel.UsersListViewModel
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UsersListViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private val mockUserDataRepository = mockk<UserDataRepository>(relaxed = true)

    private val testUserData = listOf(
        User(
            name = Name(first = "Joe", last = "Beef"),
            dob = DateOfBirth(age = "82"),
            email = "asdf@gmail.com",
            cell = "111-222-3333",
            location = Location(Timezone(description = "New York, NY (EST)")),
            picture = Picture(thumbnail = "dummyUrl")
        ),
        User(
            name = Name(first = "Jill", last = "Beef"),
            dob = DateOfBirth(age = "80"),
            email = "asdf2@gmail.com",
            cell = "444-555-6666",
            location = Location(Timezone(description = "San Francisco, CA (PST)")),
            picture = Picture(thumbnail = "dummyUrl2")
        ),
    )

    private lateinit var viewModel: UsersListViewModel

    @Before
    fun setup() {
        coEvery { mockUserDataRepository.getUserListData(any()) } returns testUserData
    }

    @Test
    fun `retrieve user data on init and update flow if successful`() {
        coroutineRule.testDispatcher.runBlockingTest {
            initViewModel()

            MatcherAssert.assertThat(
                viewModel.usersDataFlow.value,
                CoreMatchers.equalTo(testUserData)
            )
        }
    }

    @Test
    fun `send event with user data on user clicked`() {
        coroutineRule.testDispatcher.runBlockingTest {
            initViewModel()

            viewModel.onUserClicked(testUserData[0])

            MatcherAssert.assertThat(
                viewModel.eventsFlow.first(),
                CoreMatchers.equalTo(Event.NavigateToUserDetails(testUserData[0]))
            )
        }
    }

    // TODO with improved error handling
//    @Test
//    fun `retrieve user data on init and handle error if unsuccessful`() {
//    }

    private fun initViewModel() {
        viewModel = UsersListViewModel(mockUserDataRepository)
    }
}
