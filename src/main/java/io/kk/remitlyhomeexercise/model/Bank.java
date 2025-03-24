package io.kk.remitlyhomeexercise.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('bank_id_seq'")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "iso2", nullable = false, length = 2)
    private String iso2;

    @Column(name = "swift", nullable = false, length = 11)
    private String swift;

    @Column(name = "code_type", length = 10)
    private String codeType;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @Column(name = "timezone", length = 50)
    private String timezone;

    @Column(name = "is_headquarter", nullable = false)
    private Boolean isHeadquarter;
}