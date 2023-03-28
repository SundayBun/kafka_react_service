package com.example.kafkaservice.repository;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table(name = "sometable")
@Data
public class MyEntity {
    @Id
    private Integer id;
    @Column("org_id")
    private String orgId;
    @Column("lost")
    private Boolean lost;
    @Column("namespace")
    private String namespace;
    @Column("originmessageid")
    private String originmessageid;
    @Column("sending_timestamp")
    private Date sendingTimestamp;
}
