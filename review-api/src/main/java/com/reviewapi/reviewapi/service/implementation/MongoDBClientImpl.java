package com.reviewapi.reviewapi.service.implementation;

import com.mongodb.MongoClient;
import com.reviewapi.reviewapi.dto.MyLocationDto;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.reviewapi.reviewapi.repository.MorphiaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MongoDBClientImpl implements MorphiaRepository {

    private static final Logger LOG = LoggerFactory.getLogger(MongoDBClientImpl.class);

    private Morphia morphia;
    private Datastore datastore;

    public MongoDBClientImpl() {
        LOG.info("initializing MongoDB Connection");
        morphia = new Morphia();
        morphia.mapPackage("com.reviewapi.reviewapi");
        datastore = morphia.createDatastore(new MongoClient( "localhost" ), "myLocations");
        datastore.ensureIndexes();
    }

    public MyLocationDto save(MyLocationDto obj){
        LOG.info("save(): {}", obj);
        datastore.save(obj);
        return obj;
    }

    public List<MyLocationDto> get(){
        try {
            LOG.info("get()");
            return datastore.createQuery(MyLocationDto.class).asList();
        }catch (Exception e) {
            LOG.error("get(): {}", e);
        }

        return new ArrayList<>();
    }

    public List<MyLocationDto> get(String inputString){
        try {
            LOG.info("get(): {}", inputString);
       return datastore.createQuery(MyLocationDto.class)
                .search(inputString)
                .order("id")
                .asList();
        }catch (Exception e) {
            LOG.error("get(): {}", e);
        }
        return new ArrayList<>();
    }

    @Override
    public MyLocationDto update(MyLocationDto obj) {
        return null;
    }

    @Override
    public Boolean delete(MyLocationDto obj) {
        return null;
    }
}
