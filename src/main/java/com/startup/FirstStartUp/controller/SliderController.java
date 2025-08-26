package com.startup.FirstStartUp.controller;

import com.startup.FirstStartUp.model.Slider;
import com.startup.FirstStartUp.service.SliderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sliders")
@CrossOrigin(origins = "http://localhost:3000")
public class SliderController {
    private final SliderService sliderService;

    public SliderController(SliderService sliderService) {
        this.sliderService = sliderService;
    }

    @GetMapping
    public List<Slider> getAllSliders() {
        return sliderService.getAllSliders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Slider> getSliderById(@PathVariable Long id) {
        return sliderService.getSliderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Slider createSlider(@RequestBody Slider slider) {
        return sliderService.createSlider(slider);
    }

    @PutMapping("/{id}")
    public Slider updateSlider(@PathVariable Long id, @RequestBody Slider slider) {
        return sliderService.updateSlider(id, slider);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSlider(@PathVariable Long id) {
        sliderService.deleteSlider(id);
        return ResponseEntity.noContent().build();
    }
}