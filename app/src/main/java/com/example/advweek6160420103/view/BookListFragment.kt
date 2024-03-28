import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek6160420103.R


class BookListFragment : Fragment() {

    private lateinit var booksViewModel: BooksViewModel
    private lateinit var booksAdapter: BookListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_book_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        booksAdapter = BookListAdapter()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = booksAdapter
        }

        booksViewModel = ViewModelProvider(this).get(BooksViewModel::class.java)
        booksViewModel.books.observe(viewLifecycleOwner, Observer {
            booksAdapter.submitList(it)
        })

        return view
    }
}
