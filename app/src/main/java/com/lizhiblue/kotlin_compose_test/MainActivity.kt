package com.lizhiblue.kotlin_compose_test

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat


class MainActivity : ComponentActivity() {
    fun clickEven() {
        Log.i("Test", "click log")

        val uri: Uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Video.VideoColumns.DATA, MediaStore.Video.VideoColumns.TITLE, MediaStore.Video.VideoColumns.DURATION)
        val cursor = contentResolver.query(uri, projection, null, null, null)

        if(cursor != null) {
            while (cursor.moveToNext()) {
                val videoPath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.VideoColumns.DATA))
                val videoTitle = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.VideoColumns.TITLE))
                val videoDuration = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.VideoColumns.DURATION))
                // Do something with the video information
            }
        }

    }

    fun clickBlueTooth() {
        val REQUEST_ENABLE_BT = 1;
        var mang = BluetoothDeviceManage()
        mang.createBluetooth(openResult = {
            val endble = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)

            var flag = false;
            flag = if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_CONNECT
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Log.e("BLUETOOTH", "用户拒绝授予蓝牙权限")
                false;
            }  else {
                true;
            }
            startActivityForResult(endble, REQUEST_ENABLE_BT);
            flag;
        })



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greeting(
                "Android 123456",
                cb = { clickEven()},
                bluetooth = { clickBlueTooth() },
            );
        }
    }
}



@Composable
fun Greeting(name: String, cb: () -> Unit, bluetooth: () -> Unit, ) {
    Column(modifier = Modifier.padding(all = 0.dp), verticalArrangement = Arrangement.Center,) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = "Hello $name!")
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Hello --- $name!")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { cb() }) {
                Text(text = "BTN")
            }
        }
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Spacer(modifier = Modifier.width(32.dp))
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "搜索蓝牙")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { bluetooth() }) {
                Text(text = "BTN")
            }
        }
    }


}