package org.d3if3090.todo_list

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import org.d3if3090.todo_list.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        goneBangunanDatar()
        goneHasil()

    val spinner: Spinner = binding.bangunDatarSpinner
    // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        spinner.selected {}
    }

    fun Spinner.selected(action: (position:Int) -> Unit) {
        this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                action(position)
                val bangunan_datar = parent?.getItemAtPosition(position).toString()

                if(bangunan_datar == "Persegi"){
                    goneBangunanDatar()
                    tampilPersegi()
                    binding.btnHasil.setOnClickListener{hitungPersegi()}
                }else if (bangunan_datar == "Persegi Panjang"){
                    goneBangunanDatar()
                    tampilPersegiPanjang()
                    binding.btnHasil.setOnClickListener{hitungPersegiPanjang()}
                }else if(bangunan_datar == "Segitiga"){
                    goneBangunanDatar()
                    tampilSegitiga()
                    binding.btnHasil.setOnClickListener{hitungSegitiga()}
                }else if(bangunan_datar == "Lingkaran"){
                    goneBangunanDatar()
                    tampilLingkaran()
                    binding.btnHasil.setOnClickListener{hitungLingkaran()}
                }

            }
        }
    }

    private fun goneHasil(){
        binding.bangunanDatarInfoHasil.setVisibility(View.GONE)
        binding.luas.setVisibility(View.GONE)
        binding.keliling.setVisibility(View.GONE)
    }

    private fun hitungPersegi() {
        try{
            val sisi =binding.sisiInp.text.toString()
            if (TextUtils.isEmpty(sisi)) {
                Toast.makeText(this, R.string.sisi_invalid, Toast.LENGTH_LONG).show()
                return
            }

            val luas = sisi.toFloat() * sisi.toFloat()
            val keliling = sisi.toFloat() * 4
            tampilanHasil("Persegi", luas, keliling)
        }catch (ex: NumberFormatException){
            Toast.makeText(this, R.string.sisi_invalid, Toast.LENGTH_LONG).show()
            return
        }
    }

    private fun hitungPersegiPanjang() {
        try{
            val panjang =binding.panjangInp.text.toString()
            if (TextUtils.isEmpty(panjang)) {
                Toast.makeText(this, R.string.panjang_invalid, Toast.LENGTH_LONG).show()
                return
            }
            val lebar =binding.lebarInp.text.toString()
            if (TextUtils.isEmpty(lebar)) {
                Toast.makeText(this, R.string.lebar_invalid, Toast.LENGTH_LONG).show()
                return
            }

            val luas = panjang.toFloat() * lebar.toFloat()
            val keliling = ((panjang.toFloat() * 2) + (lebar.toFloat() * 2))
            tampilanHasil("Persegi Panjang", luas, keliling)
        }catch (ex: NumberFormatException){
            Toast.makeText(this, R.string.input_invalid, Toast.LENGTH_LONG).show()
            return
        }
    }

    private fun hitungSegitiga() {
        try{
            val alas =binding.alasInp.text.toString()
            if (TextUtils.isEmpty(alas)) {
                Toast.makeText(this, R.string.alas_invalid, Toast.LENGTH_LONG).show()
                return
            }
            val tinggi =binding.tinggiInp.text.toString()
            if (TextUtils.isEmpty(tinggi)) {
                Toast.makeText(this, R.string.tinggi_invalid, Toast.LENGTH_LONG).show()
                return
            }

            val sisi_a =binding.sisiAInp.text.toString()
            if (TextUtils.isEmpty(sisi_a)) {
                Toast.makeText(this, R.string.sisi_a_invalid, Toast.LENGTH_LONG).show()
                return
            }

            val sisi_b =binding.sisiBInp.text.toString()
            if (TextUtils.isEmpty(sisi_b)) {
                Toast.makeText(this, R.string.sisi_b_invalid, Toast.LENGTH_LONG).show()
                return
            }

            val sisi_c =binding.sisiCInp.text.toString()
            if (TextUtils.isEmpty(sisi_c)) {
                Toast.makeText(this, R.string.sisi_c_invalid, Toast.LENGTH_LONG).show()
                return
            }

            val luas = (0.5f * alas.toFloat() * tinggi.toFloat())
            val keliling = sisi_a.toFloat() + sisi_b.toFloat() + sisi_c.toFloat()
            tampilanHasil("Segitiga", luas, keliling)
        }catch (ex: NumberFormatException){
            Toast.makeText(this, R.string.input_invalid, Toast.LENGTH_LONG).show()
            return
        }
    }

    private fun hitungLingkaran() {
        try{
            val jari_jari =binding.jariInp.text.toString()
            if (TextUtils.isEmpty(jari_jari)) {
                Toast.makeText(this, R.string.jari_jari_invalid, Toast.LENGTH_LONG).show()
                return
            }

            val luas = jari_jari.toFloat() * jari_jari.toFloat() * 3.14f
            val keliling = 2 * 3.14f * jari_jari.toFloat()
            tampilanHasil("Persegi Panjang", luas, keliling)
        }catch (ex: NumberFormatException){
            Toast.makeText(this, R.string.input_invalid, Toast.LENGTH_LONG).show()
            return
        }
    }


    private fun tampilanHasil(namaBangunanDatar: String, luas: Float, keliling: Float){
        binding.bangunanDatarInfoHasil.setVisibility(View.VISIBLE)
        binding.luas.setVisibility(View.VISIBLE)
        binding.keliling.setVisibility(View.VISIBLE)

        binding.bangunanDatarInfoHasil.text = getString(R.string.bangunan_datar_hasil, namaBangunanDatar)
        binding.luas.text = getString(R.string.luas, luas)
        binding.keliling.text = getString(R.string.keliling, keliling)
    }

    private fun goneBangunanDatar(){
        binding.sisiHint.setVisibility(View.GONE)

        binding.panjangHint.setVisibility(View.GONE)
        binding.lebarHint.setVisibility(View.GONE)

        binding.alasHint.setVisibility(View.GONE)
        binding.tinggiHint.setVisibility(View.GONE)
        binding.sisiAHint.setVisibility(View.GONE)
        binding.sisiBHint.setVisibility(View.GONE)
        binding.sisiCHint.setVisibility(View.GONE)

        binding.jariHint.setVisibility(View.GONE)
    }

    private fun tampilPersegi(){
        binding.sisiHint.setVisibility(View.VISIBLE)
        binding.sisiInp.setText("")

        setBtnHasilConstraint(R.id.sisi_hint)
    }

    private fun tampilPersegiPanjang(){
        binding.panjangHint.setVisibility(View.VISIBLE)
        binding.lebarHint.setVisibility(View.VISIBLE)
        binding.panjangInp.setText("")
        binding.lebarInp.setText("")

        setBtnHasilConstraint(R.id.lebar_hint)
    }

    private fun tampilSegitiga(){
        binding.alasHint.setVisibility(View.VISIBLE)
        binding.tinggiHint.setVisibility(View.VISIBLE)
        binding.sisiAHint.setVisibility(View.VISIBLE)
        binding.sisiBHint.setVisibility(View.VISIBLE)
        binding.sisiCHint.setVisibility(View.VISIBLE)
        binding.alasInp.setText("")
        binding.tinggiInp.setText("")
        binding.sisiAInp.setText("")
        binding.sisiBInp.setText("")
        binding.sisiCInp.setText("")

        setBtnHasilConstraint(R.id.sisi_c_hint)
    }

    private fun tampilLingkaran(){
//        setLableBangunanDatarConstraint(R.id.txt_lingkaran)

        binding.jariHint.setVisibility(View.VISIBLE)
        binding.jariInp.setText("")

        setBtnHasilConstraint(R.id.jari_hint)
    }

    private fun setBtnHasilConstraint(id : Int){
        val constraintSet = ConstraintSet()
        val constraintLayout = binding.parentLayout
        constraintSet.clone(constraintLayout)
        constraintSet.connect(R.id.btn_hasil, ConstraintSet.TOP, id, ConstraintSet.BOTTOM, 16)
        constraintSet.applyTo(constraintLayout)
    }

//    private fun setLableBangunanDatarConstraint(id : Int){
//        val constraintSet = ConstraintSet()
//        val constraintLayout = binding.parentLayout
//        constraintSet.clone(constraintLayout)
//        constraintSet.connect(id, ConstraintSet.TOP, R.id.bangun_datar_spinner, ConstraintSet.BOTTOM, 16)
//        constraintSet.applyTo(constraintLayout)
//    }
}