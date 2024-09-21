package com.teamcoffee.appdc.service;

import com.teamcoffee.appdc.persistence.entity.Appointment;
import lombok.RequiredArgsConstructor;
import com.teamcoffee.appdc.persistence.repository.AppointmentRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderService {

    private final AppointmentRepository appointmentRepository;
    private final TwilioService twilioService;

    @Scheduled(cron = "0 0 9 * * ?")
    public void sendAppointmentReminders(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plus(1, ChronoUnit.DAYS);

        List<Appointment> appointments = appointmentRepository.findAll();
        for (Appointment appointment : appointments){
            if (appointment.getAppointmentDate().toLocalDate().isEqual(tomorrow.toLocalDate())){
                String message = String.format("Recordatorio: Tiene una cita agendada para mañana a las %s",
                        appointment.getAppointmentDate().toString());

                twilioService.sendSms(appointment.getPatient().getPhone(), message);
            }
        }
    }

}
