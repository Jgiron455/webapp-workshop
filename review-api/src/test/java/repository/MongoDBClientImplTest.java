package repository;

import com.reviewapi.reviewapi.dto.MyLocationDto;
import com.reviewapi.reviewapi.service.implementation.MongoDBClientImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MongoDBClientImplTest {


    private MongoDBClientImpl mongoDBClient;

    @Before
    public void setup(){
        mongoDBClient = new MongoDBClientImpl();
    }

    @Test
    public void save() {
        mongoDBClient.save(MyLocationDto.builder().withName("Costa").build());
    }

    @Test
    public void get() {

        List<MyLocationDto> myLocationDtoList = mongoDBClient.get();
        System.out.println(myLocationDtoList);
    }

    @Test
    public void get1() {
        List<MyLocationDto> myLocationDtoList = mongoDBClient.get("Costa");
        System.out.println(myLocationDtoList);
    }
}