import com.esotericsoftware.kryo.Kryo

class NetworkUtil {
    companion object {
        internal fun register(kryo: Kryo) {
            kryo.register(String::class.java)
            kryo.register(Double::class.java)
            kryo.register(SimpleRequest::class.java)
            kryo.register(SimpleResponse::class.java)
        }
    }

}
