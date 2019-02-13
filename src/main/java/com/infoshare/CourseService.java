package com.infoshare;

import java.time.Instant;
import java.util.UUID;

public class CourseService {

    private final DrivingLicenseCandidateRepository drivingLicenseCandidateRepository;

    public CourseService(DrivingLicenseCandidateRepository drivingLicenseCandidateRepository) {
        this.drivingLicenseCandidateRepository = drivingLicenseCandidateRepository;
    }

    public UUID registerForCourse(Person person, Category category) {
        DrivingLicenseCandidate drivingLicenseCandidate = DrivingLicenseCandidate.init()
                .registerForCourse(person, category);

        return drivingLicenseCandidateRepository.save(drivingLicenseCandidate).getUuid();
    }

    public UUID completeCourse(UUID uuid, Instant when) {
        DrivingLicenseCandidate drivingLicenseCandidate = drivingLicenseCandidateRepository.getByUUID(uuid)
                .completeCourse();

        return drivingLicenseCandidateRepository.save(drivingLicenseCandidate).getUuid();
    }

    public UUID registerForExam(UUID uuid, Instant when) {
        DrivingLicenseCandidate drivingLicenseCandidate = drivingLicenseCandidateRepository.getByUUID(uuid)
                .registerForExam(when);

        return drivingLicenseCandidateRepository.save(drivingLicenseCandidate).getUuid();
    }

    public UUID grantLicense(UUID uuid) {
        DrivingLicenseCandidate drivingLicenseCandidate = drivingLicenseCandidateRepository.getByUUID(uuid)
                .grantLicense();
        return drivingLicenseCandidateRepository.save(drivingLicenseCandidate).getUuid();
    }

    public UUID forbidLicense(UUID uuid) {
        DrivingLicenseCandidate drivingLicenseCandidate = drivingLicenseCandidateRepository.getByUUID(uuid)
                .forbidLicense();

        return drivingLicenseCandidateRepository.save(drivingLicenseCandidate).getUuid();
    }
}
