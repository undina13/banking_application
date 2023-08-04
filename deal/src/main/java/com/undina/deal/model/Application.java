package com.undina.deal.model;

import com.undina.deal.dto.ApplicationStatus;
import com.undina.deal.dto.LoanOfferDTO;
import com.undina.deal.dto.StatusHistory;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id", nullable = false)
    private Long applicationId;

    @OneToOne(cascade = CascadeType.ALL)
    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    private Credit credit;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Column(name = "applied_offer", nullable = false)
    @Type(type = "jsonb")
    private LoanOfferDTO appliedOffer;

    @Column(name = "sign_date", nullable = false)
    private LocalDate signDate;

    @Column(name = "ses_code")
    private String sesCode;

    @Type(type = "jsonb")
    @Column(name = "status_history", nullable = false)
    private StatusHistory statusHistory;
}
