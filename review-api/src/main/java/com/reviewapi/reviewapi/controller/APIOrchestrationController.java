package com.reviewapi.reviewapi.controller;

import com.reviewapi.reviewapi.dto.MyLocationReviewDto;
import com.reviewapi.reviewapi.dto.enumeration.QueryEnum;
import com.reviewapi.reviewapi.service.implementation.APIOrchestration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("/api/reviews")
public class APIOrchestrationController {
    private static final Logger LOG = LoggerFactory.getLogger(APIOrchestrationController.class);

    @Autowired
    private APIOrchestration apiOrchestration;

    @GetMapping(value="/search")
    public @ResponseBody
    ResponseEntity<?> getLocationsReview(@RequestParam(value ="inputText", required=false) String inputText,
                                                           @RequestParam(value ="lat", required=false) Double lat,
                                                           @RequestParam(value ="lng", required=false) Double lng,
                                                           @RequestParam(value ="radius", required=false) Integer radius) {
        LOG.info("getLocationsReview() -> inputText: {}, lat: {}, lng: {}, radius: {}", inputText, lat, lng, radius);
        Optional<MyLocationReviewDto> config = apiOrchestration.getMyLocationReview(QueryEnum.SEARCH, inputText, lat, lng, radius );

        LOG.info("getLocationsReview() -> MyLocationReviewDto: {}", config);
        return config.isPresent() ? new ResponseEntity<MyLocationReviewDto>(config.get(), OK) :
                new ResponseEntity<>("Invalid Request", HttpStatus.NOT_FOUND);
    }


}
