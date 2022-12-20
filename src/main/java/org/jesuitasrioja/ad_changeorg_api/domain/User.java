package org.jesuitasrioja.ad_changeorg_api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String username;

    @NotNull
    private String passwd;

    @NotNull
    //@Column(columnDefinition =  "default '0'")
    private RolUser user_rol = RolUser.USER;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Peticion> peticionList;

    @JsonIgnore
    @ManyToMany(mappedBy = "usuarios")
    private List<Peticion> peticions;

}
