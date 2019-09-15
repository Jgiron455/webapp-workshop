package com.reviewapi.reviewapi.controller;

import com.reviewapi.reviewapi.dto.MyLocationDto;
import com.reviewapi.reviewapi.dto.MyLocationReviewDto;
import com.reviewapi.reviewapi.dto.enumeration.QueryEnum;
import com.reviewapi.reviewapi.service.implementation.MongoDBClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.PARTIAL_CONTENT;

@Controller
@RequestMapping("/api/mongodb")
public class MongoDBController {

    private static final Logger LOG = LoggerFactory.getLogger(MongoDBController.class);

    @Autowired
    private MongoDBClientImpl mongoDBClientImpl;

    @GetMapping(value="/get")
    public @ResponseBody
    ResponseEntity<?> getMyReviews(@RequestParam(value ="inputText", required=false) String inputText) {
        LOG.info("getMyReviews() -> inputText: {}", inputText);
        Optional<List<MyLocationDto>> myLocationDtos = Optional.ofNullable(new ArrayList<>());
        if(inputText.isEmpty()) {
            myLocationDtos = Optional.ofNullable(mongoDBClientImpl.get());
        } else {
            myLocationDtos = Optional.ofNullable(mongoDBClientImpl.get(inputText));
        }

        LOG.info("getMyReviews() -> myLocationDtos: {}", myLocationDtos);
        return myLocationDtos.isPresent() ? new ResponseEntity<List<MyLocationDto>>(myLocationDtos.get(), OK) :
                new ResponseEntity<>("Invalid Request", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public @ResponseBody
    ResponseEntity<?> postMyReviews(@RequestBody MyLocationDto myLocationDto){
        LOG.info("postMyReviews() -> {}", myLocationDto);

        if(myLocationDto != null){
            MyLocationDto tempMyLocationDto = mongoDBClientImpl.save(myLocationDto);
           return new ResponseEntity<MyLocationDto>(tempMyLocationDto, OK);

        } else {
            LOG.warn("postMyReviews() -> Null Response");
            return new ResponseEntity<>("Invalid Request", HttpStatus.NOT_FOUND);
        }

    }




}
