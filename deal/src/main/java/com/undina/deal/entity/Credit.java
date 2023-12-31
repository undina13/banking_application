package com.undina.deal.entity;


import com.undina.deal.enums.CreditStatus;
import org.openapitools.model.PaymentScheduleElementDTO;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credit_id", nullable = false)
    private Long creditId;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private Integer term;

    @Column(name = "monthly_payment", nullable = false)
    private BigDecimal monthlyPayment;

    @Column(nullable = false)
    private BigDecimal rate;

    @Column(nullable = false)
    private BigDecimal psk;

    @Type(type = "jsonb")
    @Column(name = "payment_schedule", nullable = false)
    private List<PaymentScheduleElementDTO> paymentSchedule;

    @Column(nullable = false)
    private Boolean isInsuranceEnabled;

    @Column(nullable = false)
    private Boolean isSalaryClient;

    @Column(name = "credit_status")
    @Enumerated(EnumType.STRING)
    private CreditStatus creditStatus;
}
