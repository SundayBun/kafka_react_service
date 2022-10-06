package com.example.kafkaservice.repository;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table(name = "someTable")
@Data
public class MyEntity {
    @Id
    private Integer id;
    @Column("method")
    private String rootElement;
    @Column("org_id")
    private Integer orgId;
//    @Column("node_id")
//    private String nodeId;
    @Column("lost")
    private Boolean lost;
    @Column("namespace")
    private String namespace;
    @Column("sending_timestamp")
    private Date sendingTimestamp;
}
