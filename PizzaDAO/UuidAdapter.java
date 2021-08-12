package PizzaDAO;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UuidAdapter {
    public static byte[] getBytesFromUUID(UUID id) {
        byte[] uuidBytes = new byte[16];
        ByteBuffer.wrap(uuidBytes)
        .putLong(id.getMostSignificantBits())
        .putLong(id.getLeastSignificantBits());

        return uuidBytes;
    }

    public static UUID getUUIDFromBytes(byte[] bytes) {

        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        UUID id = new UUID(byteBuffer.getLong(), byteBuffer.getLong());

        return id;
    }
}
