package com.example.dogGallery.controller;

import com.example.dogGallery.model.Dog;
import com.example.dogGallery.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dogs")
public class DogController {
    @Autowired
    private DogService dogService;

    @GetMapping
    public List<Dog> getAllDogs() {
        return dogService.getAllDogs();
    }

    @GetMapping("/{id}")
    public Optional<Dog> getDogById(@PathVariable int id) {
        return dogService.getDogById(id);
    }

    @PostMapping
    public Dog addDog(@RequestBody Dog dog) {
        return dogService.addDog(dog);
    }

    @PutMapping("/{id}")
    public Dog updateDog(@PathVariable int id, @RequestBody Dog dog) {
        return dogService.updateDog(id, dog);
    }

    @DeleteMapping("/{id}")
    public void deleteDog(@PathVariable int id) {
        dogService.deleteDog(id);
    }

    @GetMapping("/breed/{breed}")
    public List<Dog> getDogsByBreed(@PathVariable String breed) {
        return dogService.getDogsByBreedType(breed);
    }

    @GetMapping("/search/{name}")
    public List<Dog> searchDogsByName(@PathVariable String name) {
        return dogService.searchDogsByName(name);
    }
}