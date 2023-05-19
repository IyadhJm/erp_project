package com.example.notification.Controller;

import com.example.notification.repositories.NotificationRepository;
import com.example.notification.entities.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")

@RestController
@RequestMapping("/notif")
public class NotifController {
    @Autowired
    private NotificationRepository notificationRepository;
    @GetMapping("/all")
    @ResponseBody

    public List<Notifications> produits(){
        return notificationRepository.findAll();
    }
}
