package np.info.jagdish.noteit

import android.annotation.SuppressLint
import android.os.Bundle
//import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ReadData : AppCompatActivity() {

    private val db: FirebaseFirestore by lazy {
        Firebase.firestore
    }
    private val TITLE = "Title"
    private val DESCRIPTION = "Desc"


    private lateinit var title1: TextView
    private lateinit var desc1: TextView
    // @supress private lateinit var submitBtn: Button


    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_data)
        title1 = findViewById(R.id.txtTitle2)
        desc1 = findViewById(R.id.tvDesc)

        this.title1.text = "Hello"
        update()
        readData()
    }


    private fun readData() {
        db.collection("NoteIT App").document("hi").get().addOnSuccessListener {
            val title: String = it.get(TITLE) as String
            val desc = it.get(DESCRIPTION) as String
            title1.text = title
            desc1.text = desc

        }
    }

    private fun update() {
        db.collection("NoteIT App").document("hi")
            .update(mutableMapOf<String, Any>(TITLE to "Hi Got update")).addOnSuccessListener {
                showToast(this, "Data Updated")
            }
    }

    private fun showToast(context: android.content.Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}