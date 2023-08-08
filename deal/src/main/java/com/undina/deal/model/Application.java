package com.undina.deal.model;

import lombok.*;
import org.hibernate.annotations.Type;
import org.openapitools.model.LoanOfferDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id", nullable = false)
    private Long applicationId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credit_id")
    private Credit credit;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Column(name = "applied_offer")
    @Type(type = "jsonb")
    private LoanOfferDTO appliedOffer;

    @Column(name = "sign_date")
    private LocalDate signDate;

    @Column(name = "ses_code")
    private String sesCode;

    @Type(type = "jsonb")
    @Column(name = "status_history", nullable = false)
    private List<StatusHistory> statusHistory;
}
