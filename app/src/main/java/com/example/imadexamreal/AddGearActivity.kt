package com.example.imadexamreal

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddGearActivity : AppCompatActivity() {

    private lateinit var etItemName: EditText
    private lateinit var spinnerCategory: Spinner
    private lateinit var etQuantity: EditText
    private lateinit var etComments: EditText
    private lateinit var btnSaveGear: Button
    private lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_gear)

        // Initialize views
        etItemName = findViewById(R.id.etItemName)
        spinnerCategory = findViewById(R.id.spinnerCategory)
        etQuantity = findViewById(R.id.etQuantity)
        etComments = findViewById(R.id.etComments)
        btnSaveGear = findViewById(R.id.btnSaveGear)
        btnCancel = findViewById(R.id.btnCancel)

        // Setup category spinner
        val categories = arrayOf("Shelter", "Cooking", "Food", "Safety", "First Aid", "Tools", "Clothing")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)
        spinnerCategory.adapter = adapter

        btnSaveGear.setOnClickListener {
            addNewGear()
        }

        btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun addNewGear() {
        // Error handling for user input
        val itemName = etItemName.text.toString().trim()
        val category = spinnerCategory.selectedItem.toString()
        val quantityStr = etQuantity.text.toString().trim()
        val comments = etComments.text.toString().trim()

        // Validate inputs
        when {
            itemName.isEmpty() -> {
                Toast.makeText(this, "Please enter an item name", Toast.LENGTH_SHORT).show()
                return
            }
            quantityStr.isEmpty() -> {
                Toast.makeText(this, "Please enter a quantity", Toast.LENGTH_SHORT).show()
                return
            }
            quantityStr.toIntOrNull() == null -> {
                Toast.makeText(this, "Please enter a valid number for quantity", Toast.LENGTH_SHORT).show()
                return
            }
            quantityStr.toInt() <= 0 -> {
                Toast.makeText(this, "Quantity must be greater than 0", Toast.LENGTH_SHORT).show()
                return
            }
        }

        val quantity = quantityStr.toInt()

        // Add to parallel arrays
        MainActivity.itemNames.add(itemName)
        MainActivity.categories.add(category)
        MainActivity.quantities.add(quantity)
        MainActivity.comments.add(if (comments.isEmpty()) "No additional notes" else comments)

        Toast.makeText(this, "$itemName added successfully!", Toast.LENGTH_SHORT).show()

        // Clear form
        etItemName.text.clear()
        etQuantity.text.clear()
        etComments.text.clear()
        spinnerCategory.setSelection(0)
    }
}
