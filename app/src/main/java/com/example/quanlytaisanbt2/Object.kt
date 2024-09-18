package com.example.quanlytaisanbt2

interface Object {
    fun getName():String
    fun getInfo():String
    fun getCategory():String
    fun getInfoStatistical():String{
        return " + ${getName()}: ${getCategory()}"
    }
}