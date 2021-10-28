package me.drewm.usergeneratorchallenge

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import me.drewm.usergeneratorchallenge.model.*
import me.drewm.usergeneratorchallenge.network.UserDataService
import me.drewm.usergeneratorchallenge.repo.UserDataRepository
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class UserDataRepositoryTest {

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private val testUserDataResponse = Response.success(
        UserDataResults(
            listOf(
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
        )
    )

    private lateinit var userDataRepository: UserDataRepository
    private val mockUserDataService = mockk<UserDataService>()

    @Before
    fun setup() {
        userDataRepository = UserDataRepository(mockUserDataService)
    }

    @Test
    fun `successfully return user data`() = runBlocking {
        coEvery { mockUserDataService.getUserListData(any()) } returns testUserDataResponse
        val response = userDataRepository.getUserListData(25)
        MatcherAssert.assertThat(response[0], CoreMatchers.instanceOf(User::class.java))
        response.run {
            MatcherAssert.assertThat(get(0).name.first, CoreMatchers.`is`("Joe"))
            MatcherAssert.assertThat(get(0).name.last, CoreMatchers.`is`("Beef"))
            MatcherAssert.assertThat(get(0).dob.age, CoreMatchers.`is`("82"))
            MatcherAssert.assertThat(get(0).email, CoreMatchers.`is`("asdf@gmail.com"))
            MatcherAssert.assertThat(get(0).cell, CoreMatchers.`is`("111-222-3333"))
            MatcherAssert.assertThat(get(0).location.timezone.description,
                CoreMatchers.`is`("New York, NY (EST)"))
            MatcherAssert.assertThat(get(0).picture.thumbnail, CoreMatchers.`is`("dummyUrl"))
        }
    }

    // TODO with improved error handling
//    @Test
//    fun `return correct failure`() = runBlockingTest {
//    }
}