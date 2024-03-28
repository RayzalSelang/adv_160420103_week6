import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek6160420103.R
import com.example.advweek6160420103.model.Book
import com.squareup.picasso.Picasso

class BookListAdapter : ListAdapter<Book, BookListAdapter.BookViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_book_list_adapter, parent, false)
        return BookViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtId: TextView = itemView.findViewById(R.id.txtID)
        private val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        private val txtAuthor: TextView = itemView.findViewById(R.id.txtAuthor)
        private val txtGenre: TextView = itemView.findViewById(R.id.txtGenre)
        private val txtCharacters: TextView = itemView.findViewById(R.id.txtCharacters)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(book: Book) {
            txtId.text = book.id.toString()
            txtTitle.text = book.title
            txtAuthor.text = book.author
            txtGenre.text = book.genre
            txtCharacters.text = book.characters.joinToString(", ")
            Picasso.get().load(book.imageUrl).into(imageView)
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }
    }
}
