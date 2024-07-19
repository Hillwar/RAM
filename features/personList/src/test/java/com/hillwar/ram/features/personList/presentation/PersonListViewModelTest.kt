import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hillwar.ram.core.extensions.models.Person
import com.hillwar.ram.core.extensions.models.PersonLocation
import com.hillwar.ram.core.network.Request
import com.hillwar.ram.features.personList.domain.PersonListInteractor
import com.hillwar.ram.features.personList.presentation.PersonListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PersonListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: PersonListViewModel
    private val interactor: PersonListInteractor = mockk()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = PersonListViewModel(interactor)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun createPerson(id: Int, name: String) = Person(
        id = id,
        name = name,
        status = "Alive",
        species = "Human",
        image = "https://example.com/$name.png",
        location = PersonLocation(name = "Earth"),
        episode = listOf("S01E01", "S01E02")
    )

    private fun createPersonList() = listOf(
        createPerson(1, "Rick Sanchez"),
        createPerson(2, "Morty Smith")
    )

    @Test
    fun `loadPersons should update personList and handle pagination correctly`() = runTest(testDispatcher) {
        val persons = createPersonList()
        val personListResponse = Request.Success(
            com.hillwar.ram.core.extensions.models.PersonListResponse(
                info = com.hillwar.ram.core.extensions.models.PersonListInfo(pages = 2),
                results = persons
            )
        )
        coEvery { interactor.getPersonList(PersonListViewModel.FIRST_PAGE) } returns personListResponse

        viewModel.loadPersons().join()

        assertEquals(persons.shuffled().toSet(), viewModel.personList.value.toSet())
        assertEquals(PersonListViewModel.EMPTY, viewModel.loadError.value)
        assertEquals(false, viewModel.isLoading.value)
        assertEquals(2, viewModel.curPage)
    }

    @Test
    fun `loadPersons should handle error correctly`() = runTest(testDispatcher) {
        val errorMessage = "Network Error"
        coEvery { interactor.getPersonList(PersonListViewModel.FIRST_PAGE) } returns Request.Error(404, errorMessage)

        viewModel.loadPersons().join()

        assertEquals(errorMessage, viewModel.loadError.value)
        assertEquals(false, viewModel.isLoading.value)
    }

    @Test
    fun `searchPerson should filter list correctly`() = runTest(testDispatcher) {
        val persons = createPersonList()
        viewModel.personList.value = persons
        viewModel.cachedPersonList = persons
        viewModel.isSearchStarting = false

        viewModel.searchPerson("Rick").join()

        assertEquals(
            listOf(createPerson(1, "Rick Sanchez")),
            viewModel.personList.value
        )
        assertEquals(true, viewModel.isSearching.value)
    }

    @Test
    fun `searchPerson should restore full list on empty query`() = runTest(testDispatcher) {
        val persons = createPersonList()
        viewModel.personList.value = persons
        viewModel.cachedPersonList = persons

        viewModel.searchPerson("Rick").join()
        viewModel.searchPerson("").join()

        assertEquals(persons, viewModel.personList.value)
        assertEquals(false, viewModel.isSearching.value)
    }

    @Test
    fun `searchPerson should cache list on first search`() = runTest(testDispatcher) {
        val persons = createPersonList()
        viewModel.personList.value = persons

        viewModel.searchPerson("Rick").join()

        assertEquals(persons, viewModel.cachedPersonList)
    }
}
