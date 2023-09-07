package com.example.dementiaapp

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.dementiaapp.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.firestore.FirebaseFirestore
import java.io.ByteArrayOutputStream

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var dialog: Dialog
    private lateinit var uid:String
    private lateinit var storageReference:StorageReference
    private lateinit var firestore: FirebaseFirestore
    private lateinit var storage: FirebaseStorage
    private lateinit var imageUri: Uri
    private val GALLERY_REQUEST_CODE = 1
    private val CAMERA_REQUEST_CODE = 2
    private val EDIT_IMAGE_REQUEST_CODE = 3
    private lateinit var textViewUserName: TextView
    private lateinit var textViewEmail: TextView
    private lateinit var binding:ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()

        binding.button.setOnClickListener{
            startActivity(Intent(this, MyInfoActivity::class.java))
        }
        binding.button2.setOnClickListener{
            startActivity(Intent(this, MedicalHistroryActivity::class.java))
        }
        binding.button3.setOnClickListener{
            startActivity(Intent(this, LifestyleActivity::class.java))
        }
        binding.logout.setOnClickListener{
            signOut()
        }


        firestore = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        // Initialize Firebase Auth, Firestore and Storage
        storageReference = storage.reference


        // Display profile image from Firebase Storage to hdodenhof circle image view
        val profileImageReference = storageReference.child("${auth.currentUser?.uid}ProfileImage/")
        profileImageReference.downloadUrl.addOnSuccessListener { uri ->
            Glide.with(this@ProfileActivity)
                .load(uri)
                .placeholder(R.drawable.person_pin)
                .error(R.drawable.person_pin)
                .into(binding.imageView2)
        }

        binding.imageView2.setOnClickListener{
            showProgressBar()
            lessgo()
        }

        textViewUserName = findViewById(R.id.textView)

// Check if the user is authenticated
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // User is signed in
            val displayName = currentUser.displayName
            if (displayName != null) {
                // Display the user's name in the TextView
                textViewUserName.text = "$displayName"
            }
        } else {
            // User is not signed in, handle this case as needed
        }

        textViewEmail = findViewById(R.id.textView2)
        // Get the user's email and display it in the textViewEmail
        val email = currentUser?.email
        if (email != null) {
            textViewEmail.text = "$email"
        }
         else {
            // User is not signed in, handle this case as needed
        }

    }


    private fun lessgo() {
        val uid = auth.currentUser?.uid

        if(uid!=null){
                uploadProfilePic()
            }
            else{

                hideProgressBar()
                Toast.makeText(this@ProfileActivity, "Failed to update profile", Toast.LENGTH_SHORT).show()
            }
        }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                GALLERY_REQUEST_CODE -> {
                    imageUri = data?.data!!
                    launchImageEditor(imageUri)
                }
                CAMERA_REQUEST_CODE -> {
                    val bitmap = data?.extras?.get("data") as Bitmap
                    imageUri = getImageUri(this, bitmap)
                    launchImageEditor(imageUri)
                }
                EDIT_IMAGE_REQUEST_CODE -> {
                    imageUri = data?.data!!
                    uploadImageToFirebase()
                }
            }
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun uploadProfilePic() {

        val options = arrayOf("Choose from Gallery", "Take a Picture")
        val builder = AlertDialog.Builder(this@ProfileActivity)
        builder.setTitle("Upload Profile Picture")
        builder.setItems(options) { _, item ->
            when (item) {
                0 -> {
                    val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
                }
                1 -> {
                    val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    if (takePictureIntent.resolveActivity(packageManager) != null) {
                        startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE)
                    }
                }
            }
        }
        builder.show()
        hideProgressBar()
    }



    private fun launchImageEditor(imageUri: Uri) {
        val editIntent = Intent(Intent.ACTION_EDIT)
        editIntent.setDataAndType(imageUri, "image/*")
        editIntent.putExtra("return-data", true)
        startActivityForResult(Intent.createChooser(editIntent, "Edit Image"), EDIT_IMAGE_REQUEST_CODE)
        hideProgressBar()
    }


    private fun uploadImageToFirebase() {
        storageReference = FirebaseStorage.getInstance().getReference("ProfileImage/" + auth.currentUser?.uid)
        storageReference.putFile(imageUri).addOnSuccessListener {
            hideProgressBar()
            Toast.makeText(this@ProfileActivity, "Profile successfully updated", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ProfileActivity::class.java))
        }.addOnFailureListener {
            hideProgressBar()
            Toast.makeText(this@ProfileActivity, "Failed to upload the image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getImageUri(inContext: Context, inImage: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }

    private fun signOut() {
        auth.signOut()
        val intent = Intent(this, SignInActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

    private fun showProgressBar(){

        dialog = Dialog(this@ProfileActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_wait)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()

    }

    private fun hideProgressBar(){

        dialog.dismiss()

    }
}