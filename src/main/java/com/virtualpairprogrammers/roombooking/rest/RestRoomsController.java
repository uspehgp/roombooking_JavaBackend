package com.virtualpairprogrammers.roombooking.rest;

import com.virtualpairprogrammers.roombooking.data.RoomRepository;
import com.virtualpairprogrammers.roombooking.model.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RestRoomsController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable("id") long id) {
        return roomRepository.findById(id).get();
    }

    @PostMapping
    public Room newRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    @PutMapping
    public Room update(@RequestBody Room updatedRoom) {
        Room originalRoom = roomRepository.findById(updatedRoom.getId()).get();
        originalRoom.setCapacities(updatedRoom.getCapacities());
        originalRoom.setLocation(updatedRoom.getLocation());
        originalRoom.setName(updatedRoom.getName());
        return roomRepository.save(originalRoom);
    }
}
