package np.info.jagdish.noteit


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    //late initlilaizers
    lateinit var title: EditText
    lateinit var desc: EditText
    lateinit var submitBtn: Button

    private val db: FirebaseFirestore by lazy {
        Firebase.firestore
    }
    private val TITLE = "Title"
    private val DESCRIPTION = "Desc"
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        submitBtn = findViewById(R.id.btnSubmit)
        title = findViewById(R.id.edTitle)
        desc = findViewById(R.id.edDesc)


        submitBtn.setOnClickListener {
            var titleText = title.text.toString()
            var descText = desc.text.toString()

//    send(titleText,descText)
            val intent = Intent(this, ReadData::class.java)
            startActivity(intent)


//    when(title.toString().isEmpty() || desc.toString().isEmpty()){
//        title.toString().isEmpty()  -> {
//            title.requestFocus()
//        }
//        desc.toString().isEmpty() ->{
//            desc.requestFocus()
//        }
//
//        else -> {
//
//        }
//    }

        }
    }

    private fun send(gotTitle: String, gotDesc: String) {
        Toast.makeText(this, "$gotTitle and $gotDesc", Toast.LENGTH_LONG).show()
        val map = mutableMapOf<String, String>()

        map[TITLE] = gotTitle
        map[DESCRIPTION] = gotDesc
        db.collection("NoteIT App")
            .document()
            .set(map)
            .addOnSuccessListener {
                showToast(this, "Success")
            }
            .addOnFailureListener {
//                Toast.makeText(this,Toast.LENGTH_LONG).show()
                showToast(this, "Exception e = $it")
            }


    }

    private fun showToast(context: android.content.Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}