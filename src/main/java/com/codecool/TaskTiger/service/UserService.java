package com.codecool.TaskTiger.service;


import com.codecool.TaskTiger.model.Message;
import com.codecool.TaskTiger.model.Reservation;
import com.codecool.TaskTiger.model.TimeSlot;
import com.codecool.TaskTiger.model.WorkType;
import com.codecool.TaskTiger.model.user.AppUser;
import com.codecool.TaskTiger.model.user.TaskerInfo;
import com.codecool.TaskTiger.repository.MessageRepository;
import com.codecool.TaskTiger.repository.ReservationRepository;
import com.codecool.TaskTiger.repository.UserRepository;
import com.codecool.TaskTiger.security.jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final ReservationRepository reservationRepository;
    private final JwtService jwtService;

    @Autowired
    public UserService(UserRepository userRepository, MessageRepository messageRepository, ReservationRepository reservationRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.reservationRepository = reservationRepository;
        this.jwtService = jwtService;
    }

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    public List<AppUser> getAllTaskers() {
        List<AppUser> taskers = new ArrayList<>();
        for (AppUser appUser : userRepository.findAll()) {
            if (appUser.isTasker()) {
                taskers.add(appUser);
            }
        }
        return taskers;
    }

    public void deleteUserById(Integer id) {
        List<Message> receiverMessages = messageRepository.findAllByReceiver_Id(id.longValue());
        List<Message> senderMessages = messageRepository.findAllBySender_Id(id.longValue());
        receiverMessages.forEach(message -> message.setReceiver(null));
        senderMessages.forEach(message -> message.setSender(null));
        messageRepository.saveAll(receiverMessages);
        messageRepository.saveAll(senderMessages);
        List<Reservation> clientReservations = reservationRepository.findAllByClient_Id(id.longValue());
        List<Reservation> taskerReservations = reservationRepository.findAllByTasker_Id(id.longValue());
        clientReservations.forEach(reservation -> reservation.setClient(null));
        taskerReservations.forEach(reservation -> reservation.setTasker(null));
        reservationRepository.saveAll(clientReservations);
        reservationRepository.saveAll(taskerReservations);
        userRepository.deleteById(id.longValue());
    }


    public AppUser getUserByUserId(Long id) {
        return userRepository.getUserById(id);
    }

    public boolean saveTimeSlots(List<TimeSlot> timeSlotList, Long id) {
        AppUser tasker = userRepository.getUserById(id);
        TaskerInfo taskerInfo = tasker.getTaskerInfo();
        timeSlotList.forEach(timeSlot -> timeSlot.setTasker(taskerInfo));
        return true;
    }

    public AppUser saveTaskerInfo(TaskerInfo taskerInfo, Long id) {
        AppUser tasker = userRepository.getUserById(id);
        tasker.setTaskerInfo(taskerInfo);
        return userRepository.save(tasker);
    }

    public List<AppUser> filterUserByWorkType(List<String> workTypes) {
        return userRepository.findAll().stream().filter(AppUser::isTasker).filter(user -> new HashSet<>(user.getTaskerInfo()
                .getSkills()).containsAll(workTypes.stream().map(WorkType::valueOf).toList())).collect(Collectors.toList());
//        List<WorkType> workTypeEnums = workTypes.stream().map(WorkType::valueOf).toList();
//        return userRepository.findTaskersBySkills(workTypeEnums);
    }

    public AppUser getUserFromToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUserName(token);
        return userRepository.getUserByUsername(username).orElseThrow();
    }

    public List<String> getUserSkills() {
        return Arrays.stream(WorkType.values()).map(Enum::name).collect(Collectors.toList());
    }
}
