package com.example.studentrecords.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm:FragmentManager,private val mFragmentList: ArrayList<Fragment>
                       ,private val mFragmentTitleList: ArrayList<String>):FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }

//    override fun getPageWidth(position: Int): Float {
//        return 0.85f
//    }
}