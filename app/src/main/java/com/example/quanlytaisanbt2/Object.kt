package com.example.quanlytaisanbt2

interface Object {
    fun getName():String
    fun getInfo():String
    fun getcategory():String
    fun getInfoStatistical():String{
        return " + ${getName()}: ${getcategory()}"
    }
}