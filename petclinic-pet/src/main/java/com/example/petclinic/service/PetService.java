package com.example.petclinic.service;

import com.example.petclinic.model.Owner;
import com.example.petclinic.model.Pet;
import com.example.petclinic.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService implements BasicService<Pet> {

    private PetRepository petRepository;

    public PetService(PetRepository petRepository) {

        this.petRepository = petRepository;
    }

    @Override
    public Pet add(Pet pet) {

        return petRepository.save(pet);
    }

    @Override
    public Pet get(Long id) {

        Optional optional = petRepository.findById(id);

        Pet result = null;
        if (optional.isPresent()) {
            result = (Pet) optional.get();
        }
        return result;
    }

    @Override
    public Pet modify(Pet pet) {

        return petRepository.save(pet);
    }

    @Override
    public boolean delete(Pet pet) {

        petRepository.delete(pet);
        return true;
    }

    @Override
    public List<Pet> getAll() {

        return (List<Pet>) petRepository.findAll();
    }

    public List<Pet> getAllPetsForOwner(String owner) {

        return this.petRepository.getPetsByOwner(Owner.builder().withName(owner).build());
    }

    public List<Pet> getPetByName(String name) {

        return this.petRepository.getPetByName(name);
    }
}
