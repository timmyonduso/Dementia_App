package com.example.dementiaapp

import android.os.Bundle
import android.view.View
import android.widget.CalendarView.OnDateChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.example.dementiaapp.databinding.ActivityCalenderBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CalenderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalenderBinding
    private var stringDateSelected: String? = null
    private var databaseReference: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalenderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calendarView.setOnDateChangeListener(OnDateChangeListener { calendarView, i, i1, i2 ->
            stringDateSelected =
                i.toString() + (i1 + 1).toString() + i2.toString()
            calendarClicked()
        })
        databaseReference = FirebaseDatabase.getInstance().getReference("Calendar")
    }

    private fun calendarClicked() {
        databaseReference!!.child(stringDateSelected!!)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.value != null) {
                        binding.editText.setText(snapshot.value.toString())
                    } else {
                        binding.editText.setText("null")
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }

    fun buttonSaveEvent(view: View?) {
        databaseReference!!.child(stringDateSelected!!).setValue(binding.editText.text.toString())
    }
}