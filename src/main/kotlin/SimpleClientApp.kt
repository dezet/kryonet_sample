import com.esotericsoftware.kryonet.Client
import com.esotericsoftware.kryonet.Connection
import com.esotericsoftware.kryonet.Listener
import com.esotericsoftware.minlog.Log
import java.io.IOException

object SimpleClientApp {
    @JvmStatic
    fun main(args: Array<String>) {
        Log.set(Log.LEVEL_TRACE)
        val c = Client()
        NetworkUtil.register(c.kryo)
        c.start()
        try {
            c.connect(5000, "127.0.0.1", 54555, 54777)
            val sq = SimpleRequest("10")
            c.sendTCP(sq)
            c.addListener(object : Listener() {
                override fun received(connection: Connection?, obj: Any?) {
                    if (obj is SimpleResponse) {
                        Log.info("Got message back: ")
                        Log.info(obj.number.toString())
                    }
                }
            })
        } catch (e: IOException) {
            e.printStackTrace()
        }
        try {
            Thread.sleep(10000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}