package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var  tvInput : TextView?=null
    private var decimal : TextView?=null
    private var lastnumeric :Boolean = false
    private var lastdot :Boolean =false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput =findViewById(R.id.tvInput)
        decimal=findViewById((R.id.btndecimal))

    }
    fun ondigit(view : View)
    {
        tvInput?.append((view as Button).text)
        lastnumeric=true
        lastdot=false
    }
    fun onclr(view: View)
    {
        tvInput?.text=""
    }
    fun ondecimalpoint(view: View)
    {
        if(lastnumeric&&!lastdot)
        {
            tvInput?.append(".")
            lastnumeric=false
            lastdot=true
        }
    }
    fun onEqual(view: View) {
        if (lastnumeric) {
            var tvValue = tvInput?.text.toString()
            var prefix = ""

            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty())
                        one = prefix + one
                    tvInput?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                } else if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty())
                        one = prefix + one
                    tvInput?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                } else if (tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty())
                        one = prefix + one
                    tvInput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                } else if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty())
                        one = prefix + one
                    tvInput?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                } else if (tvValue.contains("%")) {
                    val splitValue = tvValue.split("%")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty())
                        one = prefix + one
                    tvInput?.text = removeZeroAfterDot((one.toDouble() % two.toDouble()).toString())
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }


        }
    }
        private fun removeZeroAfterDot(result :String):String{
            var value =result
            if(value.contains(".0"))
                value=result.substring(0,result.length-2)
             return value
        }


    fun onOperator(view :View)
    {   tvInput?.text?.let{
        if(lastnumeric &&!isOperator(it.toString()))
        {
            tvInput?.append((view as Button).text)
            lastnumeric=false
            lastdot=false
        }
    }
    }
    private fun isOperator(value : String ) :Boolean
    {
        return if(value.startsWith("-"))
            false
        else
            value.contains("/")|| value.contains("*")||value.contains("+")||value.contains("%")||value.contains("-")
            true
    }
}