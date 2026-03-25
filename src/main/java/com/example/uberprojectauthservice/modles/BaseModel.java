package com.example.uberprojectauthservice.modles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
@Setter
public abstract class BaseModel {
    @Id //this annotation makes the id property as a primary key of our table
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id ;

    @Column(nullable = false)
    @CreatedDate  //this annotation tells spring that only handle it for object creation
    protected LocalDateTime createdAt;


    @Column(nullable = false)
    @LastModifiedDate  // this annotation tells spring that only handle it for object update
    protected LocalDateTime updatedAt;
}
