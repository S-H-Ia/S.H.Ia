package kani.springsecurity.Domain.Embeding;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kani.springsecurity.Application.Controller.Response.ProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Vector;

@Service
@RequiredArgsConstructor
public class EmbedingService {
    private final WebClient client;
    private final ObjectMapper objectMapper = new ObjectMapper();


    public Mono<?> getEmbeding(Long id, ProfileResponse profile) {
        var request = client.post()
                .uri("/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(profile)
                .retrieve()
                .bodyToMono(String.class)
                .map(json -> {
                    try {
                        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        JsonNode node = objectMapper.readTree(json);

                        String embedding = node.get("embedding").asText();
                        String userId = node.get("user_id").asText();

                        // retorna o que você precisar
                        return Map.of(
                                "embedding", embedding,
                                "user_id", userId
                        );
                    } catch (Exception e) {
                        throw new RuntimeException("Erro ao parsear JSON: " + e.getMessage());
                    }
                });
        System.out.println(request);
        return request;
    }

}
