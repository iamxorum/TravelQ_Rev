package com.travelq.service;

import com.travelq.dto.NotificationDto;
import com.travelq.exception.NotificationNotFoundException;
import com.travelq.model.NotificationEntity;
import com.travelq.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final ModelMapper modelMapper;

    @Override
    public NotificationDto addNotification(NotificationDto notificationDto) {
        NotificationEntity notificationEntity = modelMapper.map(notificationDto, NotificationEntity.class);
        NotificationEntity saved = notificationRepository.save(notificationEntity);
        return modelMapper.map(saved, NotificationDto.class);
    }

    @Override
    public List<NotificationDto> getAllNotifications() {
        List<NotificationEntity> notificationList = notificationRepository.findAll();
        return notificationList.stream()
                .map(e -> modelMapper.map(e, NotificationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public NotificationDto getNotificationById(Long notificationId) {
        NotificationEntity notificationEntity = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotificationNotFoundException(notificationId));
        return modelMapper.map(notificationEntity, NotificationDto.class);
    }

    @Override
    public NotificationDto updateNotification(Long notificationId, NotificationDto notificationDto) {
        NotificationEntity existingEntity = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotificationNotFoundException(notificationId));
        modelMapper.map(notificationDto, existingEntity);
        NotificationEntity updatedEntity = notificationRepository.save(existingEntity);
        return modelMapper.map(updatedEntity, NotificationDto.class);
    }

    @Override
    public void deleteNotification(Long notificationId) {
        if (!notificationRepository.existsById(notificationId)) {
            throw new NotificationNotFoundException(notificationId);
        }
        notificationRepository.deleteById(notificationId);
    }
}
