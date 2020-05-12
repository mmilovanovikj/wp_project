package wp.project.finki.controller;

import wp.project.finki.domain.TravelingData;
import wp.project.finki.domain.wrapper.Date;
import wp.project.finki.service.TravelingDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Represents controller for traveling data
 */
@RestController
@RequestMapping("/traveling_data")
public class TravelingDataController {
    private TravelingDataService travelingDataService;

    @Autowired
    public TravelingDataController(TravelingDataService travelingDataService) {
        this.travelingDataService = travelingDataService;
    }

    @PostMapping
    public TravelingData save(@RequestBody TravelingData travelingData) {
        return travelingDataService.save(new TravelingData(travelingData));
    }

    @GetMapping(value = "/{id}")
    public TravelingData findById(@PathVariable("id") long id) {
        return travelingDataService.findById(id);
    }

    @GetMapping(value = "/dates")
    public List<TravelingData> findByDates(@RequestBody Date date) {
        return travelingDataService.findByDates(date);
    }

    @GetMapping(value = "/all")
    public List<TravelingData> findAll() {
        return travelingDataService.findAll();
    }

    @PutMapping(value = "/dates/{travelingDataId}")
    public void updateDates(@RequestBody Date dates,
                            @PathVariable("travelingDataId") long travelingDataId) {
        travelingDataService.updateDates(travelingDataId, dates);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable("id") long id) {
        travelingDataService.deleteById(id);
    }
}