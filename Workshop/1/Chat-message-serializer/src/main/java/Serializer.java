import com.google.gson.GsonBuilder;

public class Serializer {

    public static void main(String[] args) {
        EventMessage msg = new EventMessage();
        msg.setUsername("Clausen");
        msg.setTimestamp(System.currentTimeMillis());
        msg.setMessage("Hello World!");

        String json = serialize(msg);
        System.out.println(json);
    }

    public static String serialize(EventMessage msg) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(msg);
    }
}
