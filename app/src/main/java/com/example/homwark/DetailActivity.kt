package com.example.homwark

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homwark.databinding.DetailpageBinding
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: DetailpageBinding
    private lateinit var selectedItem: MyItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailpageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedItemPosition = intent.getIntExtra("selectedItemPosition", -1)
        val dataList = intent.getSerializableExtra("dataList") as Array<MyItem>

        if (selectedItemPosition != -1 && dataList.isNotEmpty()) {
            selectedItem = dataList[selectedItemPosition]

            binding.ivIconItem.setImageResource(selectedItem.Image)
            binding.tvName.text = selectedItem.SellerName
            binding.tvPlace.text = selectedItem.Address
            binding.tvItemTitle.text = selectedItem.ItemTitle
            binding.tvItemDetail.text = selectedItem.ItemDetail
            binding.detailPrice.text = DecimalFormat("#,###").format(selectedItem.Price) + "원"
        }
    }

    private fun exit() {
        val intent = Intent(this, MainActivity::class.java)
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun onBackPressed() {
        exit()
    }
}