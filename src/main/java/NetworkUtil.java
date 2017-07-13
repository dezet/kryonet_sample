import com.esotericsoftware.kryo.Kryo;

public class NetworkUtil {

    public NetworkUtil() {
    }


    public static void register(Kryo kryo) {
        kryo.register(String.class);
        kryo.register(Double.class);
        kryo.register(SimpleRequest.class);
        kryo.register(SimpleResponse.class);
    }

}
