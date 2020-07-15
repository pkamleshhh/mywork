package com.example.studentrecords.fragments


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentrecords.R
import com.example.studentrecords.activities.HomeActivity
import com.example.studentrecords.adapters.AdapterRv
import com.example.studentrecords.models.Student
import com.google.android.material.bottomsheet.BottomSheetDialog
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter

/**
 * A simple [Fragment] subclass.
 */
class StudentListFragment : Fragment(), AdapterRv.ItemClicked {


    private val studentArrayList = ArrayList<Student>()
    private var mContext: Context? = null
    private var adapterRv: AdapterRv? = null
    private var recyclerView: RecyclerView? = null
    private var tvListIsEmpty: TextView? = null
    private var layoutParent: FrameLayout? = null
    private var v: View? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_student_list, container, false)
        init()
        checkRv()
        return v
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    private fun init() {
        recyclerView = v!!.findViewById(R.id.rvStudentList)
        tvListIsEmpty = v!!.findViewById(R.id.tvListIsEmpty)
        recyclerView!!.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        adapterRv = AdapterRv(studentArrayList, this)
        recyclerView!!.adapter = adapterRv
        recyclerView!!.adapter = AlphaInAnimationAdapter(adapterRv)
        recyclerView!!.adapter = AlphaInAnimationAdapter(adapterRv).apply {
            setDuration(500)
            setFirstOnly(false)
        }
    }

    fun addData(student: Student) {
        studentArrayList.add(student)
        adapterRv!!.notifyItemInserted(studentArrayList.size)
        openAnimationDialog()
        Handler().postDelayed({
            (activity as HomeActivity).switchViewPager()
        }, 1100)
        checkRv()
    }

    private fun checkRv() {
        if (studentArrayList.isEmpty()) {
            recyclerView!!.visibility = View.GONE
            tvListIsEmpty!!.visibility = View.VISIBLE
        } else {
            recyclerView!!.visibility = View.VISIBLE
            tvListIsEmpty!!.visibility = View.GONE
        }
    }

    override fun onItemClicked(position: Int) {
        openDialog(position)
    }

    private fun openDialog(position: Int) {
        val dialog = BottomSheetDialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_onclick_item)
        dialog.window!!.attributes.windowAnimations = R.style.DialogTheme
        val btnDelete = dialog.findViewById<LinearLayout>(R.id.llDelete)
        val btnView = dialog.findViewById<LinearLayout>(R.id.llView)
        btnDelete!!.setOnClickListener {
            studentArrayList.removeAt(position)
            adapterRv!!.notifyItemRemoved(position)
            checkRv()
            dialog.dismiss()
        }
        btnView!!.setOnClickListener {
            val studentObject = studentArrayList[position]
            (activity as HomeActivity).viewData(studentObject)
        }
        dialog.show()
    }

    private fun openAnimationDialog() {
        val dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.student_added_dialog)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window!!.attributes.windowAnimations = R.style.DialogTheme
        dialog.show()
        Handler().postDelayed({
            dialog.dismiss()
        }, 1000)
    }
}
