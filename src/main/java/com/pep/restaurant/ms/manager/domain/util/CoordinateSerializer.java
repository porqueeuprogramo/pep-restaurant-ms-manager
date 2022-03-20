package com.pep.restaurant.ms.manager.domain.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pep.restaurant.ms.manager.domain.Coordinate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CoordinateSerializer {

    /**
     * Serialize coordinate to byte array.
     * @param coordinate coordinate.
     * @return byte array with coordinate serialized.
     * @throws IOException Exception.
     */
    public static byte[] toByteArray(final Coordinate coordinate) throws IOException {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final GZIPOutputStream gzipOutputStream = new GZIPOutputStream(output);
        new ObjectMapper().writeValue(gzipOutputStream, coordinate);
        return output.toByteArray();
    }

    /**
     * Deserialize byte array to coordinate.
     * @param byteArray byteArray.
     * @return coordinate deserialized.
     * @throws IOException Exception.
     */
    public static Coordinate fromByteArray(final byte[] byteArray) throws IOException {
        final ByteArrayInputStream output = new ByteArrayInputStream(byteArray);
        final GZIPInputStream gzipInputStream = new GZIPInputStream(output);
        return new ObjectMapper().readValue(gzipInputStream, new TypeReference<Coordinate>() {});
    }
}
