package com.lizhiblue.kotlin_compose_test

import android.util.Log


class Test {
    val tag:String = "Test";
    init {
//        forNum()
//        forNum1()

//        foo()
         foo2().run {
             Log.d(tag, "FOO2 return $")
         }
    }

    fun forNum() {
        for (i in 1..3) {
            Log.d(tag, "Number val $i")

        }
    }

    fun forNum1() {
        for (i in 6 downTo 0 step 2) {
            Log.d(tag, "Number val $i")
        }
    }

    fun  foo() {
        listOf(1,2,3,4,5).forEach(fun(it) {
            if(it == 3) return
            Log.d(tag, "FOO number val $it")
        })
    }
    fun foo2() {
        run loop@ {
            listOf(1,3,4,5,).forEach {
                if(it == 3) return@loop
                Log.d(tag, "FOO2 number val $it")
            }
        }
    }
}