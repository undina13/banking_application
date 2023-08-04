package com.undina.deal.model;

import com.undina.deal.dto.PaymentScheduleElement;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

    @JdbcTypeCode(SqlTypes.JSON)
    private List<PaymentScheduleElement> paymentSchedule;

    @Column(nullable = false)
    private Boolean isInsuranceEnabled;

    @Column(nullable = false)
    private Boolean isSalaryClient;

}
