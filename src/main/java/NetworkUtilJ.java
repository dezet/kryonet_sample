import com.esotericsoftware.kryo.Kryo;

public class NetworkUtilJ {

    public NetworkUtilJ() {
    }


    public static void register(Kryo kryo) {
        kryo.register(String.class);
        kryo.register(Double.class);
        kryo.register(SimpleRequestJ.class);
        kryo.register(SimpleResponse.class);
    }

}
