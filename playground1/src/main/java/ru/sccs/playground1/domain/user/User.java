package ru.sccs.playground1.domain.user;

import jakarta.persistence.*;
import lombok.*;
import ru.sccs.playground1.domain.task.Task;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

//    private List<Task> tasks;

}
