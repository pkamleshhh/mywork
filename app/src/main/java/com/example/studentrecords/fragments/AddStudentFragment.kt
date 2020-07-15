package com.example.studentrecords.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Switch
import android.widget.Toast

import com.example.studentrecords.R
import com.example.studentrecords.activities.HomeActivity
import com.example.studentrecords.database.dbHelper
import com.example.studentrecords.models.Student
import kotlinx.android.synthetic.main.fragment_add_student.*

/**
 * A simple [Fragment] subclass.
 */
class AddStudentFragment : Fragment(), View.OnClickListener {
    private var viewParent: View? = null
    private var btnAdd: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewParent = inflater.inflate(R.layout.fragment_add_student, container, false)
        init()
        return viewParent
    }

    private fun init() {
        btnAdd = viewParent!!.findViewById(R.id.btnAddStudent)
        btnAdd!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val itemId = v!!.id
        when (itemId) {
            R.id.btnAddStudent -> if (validate()) {
                val studentName: String = etStudentName.text.toString()
                studentName.substring(0, 1).toUpperCase() + studentName.substring(1).toLowerCase()
                val student = Student(
                    studentName
                    ,
                    etStudentClass.text.toString().toInt(),
                    etStudentRollNo.text.toString().toInt()
                )
                (activity as HomeActivity).getData(student)
                clearEditTexts()
                viewParent!!.hideKeyboard()
            }
        }
    }

    private fun validate(): Boolean {
        var studentName: String = etStudentName.text.toString()
        val studentClass: String = etStudentClass.text.toString()
        val studentRollNo: String = etStudentRollNo.text.toString()
        studentName =
        if (studentName.isNullOrEmpty()) {
            etStudentName.error = getString(R.string.empty_field)
            return false
        } else if (!studentName.matches(Regex("[a-zA-Z]+\\.?"))) {
            etStudentName.error = getString(R.string.not_a_valid_name)
            return false
        } else if (studentClass.isNullOrEmpty()) {
            etStudentClass.error = getString(R.string.empty_field)
            return false
        } else if (studentClass.toInt() < 1 || studentClass.toInt() > 12) {
            etStudentClass.error = getString(R.string.class_1_12)
            return false
        } else if (studentRollNo.isNullOrEmpty()) {
            etStudentClass.error = getString(R.string.empty_field)
            return false
        } else {
            return true
        }
    }
    private fun clearEditTexts(){
        etStudentName.text=null
        etStudentClass.text=null
        etStudentRollNo.text=null
    }
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
    fun setEditTextData(student: Student){

    }
}
