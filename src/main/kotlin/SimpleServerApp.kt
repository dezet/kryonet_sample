import com.esotericsoftware.kryonet.Connection
import com.esotericsoftware.kryonet.Listener
import com.esotericsoftware.kryonet.Server
import org.apache.logging.log4j.LogManager
import java.io.IOException

object SimpleServerApp {
    @JvmStatic
    fun main(args: Array<String>) {
        val logger = LogManager.getLogger(SimpleServerApp::class.java)
        logger.info("asdsadsa")
        val server = Server()
        NetworkUtil.register(server.kryo)
        server.start()

        try {
            server.bind(54555, 54777)
            server.addListener(object : Listener() {
                override fun received(connection: Connection?, obj: Any?) {
                    if (obj is SimpleRequest) {
                        logger.info("Got some info from the socket: sq.getText()")
                        try {
                            val sr = SimpleResponse(Integer.parseInt(obj.text).toDouble())
                            connection!!.sendTCP(sr)
                        } catch (e: NumberFormatException) {
                            logger.warn("Exception while trying to parse request text as number.")
                            logger.warn(e.message)
                        }

                    }
                }
            })
        } catch (e: IOException) {
            e.printStackTrace()
        }


    }
}