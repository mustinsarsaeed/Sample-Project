package com.example.sampleprojects

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.sampleprojects.databinding.ActivitySpinnerBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.hi.dhl.binding.viewbind
import java.util.*


class SpinnerActivity : AppCompatActivity(R.layout.activity_spinner) {
    private val mBinding: ActivitySpinnerBinding by viewbind()
    private val selectedCity = ArrayList<String>()
    private var firstRun: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setSupportActionBar(mBinding.toolbar)

        val cityList = resources.getStringArray(R.array.cityList)
        val adapter = ArrayAdapter(
            this@SpinnerActivity,
            R.layout.item_drop_down,
            R.id.text_drop_down,
            cityList
        )
        mBinding.spinner.setAdapter(adapter)

        mBinding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                val selectedPlanet = parent?.getItemAtPosition(position) as String
                if (firstRun) {
                    if (selectedCity.contains(selectedPlanet)) {


                        Toast.makeText(
                            this@SpinnerActivity,
                            "You have already selected $selectedPlanet",
                            Toast.LENGTH_LONG
                        ).show()


                    } else {
                        addPlanetChip(selectedPlanet)
                    }
                } else {
                    firstRun = true
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }

    private fun addPlanetChip(cityName: String) {
        selectedCity.add(cityName)
        mBinding.skillsTagChips.addView(getChip(cityName))

    }

    private fun getChip(name: String): Chip {
        return Chip(this@SpinnerActivity).apply {
            text = name
            isCloseIconVisible = true
            closeIconTint = checkedIconTint
            closeIcon = ContextCompat.getDrawable(this@SpinnerActivity, R.drawable.ic_chip_cross)
            setOnCloseIconClickListener {
                (it.parent as ChipGroup).removeView(it)
                Log.i("tag", "" + name)
                selectedCity.remove(name)

            }
        }
    }

}