package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        binding.addName.setOnClickListener {
            val db = DBHelper(this, null)
            val name = binding.enterName.text.toString()
            val qty = binding.enterQty.text.toString()
            val price = binding.itemPrice.text.toString()

         db.addName(name,qty,price)
            Toast.makeText(this, name + " added to database", Toast.LENGTH_LONG).show()
            binding.enterName.text.clear()
            binding.enterQty.text.clear()
            binding.itemPrice.text.clear()
        }


        binding.printName.setOnClickListener {
            val db = DBHelper(this, null)
            val cursor = db.getName()
            cursor!!.moveToFirst()
            binding. Name.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
            binding.Age.append(cursor.getString(cursor.getColumnIndex(DBHelper.ITEM_QTY)) + "\n")


            while(cursor.moveToNext()){
                binding. Name.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
                binding.Age.append(cursor.getString(cursor.getColumnIndex(DBHelper.ITEM_QTY)) + "\n")
            }
            cursor.close()

        }

    }


}