package com.example.sampleprojects

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.sampleprojects.databinding.ActivitySpinnerBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.util.ArrayList

class SpinnerActivity : AppCompatActivity(R.layout.activity_spinner) {
    private var mBinding: ActivitySpinnerBinding by viewbind()
    private val selectedCity = ArrayList<String>()
    private var firstRun: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fun onCreate(savedInstanceState: Bundle?) {
/*
           mBinding = ActivitySpinnerBinding.inflate(layoutInflater)*/
            /*val view = mBinding.root*/

            val planetCity = resources.getStringArray(R.array.cityList)
            val adapter = ArrayAdapter(
                this@SpinnerActivity,
                R.layout.item_drop_down,
                R.id.text_drop_down,
                selectedCity
            )
            mBinding.spinner.setAdapter(adapter)


            mBinding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long) {


                    val selectedCitys = parent?.getItemAtPosition(position) as String
                    if (firstRun) {
                        if (selectedCity.contains(selectedCitys)) {

                            Toast.makeText(
                                this@SpinnerActivity,
                                "You have already selected $selectedCity",
                                Toast.LENGTH_LONG
                            ).show()


                        } else {
                            addPlanetChip(selectedCitys)
                        }
                    } else {
                        firstRun = true
                    }

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

                fun addPlanetChip(planetName: String) {
                    selectedCity.add(planetName)
                    mBinding.skillsTagChips.addView(getChip(planetName))

                }

                fun getChip(name: String): Chip {
                    return Chip(this@SpinnerActivity).apply {
                        text = name
                        isCloseIconVisible = true
                        closeIconTint = checkedIconTint
                        closeIcon = ContextCompat.getDrawable(
                            this@SpinnerActivity,
                            R.drawable.ic_chip_cross
                        )
                        setOnCloseIconClickListener {
                            (it.parent as ChipGroup).removeView(it)
                            Log.i("tag", "" + name)
                            selectedCity.remove(name)

                        }
                    }
                }
            }
        }
    }

}





