package org.jesuitasrioja.ad_changeorg_api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Optional;
import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Peticion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotEmpty
    private String titulo;
    @NotNull
    private String destinatario;
    @NotNull
    private String descripcion;
    private Integer firmantes = 0;
    @NotNull
    @Enumerated
    @Column(columnDefinition = "default '0'")
    private EstadoPeticion estadoPeticion = EstadoPeticion.PENDIENTE;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "peticion_user",
            joinColumns = @JoinColumn(name = "peticion_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> usuarios;

    @Nullable
    @Size(max = 225)
    private String photo;


    @Override
    public String toString() {
        return "Peticion{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", destinatario='" + destinatario + '\'' +
                ", firmantes=" + firmantes +
                ", estadoPeticion=" + estadoPeticion +
                '}';
    }

}
