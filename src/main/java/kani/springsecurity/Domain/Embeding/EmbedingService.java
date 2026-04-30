package kani.springsecurity.Domain.Embeding;

import kani.springsecurity.Application.Controller.Response.ProfileResponse;
import kani.springsecurity.Application.Events.SendSavedProfileToEmbedding;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmbedingService {
    private final WebClient client;
    private final EmbedingRepository repo;

    @Async
    @EventListener
    public Mono<EmbedingResponse> EmbedSavedProfile(SendSavedProfileToEmbedding profileToEmbedding) {
        var profile = profileToEmbedding.profileResponse();
        return client.post()
                .uri("/" + profile.user_id())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(profile)
                .retrieve()
                .bodyToMono(EmbedingResponse.class);
    }

    public List<Embeding> getAll() {
    return repo.findAll();
    }


    public Mono<EmbedingResponse> getEmbeding(Long id, ProfileResponse profile) {
        return client.post()
                .uri("/" + profile.user_id())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(profile)
                .retrieve()
                .bodyToMono(EmbedingResponse.class);
    }

}
