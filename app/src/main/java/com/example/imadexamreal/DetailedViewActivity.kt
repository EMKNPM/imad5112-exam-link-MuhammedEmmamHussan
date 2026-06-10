package com.example.imadexamreal

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class DetailedViewActivity : AppCompatActivity() {

    private lateinit var containerDetails: LinearLayout
    private lateinit var btnBackToBase: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_view)

        containerDetails = findViewById(R.id.containerDetails)
        btnBackToBase = findViewById(R.id.btnBackToBase)

        displayGearList()

        btnBackToBase.setOnClickListener {
            finish() // Return to Main Screen
        }
    }

    private fun displayGearList() {
        containerDetails.removeAllViews()

        // Add header
        val headerView = TextView(this).apply {
            text = "=== CAMPING GEAR INVENTORY ===\n"
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, android.R.color.white))
            setPadding(16, 32, 16, 16)
        }
        containerDetails.addView(headerView)

        // Using a loop to display all items with details
        for (i in MainActivity.itemNames.indices) {
            val itemCard = createItemCard(
                itemName = MainActivity.itemNames[i],
                category = MainActivity.categories[i],
                quantity = MainActivity.quantities[i],
                comment = MainActivity.comments[i],
                index = i + 1
            )
            containerDetails.addView(itemCard)

            // Add divider
            val divider = View(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    2
                )
                setBackgroundColor(ContextCompat.getColor(context, R.color.divider_gray))
            }
            containerDetails.addView(divider)
        }

        // Add packing tips footer
        val tipsView = TextView(this).apply {
            text = "\n📋 PACKING TIPS:\n• Always pack heavier items at the bottom\n• Keep safety items easily accessible\n• Check weather forecast before packing\n• Bring extra batteries and light sources"
            textSize = 12f
            setTextColor(ContextCompat.getColor(context, R.color.tip_green))
            setPadding(16, 32, 16, 16)
        }
        containerDetails.addView(tipsView)
    }

    private fun createItemCard(itemName: String, category: String, quantity: Int, comment: String, index: Int): LinearLayout {
        val card = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        val itemInfo = TextView(this).apply {
            text = """
                |Item #$index: ${itemName.uppercase()}
                |━━━━━━━━━━━━━━━━━━━━
                |📂 Category: $category
                |🔢 Quantity: $quantity
                |💬 Comments: $comment
            """.trimMargin()
            textSize = 14f
            setTextColor(ContextCompat.getColor(context, android.R.color.white))
            setPadding(8, 8, 8, 8)
        }

        card.addView(itemInfo)
        return card
    }
}
