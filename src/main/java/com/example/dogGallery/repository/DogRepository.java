package com.example.dogGallery.repository;

import com.example.dogGallery.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DogRepository extends JpaRepository<Dog, Integer> {
    List<Dog> findByBreedType(String breedType);
    List<Dog> findByNameContaining(String name);
}