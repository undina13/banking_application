package com.undina.deal.model;


import com.undina.deal.dto.Employment;
import com.undina.deal.dto.Gender;
import com.undina.deal.dto.MaritalStatus;
import com.undina.deal.dto.Passport;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", nullable = false)
    private Long clientId;
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "birth_date", nullable = false)
    LocalDate date;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @Enumerated(EnumType.STRING)
    MaritalStatus maritalStatus;

    @Column(name = "dependent_amount", nullable = false)
    Integer dependentAmount;

    @JdbcTypeCode(SqlTypes.JSON)
    private Passport passport;

    @JdbcTypeCode(SqlTypes.JSON)
    private Employment employment;

    @Column(nullable = false)
    String account;
}
