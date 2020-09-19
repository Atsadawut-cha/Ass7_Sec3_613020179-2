package com.example.ass7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_dialog_layout.*

class MainActivity : AppCompatActivity() {

    val employeeList = arrayListOf<Employee>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        employeeData()
        recycler_view.adapter = EmployeeAdapter(this.employeeList,applicationContext)
        recycler_view.layoutManager = LinearLayoutManager(applicationContext)

    }
    fun employeeData(){
        employeeList.add(Employee("Danny","Male","danny@kku.ac.th",30000))
        employeeList.add(Employee("Sara","Female","sara@kku.ac.th",34000))
    }
    fun addEmployee(view :View) {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.add_dialog_layout,null)
        val myBuilder = AlertDialog.Builder(this)
        myBuilder.setView(mDialogView)

        val mAlertDialog = myBuilder.show()
        mAlertDialog.btnAdd.setOnClickListener(){
            var selectedId: Int = mAlertDialog.radioGroup.checkedRadioButtonId
            var radioButton: RadioButton? = mAlertDialog.findViewById(selectedId)
            employeeList.add(
                Employee(
                    mAlertDialog.edt_name.text.toString(),
                    radioButton?.text.toString(),
                    mAlertDialog.edt_email.text.toString(),
                    mAlertDialog.edt_salary.text.toString().toInt()
                )
            )
            recycler_view.adapter?.notifyDataSetChanged()
            Toast.makeText(
                applicationContext,
                "The employee is added successfully",
                Toast.LENGTH_LONG
            ).show()
            mAlertDialog.dismiss()
        }
        mAlertDialog.btnCancel.setOnClickListener(){
            mAlertDialog.dismiss()
        }
        }
    }
