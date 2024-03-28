import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.advweek6160420103.model.Book
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class BooksViewModel : ViewModel() {
    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> = _books

    fun fetchBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val url = URL("http://192.168.117.76/books/books.json")
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connectTimeout = 10000
                connection.readTimeout = 10000

                if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                    val inputStream = connection.inputStream
                    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
                    val jsonString = bufferedReader.use { it.readText() }
                    val booksList = Gson().fromJson(jsonString, Array<Book>::class.java).toList()
                    _books.postValue(booksList)
                } else {

                    // Log.d("BooksViewModel", "Response code: ${connection.responseCode}")
                }

                connection.disconnect()
            } catch (e: Exception) {
                // Log.e("BooksViewModel", "Exception: ${e.message}", e)
            }
        }
    }
}
