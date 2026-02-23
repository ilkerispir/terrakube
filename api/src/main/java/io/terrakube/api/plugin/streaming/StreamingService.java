package io.terrakube.api.plugin.streaming;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import io.terrakube.api.repository.StepRepository;
import io.terrakube.api.rs.job.JobStatus;
import io.terrakube.api.rs.job.step.Step;
import org.apache.commons.text.TextStringBuilder;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class StreamingService {

    RedisTemplate redisTemplate;

    StepRepository stepRepository;

    public String getCurrentLogs(String stepId) {
        TextStringBuilder currentLogs = new TextStringBuilder();
        try {
            Step step = stepRepository.getReferenceById(UUID.fromString(stepId));
            String streamKey = String.valueOf(step.getJob().getId());
            // Always try Redis first - ephemeral jobs may complete before UI polls
            List<MapRecord> streamData = redisTemplate.opsForStream().read(StreamOffset.fromStart(streamKey));
            for (MapRecord mapRecord : streamData) {
                StringRecord stringRecord = StringRecord.of(mapRecord);
                String output = stringRecord.getValue().get("output");
                if (output != null) {
                    currentLogs.appendln(output);
                }
            }
            if (currentLogs.size() > 0) {
                log.info("Logs Size from Redis: {}", currentLogs.size());
            }
        } catch (Exception ex) {
            log.error("Error reading Redis stream: {}", ex.getMessage());
        }
        return currentLogs.toString();
    }
}
