package be.jkin.q2service.controllers;

import be.jkin.q2service.model.Kudos;
import be.jkin.q2service.queue.Publisher;
import be.jkin.q2service.repository.KudosRepository;

import com.google.gson.Gson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class KudosController {
    @Autowired
    private KudosRepository kudosRepository;

    @Autowired
    Publisher publisher;

    @GetMapping("/kudos")
    public List<Kudos> getAllKudos()
    {
        return kudosRepository.findAll();
    }

    @GetMapping("/kudos/{id}")
    public ResponseEntity<Kudos> getKudosById(@PathVariable(value = "id") ObjectId id) throws Exception
    {
        Kudos kudos = kudosRepository.findBy_id(id);
        return ResponseEntity.ok().body(kudos);
    }

    @GetMapping("/kudos/ByTargetNickname/{nickname}")
    public ResponseEntity<List<Kudos>> getKudosByNickname(@PathVariable(value = "nickname") String nickname) throws Exception
    {
        List<Kudos> kudos = kudosRepository.findByDestino(nickname);
        return ResponseEntity.ok().body(kudos);

    }

    @PostMapping("/kudos")
    public Kudos createKudos(@Valid @RequestBody Kudos kudos)
    {
       @Valid Kudos newKudos = kudosRepository.save(kudos);

       //Send to Queue
       Gson gson = new Gson();
       String json = gson.toJson(newKudos);

        publisher.SendMessageToQueue(json);

       return newKudos;
    }

    @PutMapping("/kudos/{id}")
    public ResponseEntity<Kudos> updateKudos(@PathVariable(value = "id") Long kudosId,
                                             @Valid @RequestBody Kudos kudosDetails) throws Exception
    {
        Kudos kudos = kudosRepository.findById(String.valueOf(kudosId)).orElseThrow(()->new Exception("Kudos not found on::"+ kudosId));

        kudos.setFuente(kudosDetails.getFuente());
        kudos.setDestino(kudosDetails.getDestino());
        kudos.setTema(kudosDetails.getTema());

        final Kudos updatedKudos = kudosRepository.save(kudos);
        return ResponseEntity.ok(updatedKudos);
    }

    @DeleteMapping("/kudos/{id}")
    public Map<String, Boolean> deleteKudos(@PathVariable(value = "id") long kudosId) throws Exception
    {
        Kudos kudos = kudosRepository.findById(String.valueOf(kudosId)).orElseThrow(()->new Exception("Kudos not found on::"+kudosId));

        kudosRepository.delete(kudos);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Kudos deleted", Boolean.TRUE);

        return response;
    }
}
