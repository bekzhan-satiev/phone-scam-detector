package kg.digitalshield.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaCodec
import android.media.MediaExtractor
import android.media.MediaFormat
import android.media.MediaMuxer
import android.os.Environment
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import java.io.File
import java.io.FileOutputStream
import java.io.RandomAccessFile
import java.nio.ByteBuffer

class PhoneStateReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        try {
            println("Receiver start")
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
            val incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)

            when (state) {
                TelephonyManager.EXTRA_STATE_RINGING -> {
                    Toast.makeText(context, "Incoming Call State", Toast.LENGTH_SHORT).show()
                    Toast.makeText(
                        context,
                        "Ringing State Number is - $incomingNumber",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TelephonyManager.EXTRA_STATE_OFFHOOK -> {
                    Toast.makeText(context, "Call Received State", Toast.LENGTH_SHORT).show()
                }

                TelephonyManager.EXTRA_STATE_IDLE -> {
                    Toast.makeText(context, "Call Idle State", Toast.LENGTH_SHORT).show()

                    val folder = File(Environment.getExternalStorageDirectory(), "MIUI/sound_recorder/call_rec/")
                    val lastRecording = folder.listFiles()?.sortedByDescending { it.lastModified() }?.firstOrNull()

                    lastRecording?.let { mp3File ->
                        Log.d("Latest file", mp3File.name)
                        
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}