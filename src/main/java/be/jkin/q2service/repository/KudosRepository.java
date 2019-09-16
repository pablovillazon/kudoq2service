package be.jkin.q2service.repository;

import be.jkin.q2service.model.Kudos;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface KudosRepository extends MongoRepository<Kudos, String> {
    Kudos findBy_id(ObjectId _id);
}
