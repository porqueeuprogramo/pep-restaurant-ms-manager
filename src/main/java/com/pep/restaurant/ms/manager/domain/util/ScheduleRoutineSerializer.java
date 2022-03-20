package com.pep.restaurant.ms.manager.domain.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pep.restaurant.ms.manager.domain.ScheduleRoutine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ScheduleRoutineSerializer {

    /**
     * Serialize scheduleRoutine to byte array.
     * @param scheduleRoutine scheduleRoutine.
     * @return byte array with scheduleRoutine serialized.
     * @throws IOException Exception.
     */
    public static byte[] toByteArray(final ScheduleRoutine scheduleRoutine) throws IOException {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final GZIPOutputStream gzipOutputStream = new GZIPOutputStream(output);
        new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .writeValue(gzipOutputStream, scheduleRoutine);
        return output.toByteArray();
    }

    /**
     * Deserialize byte array to scheduleRoutine.
     * @param byteArray byteArray.
     * @return scheduleRoutine deserialized.
     * @throws IOException Exception.
     */
    public static ScheduleRoutine fromByteArray(final byte[] byteArray) throws IOException {
        final ByteArrayInputStream output = new ByteArrayInputStream(byteArray);
        final GZIPInputStream gzipInputStream = new GZIPInputStream(output);
        return new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .readValue(gzipInputStream, new TypeReference<ScheduleRoutine>() {});
    }
}
