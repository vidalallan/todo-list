package com.example.demo.domain.task;

import com.example.demo.domain.statustask.StatusTask;
import com.example.demo.domain.typetask.TypeTask;
import com.example.demo.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="task")
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String description;
    private LocalDateTime startTask;
    private LocalDateTime endTask;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_task_id", foreignKey = @ForeignKey(name = "fk_task_to_status"))
    private StatusTask statusTask;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_task_to_user"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_task_id", foreignKey = @ForeignKey(name = "fk_task_to_type"))
    private TypeTask typeTask;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean deleted;

}
