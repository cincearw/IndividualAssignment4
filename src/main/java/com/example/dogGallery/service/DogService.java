package com.example.dogGallery.service;

import com.example.dogGallery.model.Dog;
import com.example.dogGallery.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DogService {
    @Autowired
    private DogRepository dogRepository;

    public List<Dog> getAllDogs() {
        return dogRepository.findAll();
    }

    public Optional<Dog> getDogById(int id) {
        return dogRepository.findById(id);
    }

    public Dog addDog(Dog dog) {
        return dogRepository.save(dog);
    }

    public Dog updateDog(int id, Dog updatedDog) {
        return dogRepository.findById(id).map(dog -> {
            dog.setName(updatedDog.getName());
            dog.setDescription(updatedDog.getDescription());
            dog.setBreedType(updatedDog.getBreedType());
	    dog.setColor(updatedDog.getColor());
            dog.setAge(updatedDog.getAge());
            dog.setActiveDate(updatedDog.getActiveDate());
            return dogRepository.save(dog);
        }).orElse(null);
    }

    public void deleteDog(int id) {
        dogRepository.deleteById(id);
    }

    public List<Dog> getDogsByBreedType(String breed) {
        return dogRepository.findByBreedType(breed);
    }

    public List<Dog> searchDogsByName(String name) {
        return dogRepository.findByNameContaining(name);
    }
}