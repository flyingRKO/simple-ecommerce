package com.example.simpleecommerce.dg;

import com.example.simpleecommerce.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.example.simpleecommerce.enums.DeliveryStatus.*;

@Component
@RequiredArgsConstructor
public class DeliveryStatusUpdater {
    private final DeliveryRepository deliveryRepository;
    @Scheduled(fixedRate = 10000)
    public void deliveryStatusUpdate() {
        System.out.println("----------delivery status update----------");

        var inDeliveryList = deliveryRepository.findAllByStatus(IN_DELIVERY);
        inDeliveryList.forEach(delivery -> {
            delivery.setStatus(COMPLETED);
            deliveryRepository.save(delivery);
        });

        var requestedList = deliveryRepository.findAllByStatus(REQUESTED);
        requestedList.forEach(delivery -> {
            delivery.setStatus(IN_DELIVERY);
            deliveryRepository.save(delivery);
        });
    }
}
