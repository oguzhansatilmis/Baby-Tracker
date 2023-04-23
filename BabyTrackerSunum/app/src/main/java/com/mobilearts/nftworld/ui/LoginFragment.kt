package com.mobilearts.nftworld.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.mobilearts.nftworld.R
import com.mobilearts.nftworld.databinding.FragmentLoginBinding
import com.mobilearts.nftworld.model.BabyDataClass
import com.mobilearts.nftworld.viewmodel.Viewmodel
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class LoginFragment : Fragment(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    private lateinit var binding: FragmentLoginBinding
    var selectedBitmap: Bitmap? = null
    private lateinit var gender: String
    var selectedPostImage: Uri? = null
    private val calendar = Calendar.getInstance()
    private lateinit var viewmodel: Viewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this)[Viewmodel::class.java]
        gender = ""
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel.viewModelScope.launch {
            viewmodel.getData().forEach {
                val babyFullName = it.babyFullName // burada babyFullName değişkeni bir String tipinde
                binding.editTextTextPersonName2.setText(babyFullName) // setText() metodu kullanılarak babyFullName değeri EditText alanına yazdırılır
                val baybBirtDate = it.birthDate
                binding.loginBirtyDate.setText(baybBirtDate)
                val babyDueDate = it.dueDate
                binding.loginDueDate.setText(babyDueDate)
                val babyTimeBirt= it.timeofBirth
                binding.loginTimeBirth.setText(babyTimeBirt)

                var deger = it.babyProfileImage
                val bitmap = deger?.let {
                        it1 -> BitmapFactory.decodeByteArray(deger, 0, it1.size) }

              binding.selectProfilImage.setImageBitmap(bitmap)

                if(it.babyGender == "boy")
                {
                    binding.loginImageViewBoy.setImageResource(R.drawable.img_selected_boy)
                }
                if(it.babyGender == "girl") {
                    binding.loginImageViewBoy.setImageResource(R.drawable.img_selected_girl)
                }
            }
        }
        binding.loginBirtyDateView.setOnClickListener {
            val datePickerDialog =
                DatePickerDialog(requireActivity(), this@LoginFragment, 2020, 0, 1)
            datePickerDialog.show()
            control()

        }
        binding.loginTimeBirthView.setOnClickListener {
            showTimePickerDialog(binding.loginTimeBirth)
            control()
        }
        binding.loginDueDate.setOnClickListener {

            showTimePickerDialog(binding.loginDueDate)
            control()
        }
        val selectProfilImage: CircleImageView = binding.selectProfilImage
        selectProfilImage.setOnClickListener {
            selectedImage()
            control()
        }
        binding.loginImageViewBoy.setOnClickListener {
            selectBoy()
            control()
        }
        binding.loginImageViewGirl.setOnClickListener {
            selectGirl()
            control()
        }
        binding.editTextTextPersonName2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var deger = s.toString()

                if (deger == "") {
                    binding.view55.setBackgroundResource(R.drawable.rectangle_2)
                    control()
                }

            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
        //viewLifecycleOwner.lifecycleScope.launch {}
        binding.view55.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {

                if (binding.editTextTextPersonName2.text.toString().isNotEmpty() &&
                    binding.loginBirtyDate.text.toString().isNotEmpty() &&
                    binding.loginDueDate.text.toString().isNotEmpty() &&
                    binding.loginTimeBirth.text.toString().isNotEmpty() &&
                    gender.isNotEmpty()
                ) {
                    if(selectedBitmap != null){
                        saveData()
                        activity?.runOnUiThread{
                            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                        }
                    }
                    else{
                        activity?.runOnUiThread {
                            Toast.makeText(requireContext(), "Please select a picture!!", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                } else {
                    activity?.runOnUiThread {
                        Toast.makeText(requireContext(), "Please fill in information!!", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }
    fun selectedImage() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // İZİN VERİLMEMİŞ,İZİN İSTİYORUZ
            ActivityCompat.requestPermissions(
                requireContext() as Activity,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )
        } else {
            //İZİN VERİLMİŞ GALERİYE GİDİYORUZ
            val galeriIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galeriIntent, 2)
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //izin verilmiş
                val galeriIntent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeriIntent, 2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 2 && resultCode == AppCompatActivity.RESULT_OK && data != null) {
            selectedPostImage = data.data
            binding.selectProfilImage.visibility = View.VISIBLE
            if (selectedPostImage != null) {

                if (Build.VERSION.SDK_INT >= 28) {

                    val source = ImageDecoder.createSource(
                        requireContext().contentResolver,
                        selectedPostImage!!

                    )
                    selectedBitmap = ImageDecoder.decodeBitmap(source)
                    binding.selectProfilImage.setImageBitmap(selectedBitmap)
                } else {
                    selectedBitmap = MediaStore.Images.Media.getBitmap(
                        requireContext().contentResolver,
                        selectedPostImage
                    )
                    binding.selectProfilImage.setImageBitmap(selectedBitmap)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        // Seçilen tarihi LocalDate nesnesine dönüştür
        val selectedDate = LocalDate.of(year, month + 1, dayOfMonth)

        // Tarihi TextView'a yazdır
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        binding.loginBirtyDate.text = formatter.format(selectedDate)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val selectedTime = LocalTime.of(hourOfDay, minute)
        // Saati TextView'a yazdır
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        binding.loginTimeBirth.text = formatter.format(selectedTime)
    }

    private fun showTimePickerDialog(textview: TextView) {
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.time)
            textview.text = time.toString()
        }

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(requireContext(), timeSetListener, hour, minute, true).show()
    }

    fun selectBoy() {
        binding.loginImageViewBoy.setImageResource(R.drawable.img_selected_boy)
        binding.loginImageViewGirl.setImageResource(R.drawable.img_unselected_girl)
        gender = "boy"
        control()

    }

    fun selectGirl() {
        binding.loginImageViewBoy.setImageResource(R.drawable.img_unselected_boy)
        binding.loginImageViewGirl.setImageResource(R.drawable.img_selected_girl)
        gender = "girl"
        control()
    }

    @SuppressLint("SuspiciousIndentation")
    fun saveData() {
        val babyName = binding.editTextTextPersonName2.text.toString()
        val birtyDate = binding.loginBirtyDate.text.toString()
        val timeBirth = binding.loginTimeBirth.text.toString()
        val dueDate = binding.loginDueDate.text.toString()
            val stream = ByteArrayOutputStream()
                selectedBitmap?.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val imageByteArray = stream.toByteArray()


        val user = BabyDataClass(
            babyFullName = babyName,
            birthDate = birtyDate,
            timeofBirth = timeBirth,
            dueDate = dueDate,
            babyGender = gender,
            babyProfileImage = imageByteArray
        )
        viewmodel.insertBaby(user)
    }

    fun control() {
        if (binding.editTextTextPersonName2.text.toString().isNotEmpty() &&
            binding.loginBirtyDate.text.toString().isNotEmpty() &&
            binding.loginDueDate.text.toString().isNotEmpty() &&
            binding.loginTimeBirth.text.toString().isNotEmpty()&& gender.isNotEmpty()
        )
        {
            binding.view55.setBackgroundResource(R.drawable.rectangle2)
        }
    }

}


