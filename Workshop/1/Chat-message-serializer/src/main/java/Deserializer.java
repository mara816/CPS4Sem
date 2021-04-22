import com.google.gson.Gson;

public class Deserializer {
    private Deserializer() {}

    public static void main(String[] args) {
        String json = """
                    {
                        "username": "clausen",
                        "timestamp": "1618925917000",
                        "message": "Hello World!"
                    }
                """;
        EventMessage msg = deserialize(json);
        System.out.println(msg);

    }

    public static EventMessage deserialize(String json) {
        return new Gson().fromJson(json, EventMessage.class);
    }
}