package repository;


import com.reviewapi.reviewapi.dto.MyLocationDto;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MorphiaRepository {


    MyLocationDto save(MyLocationDto obj);

    List<MyLocationDto> get();

    List<MyLocationDto> get(String inputText);

    MyLocationDto update(MyLocationDto obj);

    Boolean delete(MyLocationDto obj);
}
