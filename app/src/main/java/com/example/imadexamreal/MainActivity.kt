package com.example.imadexamreal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Parallel arrays to store camping gear data
    companion object {
        // Using parallel arrays for data storage
        val itemNames = mutableListOf(
            "Tent",
            "Marshmallows",
            "Flashlight",
            "Sleeping Bag",
            "First Aid Kit",
            "Camp Stove",
            "Water Filter"
        )

        val categories = mutableListOf(
            "Shelter",
            "Food",
            "Safety",
            "Shelter",
            "First Aid",
            "Cooking",
            "Cooking"
        )

        val quantities = mutableListOf(
            1,
            3,
            2,
            2,
            1,
            1,
            1
        )

        val comments = mutableListOf(
            "4-person waterproof",
            "For S'mores (Mega size)",
            "Check batteries (AA)",
            "Rated for 20°F",
            "Includes bandages and antiseptic",
            "Propane powered",
            "Removes 99.9% of bacteria"
        )
    }

    private lateinit var tvTotalItems: TextView
    private lateinit var btnAddGear: Button
    private lateinit var btnViewDetails: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        tvTotalItems = findViewById(R.id.tvTotalItems)
        btnAddGear = findViewById(R.id.btnAddGear)
        btnViewDetails = findViewById(R.id.btnViewDetails)

        // Calculate and display total items using a loop
        updateTotalItems()

        // Set click listeners
        btnAddGear.setOnClickListener {
            val intent = Intent(this, AddGearActivity::class.java)
            startActivity(intent)
        }

        btnViewDetails.setOnClickListener {
            val intent = Intent(this, DetailedViewActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Update total items when returning from other activities
        updateTotalItems()
    }

    private fun updateTotalItems() {
        // Using a loop to calculate total number of items
        var totalItems = 0
        for (quantity in quantities) {
            totalItems += quantity
        }
        tvTotalItems.text = "Total Items Packed: $totalItems"
    }
}
