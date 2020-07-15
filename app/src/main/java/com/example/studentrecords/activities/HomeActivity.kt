package com.example.studentrecords.activities

import android.annotation.TargetApi
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTabHost
import androidx.viewpager.widget.ViewPager
import com.example.studentrecords.R
import com.example.studentrecords.adapters.ViewPagerAdapter
import com.example.studentrecords.fragments.AddStudentFragment
import com.example.studentrecords.fragments.StudentListFragment
import com.example.studentrecords.models.Student
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    private val mFragmentList: ArrayList<Fragment> = ArrayList()
    private val mFragmentTitleList: ArrayList<String> = ArrayList()
    private val studentListFragment = StudentListFragment()
    private val addStudentFragment = AddStudentFragment()
    private var viewPagerAdapter: ViewPagerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        viewpager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
//                setTabColor()
            }

            override fun onPageSelected(position: Int) {
            }

        })

    }

    private fun init() {
        mFragmentList.add(studentListFragment)
        mFragmentList.add(addStudentFragment)
        mFragmentTitleList.add("Student List")
        mFragmentTitleList.add("Add/Update Student")
        viewPagerAdapter =
            ViewPagerAdapter(supportFragmentManager, mFragmentList, mFragmentTitleList)
        viewpager!!.adapter = viewPagerAdapter
        tablayout!!.setupWithViewPager(viewpager)
    }

    fun getData(student: Student) {
        studentListFragment.addData(student)
//        val db=dbHelper(this)
//        db.insertData(student,this)
    }

    fun switchViewPager() {
        if (viewpager.currentItem == 0) {
            viewpager.currentItem = 1
        } else {
            viewpager.currentItem = 0
        }
    }
    fun viewData(student: Student){
        switchViewPager()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setTabColor() {
        if (viewpager.currentItem == 0) {
            tablayout.background = getDrawable(R.color.orange)
        } else {
            tablayout.background = getDrawable(R.color.darkerBlue)
        }
    }
}
