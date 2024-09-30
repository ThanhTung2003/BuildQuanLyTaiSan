package com.example.quanlytaisanbt2

interface Objectt {
    fun getName():String
    fun getInfo():String
    fun getCategory():String
    fun getInfoStatistical():String{
        return " + ${getName()}: ${getCategory()}"
    }
}