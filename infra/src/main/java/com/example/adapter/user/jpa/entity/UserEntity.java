package com.example.adapter.user.jpa.entity;

import com.example.adapter.item.jpa.entity.ItemEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(exclude = "items")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String mail;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @Setter(AccessLevel.NONE)
    private Set<ItemEntity> items = new HashSet<>();

    public void addItem(ItemEntity item) {
        if (item == null) {
            return;
        }

        item.setUser(this);
        items.add(item);
    }
}
