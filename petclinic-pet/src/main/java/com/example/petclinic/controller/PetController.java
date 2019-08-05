package com.example.petclinic.controller;

import com.example.petclinic.model.Pet;
import com.example.petclinic.service.PetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pet")
public class PetController implements BasicController<Pet> {

    private static final Logger logger = LoggerFactory.getLogger(PetController.class.getName());

    private PetService petService;

    public PetController(PetService petService) {

        this.petService = petService;
    }

    @Override
    @PostMapping(value = "addPet", produces = "application/json")
    public Pet add(@RequestBody Pet pet) {

        String petName = pet.getName();
        logger.info(new StringBuilder().append("Adding pet [").append(petName).append("].").toString());

        return this.petService.add(pet);
    }

    @Override
    @GetMapping(value = "getPetById/{id}", produces = "application/json")
    public Pet get(@PathVariable("id") Long id) {

        return this.petService.get(id);
    }

    @Override
    @PutMapping(value = "updatePet", produces = "application/json")
    public Pet modify(@RequestBody Pet pet) {

        String petName = pet.getName();
        logger.info(new StringBuilder().append("Updating pet [").append(petName).append("].").toString());

        return this.petService.modify(pet);
    }

    @Override
    @DeleteMapping(value = "deletePet", produces = "application/json")
    public boolean delete(@RequestBody Pet pet) {

        return this.petService.delete(pet);
    }

    @Override
    @GetMapping(value = "getAllPets", produces = "application/json")
    public List<Pet> getAll() {

        return this.petService.getAll();

    }

    @GetMapping(value = "getAllPetsByOwner/{owner}", produces = "application/json")
    public List<Pet> getAllPetsForOwner(@PathVariable("owner") String owner) {

        return this.petService.getAllPetsForOwner(owner);
    }

    @GetMapping(value = "getPetByName/{name}", produces = "application/json")
    public List<Pet> getPetByName(@PathVariable("name") String name) {

        return this.petService.getPetByName(name);
    }
}
