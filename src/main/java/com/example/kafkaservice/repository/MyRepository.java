package com.example.kafkaservice.repository;


import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Date;

@Repository
public interface MyRepository extends ReactiveCrudRepository<MyEntity, Integer> {

    @Query("SELECT org_id,lost, namespace FROM table WHERE originMessageId=:originMessageId")
    Mono<MyEntity> findByMessageId(String originMessageId);

    @Query("UPDATE someTable SET date_from_queue=case WHEN date_from_queue IS NULL THEN now() ELSE date_from_queue END," +
            " sending_timestamp=:timestamp, reread=reread+1 WHERE originMessageId=:originMessageId")
    Mono<Integer> updateByMessageId(Date timestamp, String originMessageId);
}
