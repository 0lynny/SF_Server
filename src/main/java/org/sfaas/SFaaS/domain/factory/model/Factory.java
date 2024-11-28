package org.sfaas.SFaaS.domain.factory.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sfaas.SFaaS.domain.sensor.model.Sensor;
import org.sfaas.SFaaS.domain.user.model.User;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Factory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "factory_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String name;

    private String location;

    private LocalDate establishedAt;

    @OneToMany(mappedBy = "factory", fetch = FetchType.LAZY)
    private List<Sensor> sensors;

}
