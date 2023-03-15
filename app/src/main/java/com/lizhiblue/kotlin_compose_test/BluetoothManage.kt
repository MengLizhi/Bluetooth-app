package com.lizhiblue.kotlin_compose_test

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.util.Log

class BluetoothDeviceManage {

    init {

    }

    fun createBluetooth (openResult: () -> Boolean) {
        val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter != null) {
            Log.i("BLUETOOTH", "设备支持蓝牙")
            if(bluetoothAdapter.isEnabled || openResult()) {
                Log.i("BLUETOOTH", "设备是否开启蓝牙")
            }
        }
    }
}