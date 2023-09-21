import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.LifecycleService

class PlayerService : LifecycleService() {
    companion object {
        fun startService(context: Context) {
            val startIntent = Intent(context, PlayerService::class.java)
            context.startService(startIntent)
            Log.d("PlayerService", "startService")
        }

        fun stopService(context: Context) {
            val stopIntent = Intent(context, PlayerService::class.java)
            context.stopService(stopIntent)
            Log.d("PlayerService", "stopService")
        }
    }
    override fun onCreate() {
        super.onCreate()
        Log.d("PlayerService", "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("PlayerService", "onDestroy")
    }
}



