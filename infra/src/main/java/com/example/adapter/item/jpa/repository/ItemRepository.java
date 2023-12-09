package com.example.adapter.item.jpa.repository;

import com.example.adapter.item.jpa.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {


}
