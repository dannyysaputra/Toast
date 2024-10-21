package lat.pam.hellotoast

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val buttonBrowsePage = findViewById<Button>(R.id.button_browser)
        val buttonCamera = findViewById<Button>(R.id.button_camera)
        val buttonMaps = findViewById<Button>(R.id.button_maps)

        buttonBrowsePage.setOnClickListener(View.OnClickListener {
            val intentBrowser = Intent(Intent.ACTION_VIEW)
            intentBrowser.setData(Uri.parse("https://www.google.com"))
            startActivity(intentBrowser)
        })

        buttonCamera.setOnClickListener(View.OnClickListener {
            val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intentCamera)
        })

        buttonMaps.setOnClickListener(View.OnClickListener {
            val gmmIntentUri = Uri.parse("geo:-6.913576006266719, 107.68540596388611")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            mapIntent.resolveActivity(packageManager)?.let {
                startActivity(mapIntent)
            }
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}