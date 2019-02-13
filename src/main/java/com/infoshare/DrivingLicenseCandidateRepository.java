package com.infoshare;

import java.util.UUID;

public interface DrivingLicenseCandidateRepository {

    DrivingLicenseCandidate save(DrivingLicenseCandidate aggregate);

    DrivingLicenseCandidate getByUUID(UUID uuid);

}
