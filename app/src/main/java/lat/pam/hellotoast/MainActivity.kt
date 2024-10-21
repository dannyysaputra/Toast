package lat.pam.hellotoast

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class MainActivity : AppCompatActivity() {
    private var mCount: Int = 0;
    private val model: NameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mShowCount = findViewById<TextView>(R.id.show_count)
        val buttonCountUp = findViewById<Button>(R.id.button_count)
        val buttonToast = findViewById<Button>(R.id.button_toast)
        val buttonSwitchPage = findViewById<Button>(R.id.button_switch_page)

        // observer to update UI
        val nameObserver = Observer<Int> { newName ->
            // update textView
            mShowCount.text = newName.toString()
        }

        // observe the live data, passing in this activity as the lifecycleowner
        model.currentName.observe(this, nameObserver)

        mCount = model.currentName.value ?: 0

        buttonCountUp.setOnClickListener(View.OnClickListener {
            mCount++;
            if (mShowCount != null) {
                mShowCount.text = mCount.toString()
                model.currentName.value = mCount
            }
        })

        buttonToast.setOnClickListener(View.OnClickListener {
            val value: String = mShowCount?.text.toString()
            val toast: Toast = Toast.makeText(this, "Count: " + value, Toast.LENGTH_LONG)
            toast.show()
        })

        buttonSwitchPage.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

//    fun launchThirdActivity(view: View) {
//        Log.d(LOG_TAG, "Button clicked!");
//    }
}