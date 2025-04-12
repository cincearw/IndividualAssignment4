package com.example.dogGallery.controller;

import com.example.dogGallery.model.Dog;
import com.example.dogGallery.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.*;
import java.util.*;


@Controller
@RequestMapping("/dogs")
public class DogController {
    @Autowired
    private DogService dogService;

    @GetMapping("/all")
    public Object getAllDogs(Model model) {

         model.addAttribute("dogList",dogService.getAllDogs());
         model.addAttribute("title", "All Poodles");
         return "animal-list";
    }

    @GetMapping("/{id}")
    public Object getDogById(@PathVariable int id,Model model) {
        Optional<Dog> optionalDog = dogService.getDogById(id);
        if (optionalDog.isPresent()) {
            Dog dog = optionalDog.get();  // Extract Dog from Optional
            model.addAttribute("dog", dog);
            model.addAttribute("title", "Poodle #: " + id);
            return "animal-details";  // Return the view with the dog data
        } else {
            // Handle the case where the dog is not found
            model.addAttribute("title", "Dog not found");
            return "error";  // You can create an error page or a 404 page
        }
    }

    @PostMapping
    public Dog addDog(@RequestBody Dog dog) {
        return dogService.addDog(dog);
    }

    @PutMapping("/{id}")
    public Dog updateDog(@PathVariable int id, @RequestBody Dog dog) {
        return dogService.updateDog(id, dog);
    }

    @GetMapping("/delete/{id}")
    public String deleteDog(@PathVariable int id) {
        dogService.deleteDog(id);
        return "redirect:/dogs/all";
    }

    @GetMapping("/breed/{breed}")
    public Object getDogsByBreed(@PathVariable String breed, Model model) {

        model.addAttribute("dogList", dogService.getDogsByBreedType(breed));
        model.addAttribute("title", "Poodles by Breed: " + breed);
        return "animal-list";
    }

    @GetMapping("/search")
    public Object searchDogsByName(@RequestParam("name") String name, Model model) {

        model.addAttribute("dogList", dogService.searchDogsByName(name));
        model.addAttribute("title", "Poodle by Name: " + name);
        return "animal-list";
    }


    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("dog", new Dog());
        model.addAttribute("title", "Create New Poodle");
        return "animal-create";
    }


    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Optional<Dog> optionalDog = dogService.getDogById(id);
        if (optionalDog.isPresent()) {
            model.addAttribute("dog", optionalDog.get());
            model.addAttribute("title", "Update Poodle");
            return "animal-update";
        } else {
            model.addAttribute("error", "Dog not found.");
            return "error";
        }
    }

    // POST mapping to create a new dog
    @PostMapping("/new")
    public String createDog(@ModelAttribute Dog dog, @RequestParam("imageFile") MultipartFile imageFile) {
        try {

            if (!imageFile.isEmpty()) {
                String filename = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
                Path uploadPath = Paths.get("src/main/resources/static/images");
                Files.createDirectories(uploadPath);
                Path filePath = uploadPath.resolve(filename);
                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);


                dog.setImage(filename);
            }

            dogService.saveDog(dog);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return "redirect:/dogs/all";
    }


    @PostMapping("/update")
    public String updateDog(@ModelAttribute Dog dog) {
        dogService.saveDog(dog);
        return "redirect:/dogs/" + dog.getId();
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/allPoodleDetails")
    public String allPoodleDetails() {
        return "redirect:/dogs/all";
    }


}