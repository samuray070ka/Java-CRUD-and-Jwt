package com.startup.FirstStartUp.service;

import com.startup.FirstStartUp.model.Slider;
import com.startup.FirstStartUp.repository.SliderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SliderService {
    private final SliderRepository sliderRepository;

    public SliderService(SliderRepository sliderRepository) {
        this.sliderRepository = sliderRepository;
    }

    public List<Slider> getAllSliders() {
        return sliderRepository.findAll();
    }

    public Optional<Slider> getSliderById(Long id) {
        return sliderRepository.findById(id);
    }

    public Slider createSlider(Slider slider) {
        return sliderRepository.save(slider);
    }

    public Slider updateSlider(Long id, Slider slider) {
        slider.setId(id);
        return sliderRepository.save(slider);
    }

    public void deleteSlider(Long id) {
        sliderRepository.deleteById(id);
    }
}