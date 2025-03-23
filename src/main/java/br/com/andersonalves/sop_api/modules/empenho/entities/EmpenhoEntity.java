package br.com.andersonalves.sop_api.modules.empenho.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "empenho", uniqueConstraints = {
        @UniqueConstraint(columnNames = "numeroEmpenho", name = "uniqueNumeroEmpenho")
})
public class EmpenhoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Pattern(regexp = "^\\d{4}NE\\d{4}$", message = "O número do empenho deve seguir o formato YYYYNE0000")
    @Column(nullable = false, unique = true, length = 12)
    private String numeroEmpenho;

    @Column(nullable = false)
    private LocalDate dataEmpenho;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal valorEmpenho;

    @Column(length = 500)
    private String observacao;

}
