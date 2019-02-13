package com.infoshare;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

class DrivingLicenseCandidate {

    private final UUID uuid;
    private Person person;
    private Category category;

    private Instant courseRegistrationDate;
    private Instant courseCompletedDate;
    private Instant examRegistrationDate;
    private Instant licenseGrantedDate;
    private Instant licenseForbiddenDate;
    private Instant examPassedDate;

    private DrivingLicenseCandidate() {
        uuid = UUID.randomUUID();
    }

    static DrivingLicenseCandidate init() {
        return new DrivingLicenseCandidate();
    }

    DrivingLicenseCandidate registerForCourse(final Person person, final Category category) {
        if (isUnderSeventeen(person)) {
            throw new IllegalStateException();
        }
        if (isLicenseGranted()) {
            throw new IllegalStateException();
        }
        if (isRegisteredForCourse()) {
            throw new IllegalStateException();
        }
        if (isLicenseForbidden()) {
            throw new IllegalStateException();
        }
        this.person = person;
        this.category = category;
        this.courseRegistrationDate = Instant.now();
        return this;
    }

    private boolean isLicenseForbidden() {
        return Optional.ofNullable(licenseForbiddenDate).isPresent();
    }

    private boolean isLicenseGranted() {
        return Optional.ofNullable(licenseGrantedDate).isPresent();
    }

    private boolean isUnderSeventeen(Person person) {
        return person.getBirthDate().isAfter(LocalDate.now().minusYears(17));
    }

    DrivingLicenseCandidate completeCourse() {
        if (!isRegisteredForCourse()) {
            throw new IllegalStateException();
        }
        this.courseCompletedDate = Instant.now();
        return this;
    }

    DrivingLicenseCandidate registerForExam(final Instant when) {
        if (!isCourseCompleted()) {
            throw new IllegalStateException();
        }
        if (isUnderEighteen()) {
            throw new IllegalStateException();
        }
        if (isLicenseForbidden()) {
            throw new IllegalStateException();
        }
        this.examRegistrationDate = Instant.now();
        return this;
    }

    private boolean isUnderEighteen() {
        return person.getBirthDate().isAfter(LocalDate.now().minusYears(18));
    }

    DrivingLicenseCandidate grantLicense() {
        if (!isCourseCompleted()) {
            throw new IllegalStateException();
        }
        if (!isRegisteredForExam()) {
            throw new IllegalStateException();
        }
        this.licenseGrantedDate = Instant.now();
        return this;
    }

    DrivingLicenseCandidate forbidLicense() {
        this.licenseForbiddenDate = Instant.now();
        return this;
    }

    private boolean isRegisteredForCourse() {
        return Optional.ofNullable(courseRegistrationDate).isPresent();
    }

    private boolean isCourseCompleted() {
        return Optional.ofNullable(courseCompletedDate).isPresent();
    }

    private boolean isRegisteredForExam() {
        return Optional.ofNullable(examRegistrationDate).isPresent();
    }

    UUID getUuid() {
        return uuid;
    }

    public DrivingLicenseCandidate passExam() {
        if (!isCourseCompleted()) {
            throw new IllegalStateException();
        }
        if (!isRegisteredForExam()) {
            throw new IllegalStateException();
        }
        this.examPassedDate = Instant.now();
        return this;
    }
}
